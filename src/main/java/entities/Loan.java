
package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author miade
 */
@Entity
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loan_id;
    
    private String chenckout_date;
    private String due_date;
    private String returned_date;
    
    @ManyToOne(cascade = { CascadeType.PERSIST })
    private Book book;
    private String isbn;
     
    @ManyToOne(cascade = { CascadeType.PERSIST })
    private User member;
    private String memberName;

    public Loan() {}

    public Loan(int loan_id, String chenckout_date, String due_date, 
            String returned_date) {
        this.loan_id = loan_id;
        this.chenckout_date = chenckout_date;
        this.due_date = due_date;
        this.returned_date = returned_date;
    }

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public String getChenckout_date() {
        return chenckout_date;
    }

    public void setChenckout_date(String chenckout_date) {
        this.chenckout_date = chenckout_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getReturned_date() {
        return returned_date;
    }

    public void setReturned_date(String returned_date) {
        this.returned_date = returned_date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        if (book != null){
            this.book = book;
            book.setLoans(this);
        } else {
            this.book = null;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    
    
    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    
    
    

}
