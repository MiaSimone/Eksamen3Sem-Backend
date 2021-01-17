package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/*
firstName, lastName, phone (String), created, lastEdited (java.utils.Date) and id (Integer).

*/

@Entity
@NamedQueries({
    @NamedQuery(name = "Book.deleteAllRows", query = "DELETE from Book"),
    @NamedQuery(name = "Book.getById", query = "SELECT b FROM Book b WHERE b.isbn= :isbn"),
    @NamedQuery(name = "Book.getAllRows", query = "SELECT b from Book b")
})

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String isbn;
    private String title;
    private String authors;
    private String publisher;
    private int publishYear;
    
    @ManyToOne(cascade = { CascadeType.PERSIST })
    private Library library;
    
    @OneToMany(mappedBy = "book")
    private List<Loan> loans = new ArrayList();
    
    
    public Book() {}

    public Book(String isbn, String title, String authors, String publisher, int publishYear) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishYear = publishYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publish_year) {
        this.publishYear = publish_year;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        if (library != null){
            this.library = library;
            library.setBooks(this);
        } else {
            this.library = null;
        }
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Loan loan) {
        if (loan != null){
            loans.add(loan);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
