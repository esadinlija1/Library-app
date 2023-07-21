package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.exceptions.LibraryException;

/***
 * Business logic layer for management of Books
 */

public class BookManager {

    /***
     * Following method is used to validate title field when adding new book, or making an update
     * @param title
     */
    public void validateBookTitle(String title) throws LibraryException {
        if(title==null || title.length()<3 || title.length()>100)
            throw new LibraryException("Title of the book is too short");
    }

}
