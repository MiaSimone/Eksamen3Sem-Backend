
package dto;

import entities.Book;
import entities.Loan;
import entities.User;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author miade
 */
public class LoanDTO {
    
    private int loan_id;
    private String chenckout_date;
    private String due_date;
    private String returned_date;
    private String isbn;
    private String member;

    public LoanDTO() {}

    public LoanDTO(Loan l) {
        this.loan_id = l.getLoan_id();
        this.chenckout_date = l.getChenckout_date();
        this.due_date = l.getDue_date();
        this.returned_date = l.getReturned_date();
        this.isbn = l.getBook().getIsbn();
        this.member = l.getMember().getUserName();
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoanDTO other = (LoanDTO) obj;
        if (this.loan_id != other.loan_id) {
            return false;
        }
        return true;
    }
    
    
    
}
