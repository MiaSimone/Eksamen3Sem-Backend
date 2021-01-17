/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Book;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miade
 */
public class BooksDTO {
    
    List<BookDTO> all = new ArrayList();

    public BooksDTO(List<Book> bookEntities) {
        bookEntities.forEach((b) -> {
            all.add(new BookDTO(b));
        });
    }

    public List<BookDTO> getAll() {
        return all;
    }

    
}
