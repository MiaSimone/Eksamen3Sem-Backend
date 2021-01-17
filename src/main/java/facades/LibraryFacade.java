package facades;

import dto.BookDTO;
import dto.BooksDTO;
import dto.LoanDTO;
import entities.Library;
import entities.Book;
import entities.Loan;
import entities.User;
import exceptions.BookNotFound;
import exceptions.MissingInput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class LibraryFacade implements ILibraryFacade{

    private static LibraryFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private LibraryFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static LibraryFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LibraryFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getBookCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long bookCount = (long)em.createQuery("SELECT COUNT(b) FROM Book b").getSingleResult();
            return bookCount;
        }finally{  
            em.close();
        }
        
    }
    
    // GET metoder:
    @Override
    public BookDTO getBook(int id) throws BookNotFound {
       //String bISBN = isbn;
       long b_id = id;
       
       EntityManager em = getEntityManager();
       try {
           Book book = em.find(Book.class, b_id);
           
           if (book == null) {
                throw new BookNotFound(String.format("No book with provided id found.", id));
            } else {
                return new BookDTO(book);
           }
       } finally {
           em.close();
       }
    }

    @Override
    public BooksDTO getAllBooks() {
        EntityManager em = getEntityManager();
        try {
            return new BooksDTO(em.createNamedQuery("Book.getAllRows").getResultList());
        } finally{  
            em.close();
        }   
    }
    
    // OPG-5:
    @Override
    public BookDTO addBook(Book b, String libraryName) throws MissingInput{
        
        checkFormMissingInput(b.getIsbn(), b.getTitle(), b.getAuthors(), 
                b.getPublisher(), b.getPublishYear());
        
        b.setLibrary(new Library(libraryName));
        Library library = b.getLibrary();
        
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
                List<Library> libraries = getLibrariesFromDB(em, library);
            
                checkForExcistingLibrary(libraries, b, libraryName);
                em.persist(b);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BookDTO(b);
    }
    
    private List<Library> getLibrariesFromDB(EntityManager em, Library library) {
        Query q = em.createQuery(
          "SELECT l FROM Library l WHERE l.name= :name"
        );
        q.setParameter("name", library.getName());
        
        List<Library> libraries = q.getResultList();
        
        return libraries;
    }

    private void checkForExcistingLibrary(List<Library> libs, Book book, String name) {
        if (libs.size() > 0){
            book.setLibrary(libs.get(0));
        } else {
            book.setLibrary(new Library(name));
        }
    }

    private void checkFormMissingInput(String isbn, String title, String authors,
            String publisher, int publishYear) throws MissingInput {
        if ((isbn.length() == 0)){
            throw new MissingInput("ISBN is missing.");
        } else if ((title.length() == 0)){
            throw new MissingInput("Title is missing.");
        } else if ((authors.length() == 0)){
            throw new MissingInput("Authors are missing.");
        } else if ((publisher.length() == 0)){
            throw new MissingInput("Publisher is missing.");
        } else if ((publishYear < 1000 && publishYear > 2030)){
            throw new MissingInput("Year is not valid.");
        }
    }
    
    @Override
    public BookDTO deleteBook(int id) throws BookNotFound {
          EntityManager em = getEntityManager();
         
          Long b_id = Long.valueOf(id);
         
          Book book = em.find(Book.class, (b_id+1));
          if (book == null) {
            throw new BookNotFound(String.format("Book with id: (%d) not found", id));
          } else {
                try {
                    em.getTransaction().begin();
                        em.remove(book);
                    em.getTransaction().commit();
                } finally {
                    em.close();
            }
            return new BookDTO(book);
          }
    }

    @Override
    public BookDTO editBook(BookDTO b) throws BookNotFound, MissingInput {
        checkFormMissingInput(b.getIsbn(), b.getTitle(), b.getAuthors(), 
                b.getPublisher(), b.getPublishYear());
        EntityManager em = getEntityManager();
        Book book = em.find(Book.class, b.getId());
        if (book == null) {
                throw new BookNotFound(String.format("No book with provided id found", b.getId()));
        } else {
            book.setIsbn(b.getIsbn());
            book.setTitle(b.getTitle());
            book.setAuthors(b.getAuthors());
            book.setPublisher(b.getPublisher());
            book.setPublishYear(b.getPublishYear());
            book.setLibrary(new Library(b.getLibraryName()));
            try {
                em.getTransaction().begin();
                    em.merge(book);
                em.getTransaction().commit();

                    return new BookDTO(book);
               
            } finally {  
            em.close();
          }
        }    
    }
    
    
    
    
    
    
    
    
    
    @Override
    public LoanDTO createLoan(Loan loan, String isbn, String member){
        
        loan.setIsbn(isbn);
        loan.setMemberName(member);
        
        String checkout = "";
        String due = "";
        String returned = "";
        
        
        LocalDate checkout_date = LocalDate.now();
        checkout = checkout_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        LocalDate due_date = checkout_date.plus(2, ChronoUnit.WEEKS);
        due = due_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        LocalDate now = LocalDate.now();
        if (now == due_date){
            LocalDate returned_date = due_date;
            returned = returned_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            returned = "Not returned yet.";
        }
        
        loan.setChenckout_date(checkout);
        loan.setDue_date(due);
        loan.setReturned_date(returned);
        
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
                em.persist(loan);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new LoanDTO(loan);
    }

    public static void main(String[] args) {
        LocalDate checkout_date = LocalDate.now();
        
        String test = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        System.out.println("Loan date: " + checkout_date);
        LocalDate due_date = checkout_date.plus(2, ChronoUnit.WEEKS);
        System.out.println("2 weeks: " + due_date);
        
        LocalDate returned_date = LocalDate.now();
    }

}
