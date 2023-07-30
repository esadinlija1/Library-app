package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.exceptions.LibraryException;

import java.util.List;

/***
 * Dao interface for Book domain bean
 */
public interface BookDao extends Dao<Book>{

    List<Book> searchByTitle(String title) throws LibraryException;
}
