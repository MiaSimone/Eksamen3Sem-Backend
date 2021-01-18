
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.BookDTO;
import dto.BooksDTO;
import dto.LoanDTO;
import facades.LibraryFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import entities.Book;
import entities.Loan;
import exceptions.BookNotFound;
import exceptions.MissingInput;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

//Todo Remove or change relevant parts before ACTUAL use
@Path("library")
public class LibraryResource {

    
     private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final LibraryFacade FACADE =  LibraryFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
 
   
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Welcome to the MIAAAAA\"}";
    }


    @GET
    @Path("/searchbook/{input}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getBook(@PathParam("input") int input) throws BookNotFound{
        BookDTO bookDTO = FACADE.getBook(input);
        
        return GSON.toJson(bookDTO);
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        BooksDTO books = FACADE.getAllBooks();
        return GSON.toJson(books);
    }
    
    
    @POST	
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addBook(String book) throws MissingInput {
       BookDTO bDTO = GSON.fromJson(book, BookDTO.class); 
       
       Book b = new Book(bDTO.getIsbn(), bDTO.getTitle(), bDTO.getAuthors(), 
               bDTO.getPublisher(), bDTO.getPublishYear());
       
        System.out.println("YEAR: " + b.getPublishYear());
        
       String libraryName = bDTO.getLibraryName();
       
       BookDTO bAdded = FACADE.addBook(b, libraryName);
       return GSON.toJson(bAdded);
    }
    
    /*
    @Path("loan")
    @POST	
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String loanBook(String loan) throws MissingInput {
        
        LoanDTO lDTO = GSON.fromJson(loan, LoanDTO.class); 
        Loan l = new Loan(lDTO.getLoan_id(), lDTO.getChenckout_date(), 
                lDTO.getDue_date(), lDTO.getReturned_date());
        
       LoanDTO loanDTO = FACADE.createLoan(l, lDTO.getIsbn(), lDTO.getMember());
       return GSON.toJson(loanDTO);
    }
    */

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String editBook(@PathParam("id") Long id, String book) throws BookNotFound, MissingInput {
        BookDTO bookDTO = GSON.fromJson(book, BookDTO.class);
        bookDTO.setId(id);
        BookDTO bEdit = FACADE.editBook(bookDTO);
        return GSON.toJson(bEdit);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deletePerson(@PathParam("id") int id) throws BookNotFound {
        BookDTO bDeleted = FACADE.deleteBook(id);
        return GSON.toJson(bDeleted);
    }


}
