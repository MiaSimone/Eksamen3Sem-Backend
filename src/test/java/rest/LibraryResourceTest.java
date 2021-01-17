package rest;

import dto.BookDTO;
import entities.Book;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
@Disabled
public class LibraryResourceTest {
    

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Book b1,b2,b3;
    
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        
        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @AfterAll
    public static void closeTestServer(){
        //System.in.read();
         //Don't forget this, if you called its counterpart in @BeforeAll
         EMF_Creator.endREST_TestWithDB();
         httpServer.shutdownNow();
    }
    
    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        b1 = new Book("3983923925", "Harry Potter", "J.K Rowling", "Magic Books", 1997);
        b2 = new Book("4+393785434", "Kriger Prinsessen", "Josefine Ottesen", "Gyldendal", 1999);
        b3 = new Book("9098876", "Fifty Shades of Grey", "E. L", "Amerika", 2011);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Book.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table BOOK AUTO_INCREMENT = 1").executeUpdate();
                em.persist(b1);
                em.persist(b2); 
                em.persist(b3);
            em.getTransaction().commit();
        } finally { 
            em.close();
        }
    }
    
    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/library").then().statusCode(200);
    }
   
    @Test
    public void testDummyMsg() throws Exception {
        given()
        .contentType("application/json")
        .get("/library/").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("msg", equalTo("Welcome to the library"));   
    }
    
    /*
        getPerson
        getAll
        addPerson
        editPerson
        deletePerson
    */
    
    @Test
    public void testGetBook() throws Exception {
        System.out.println("Testing getting Book");
        
        Long input = b1.getId();
        
        given()
            .contentType("application/json")
            .get("/library/searchbook/" + input)
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK_200.getStatusCode())
            .body("isbn", is("3983923925"))
            .and()
            .body("title", is("Harry Potter"));
    }
    
    
    
    @Test
    public void getAllBooks(){
        System.out.println("Testing getting all");
        
            List<BookDTO> booksDTOs;
        
            booksDTOs = given()
                .contentType("application/json")
                .when()
                .get("/library/all")
                .then()
                .extract().body().jsonPath().getList("all", BookDTO.class);
            
            System.out.println(booksDTOs.size());
            
            BookDTO b1DTO = new BookDTO(b1);
            BookDTO b2DTO = new BookDTO(b2);
            BookDTO b3DTO = new BookDTO(b3);
            
            assertThat(booksDTOs, containsInAnyOrder(b1DTO, b2DTO, b3DTO));
    }
    
    @Test
    public void testAddBook(){
        
        Book b = new Book("7473843674", "Title", "Test author", "Test publisher", 2020);
        
        given()
            .contentType(ContentType.JSON)
            .body(new BookDTO(b))
            .when()
            .post("book")
            .then()
            .body("isbn", equalTo("7473843674"))
            .body("title", equalTo("Title"))
            .body("author", equalTo("Test author"))
            .body("id", notNullValue());
    }
    
    
    @Test
    public void testEditPerson(){
        BookDTO book = new BookDTO(b1);
        book.setTitle("Cool-BoB");
 
        given()
            .contentType(ContentType.JSON)
            .body(book)
            .when()
            .put("person/"+ book.getId())
            .then()
            .body("title", equalTo("Cool-BoB"))
            .body("author", equalTo("J.K Rowling"))
            .body("publisher", equalTo("Magic Books"))
            .body("id", equalTo((int)book.getId()));
    }
    

    @Test
      public void testDelete() throws Exception {
        
        BookDTO book = new BookDTO(b1);
        
        given()
            .contentType("application/json")
            .delete("/library/" + book.getId())
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK_200.getStatusCode());
        
        List<BookDTO> booksDTOs;
        
        booksDTOs = given()
                .contentType("application/json")
                .when()
                .get("/library/all")
                .then()
                .extract().body().jsonPath().getList("all", BookDTO.class);

     

        BookDTO b2DTO = new BookDTO(b2);
        BookDTO b3DTO = new BookDTO(b3);

        assertThat(booksDTOs, containsInAnyOrder(b2DTO, b3DTO));
            
    }
      
      // Exceptions:
      @Test
        public void testGetBookException() throws Exception {
            System.out.println("Testing getting Book exception");

            given()
                .contentType("application/json")
                .get("/library/id/" + 4)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
                .body("message", equalTo("HTTP 404 Not Found"));
        }
      
      @Test
        public void testEditBookException() throws Exception {
            System.out.println("Testing edit book exception");

            BookDTO book = new BookDTO(b1);
 
            given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .put("library/"+ 600)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
                .body("message", equalTo("No book with provided id found"));
        }
      
        @Test
        public void testEditBookException2() throws Exception {
            System.out.println("Testing edit Book exception 2");

            BookDTO book = new BookDTO(b1);
            book.setTitle("");
 
            given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .put("library/"+ book.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST_400.getStatusCode())
                .body("message", equalTo("Title is missing."));
        }
        
      @Test
        public void testDeleteBookException() throws Exception {
            System.out.println("Testing deleting Book exception");
        
            given()
                .contentType("application/json")
                .delete("/library/" + 300)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
                .body("message", equalTo("Book with id: (300) not found"));
        }
        
        @Test
        public void testAddBookException() throws Exception {
            System.out.println("Testing adding Book exception");

            given()
            .contentType(ContentType.JSON)
            .body(new BookDTO(new Book("", "Andersen", "Hello", "Gyldendal", 2020)))
            .when()
            .post("library")
            .then()
            .assertThat()
            .statusCode(HttpStatus.BAD_REQUEST_400.getStatusCode())
            .body("message", equalTo("ISBN is missing."));
        }
        
        
}























