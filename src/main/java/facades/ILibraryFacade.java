/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.BookDTO;
import dto.BooksDTO;
import dto.LoanDTO;
import entities.Book;
import entities.Loan;
import entities.User;
import exceptions.BookNotFound;
import exceptions.MissingInput;

/**
 *
 * @author miade
 */
public interface ILibraryFacade {
  public BookDTO addBook(Book book, String libraryName) throws MissingInput;   
  public BooksDTO getAllBooks();  
  public BookDTO getBook(int isbn) throws BookNotFound;
  public BookDTO deleteBook(int id) throws BookNotFound;
  public BookDTO editBook(BookDTO b) throws BookNotFound, MissingInput;  
  
  public LoanDTO createLoan(Loan loan, String book, String member);
  

}


