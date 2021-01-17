package Facade;

import dto.BookDTO;
import dto.BooksDTO;
import utils.EMF_Creator;
import entities.Book;
import exceptions.MissingInput;
import exceptions.BookNotFound;
import facades.LibraryFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
@Disabled
public class LibraryFacadeTest {
    

    private static EntityManagerFactory emf;
    private static LibraryFacade facade;
    private static Book b1, b2;

    public LibraryFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = LibraryFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        b1 = new Book("3983923925", "Harry Potter", "J.K Rowling", "Magic Books", 1997);
        b2 = new Book("4+393785434", "Kriger Prinsessen", "Josefine Ottesen", "Gyldendal", 1999);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Book.deleteAllRows").executeUpdate();
                em.persist(b1);
                em.persist(b2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    
    @Test
    public void testGetBook() throws BookNotFound {
        System.out.println("Tester getBook");
        
        Long tempID = b1.getId();
        int id = tempID.intValue();
        
        EntityManagerFactory _emf = null;
        LibraryFacade lFac = LibraryFacade.getFacadeExample(_emf);
        
        BookDTO expResult = new BookDTO(b1);
        BookDTO result = lFac.getBook(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBookCount() {
        System.out.println("Tester getBookCount");
        
        EntityManagerFactory _emf = null;
        LibraryFacade pFac = LibraryFacade.getFacadeExample(_emf);
        
        Long expResult = 2L;
        Long result = pFac.getBookCount();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetAll() {
        System.out.println("Tester getAll");
        
        EntityManagerFactory _emf = null;
        LibraryFacade pFac = LibraryFacade.getFacadeExample(_emf);
        
        int expResult = 2;
        BooksDTO result = pFac.getAllBooks();
        
        assertEquals(expResult, result.getAll().size());
    }
    
    @Test
    public void testGetAllContains() {
        System.out.println("Tester getAll contains");
        
        EntityManagerFactory _emf = null;
        LibraryFacade pFac = LibraryFacade.getFacadeExample(_emf);
        
        BooksDTO result = pFac.getAllBooks();
        
        BookDTO b1DTO = new BookDTO(b1);
        BookDTO b2DTO = new BookDTO(b2);
        
        assertThat(result.getAll(), containsInAnyOrder(b1DTO, b2DTO));
    }
    
    @Test
    public void testAddBook() throws MissingInput {
        System.out.println("Tester addBook");
        
        String isbn = "632764735267432";
        String title = "Test";
        String authors = "J.K";
        String publisher = "Gyldendal";
        int publishyear = 2020;
        String library = "Biblioteka";
        
        EntityManagerFactory _emf = null;
        LibraryFacade pFac = LibraryFacade.getFacadeExample(_emf);
        
        Book b = new Book(isbn, title, authors, publisher, publishyear);
        BookDTO result = pFac.addBook(b, library);
        
        BookDTO expResult = new BookDTO(new Book(isbn, title, authors, publisher, publishyear));
        
        expResult.setId(expResult.getId());
        assertEquals(expResult.getTitle(), result.getTitle());
    }
    
    @Test
    public void testEditBook() throws BookNotFound, MissingInput {
        System.out.println("Tester editBook");
        
        BookDTO pDto = new BookDTO(b1);
        
        EntityManagerFactory _emf = null;
        LibraryFacade pFac = LibraryFacade.getFacadeExample(_emf);
        
        BookDTO expResult = new BookDTO(b1);
        expResult.setTitle("Bo");
        
        pDto.setTitle("Bo");
        
        BookDTO result = pFac.editBook(pDto);
        assertEquals(expResult.getTitle(), result.getTitle());
    }

    @Test
    public void testDeleteBook() throws BookNotFound {
        System.out.println("Tester deleteBook");
        
        long id = b2.getId();
        
        int p_id = (int) id;
        
        EntityManagerFactory _emf = null;
        LibraryFacade pFac = LibraryFacade.getFacadeExample(_emf);
        
        BookDTO expResult = new BookDTO(b2);
        BookDTO result = pFac.deleteBook(p_id);
        
        assertEquals(expResult, result);
    }
}
