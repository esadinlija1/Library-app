package ba.unsa.etf.rpr;


import ba.unsa.etf.rpr.business.BookManager;
import ba.unsa.etf.rpr.dao.BookDaoSQLImpl;
import ba.unsa.etf.rpr.dao.IssuedBookDaoSQLImpl;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.domain.IssuedBook;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.LibraryException;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws ClassNotFoundException {

       BookManager bookManager=new BookManager();

        /*bookDaoSQL.add(new Book(1,"Na Drini ćuprija","Ivo Andrić"));
        bookDaoSQL.add(new Book(2,"Tvrđava","Meša Selimović"));*/

        List<Book> books= null;
        try {
            books = bookManager.searchByTitle("Zeleno busenje");
            System.out.println(books.get(0).toString());
        } catch (LibraryException e) {
            throw new RuntimeException(e);
        }




       // UserDaoSQLImpl userDaoSQL=new UserDaoSQLImpl();

        /*userDaoSQL.add(new User(1,"Emin Šadinlija","emin.sadinlija@gmail.com","062/095-112"));*/

       /* List<User> users=userDaoSQL.getAll();

        System.out.println(users.get(0).toString());

        IssuedBookDaoSQLImpl issuedBookDaoSQL=new IssuedBookDaoSQLImpl();
        /*issuedBookDaoSQL.add(new IssuedBook(1,1,1,"19.07.2023."));

        List<IssuedBook> issuedBooks=issuedBookDaoSQL.getAll();
        System.out.println(issuedBooks.get(0).toString());*/

    }
}
