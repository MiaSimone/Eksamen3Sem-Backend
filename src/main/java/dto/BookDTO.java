/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Book;
import java.util.Objects;

/**
 *
 * @author miade
 */
public class BookDTO {
 
    private long id;
    private String isbn;
    private String title;
    private String authors;
    private String publisher;
    private int publishYear;
    private String libraryName;

    public BookDTO(Book b) {
        this.id = b.getId();
        this.isbn = b.getIsbn();
        this.title = b.getTitle();
        this.authors = b.getAuthors();
        this.publisher = b.getPublisher();
        this.publishYear = b.getPublishYear();
        this.libraryName = b.getLibrary().getName();
    }
    
    public BookDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
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
        final BookDTO other = (BookDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


    
    
}
