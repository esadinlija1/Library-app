package ba.unsa.etf.rpr;


import ba.unsa.etf.rpr.dao.BookDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Book;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {

        BookDaoSQLImpl bookDaoSQL=new BookDaoSQLImpl();
        /*bookDaoSQL.add(new Book(1,"Na Drini ćuprija","Ivo Andrić"));
        bookDaoSQL.add(new Book(2,"Tvrđava","Meša Selimović"));*/

        List<Book> books=bookDaoSQL.getAll();

        System.out.println(books.get(0).toString());
        System.out.println((books.get(1).toString()));

    }
}
