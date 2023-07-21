package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
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

    /***
     * This method is for validation of author's name
     * @param author
     * @throws LibraryException
     */
    public void validateBookAuthor(String author) throws LibraryException {
        if(author==null || author.length()<3 || author.length()>100)
            throw new LibraryException("Name of book author is too short");
    }

    /***
     * Implements add method from DAO layer in business logic
     * @param book
     * @return
     */
    public Book add(Book book) throws LibraryException {
        if(book.getId()!=0){
            throw new LibraryException("Can't add a book with ID. ID is autogenerated");
        }

        validateBookAuthor(book.getAuthor());
        validateBookTitle(book.getTitle());

        try {
            return DaoFactory.bookDao().add(book);
        }catch(LibraryException e){
            throw e;
        }

    }




}
