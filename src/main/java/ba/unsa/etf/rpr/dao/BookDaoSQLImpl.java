package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/***
 * MySQL implementation for Book beans
 */
public class BookDaoSQLImpl extends AbstractDao<Book> implements BookDao{

    private Connection connection;


    /***
     * Constructor which uses constructor defined in AbstractDao
     *
     */
    public BookDaoSQLImpl()  {
        super("books");
    }

    /***
     * Following two methods are abstract methods from AbstractDao class. We will use
     * them to define how app should get Book instance based on data in row, and how should Book bean be transformed to row in table
     * @param rs
     * @return
     */
    @Override
    public Book row2object(ResultSet rs) {
        try{
            Book book=new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> object2row(Book object) {
        Map<String,Object> row=new TreeMap<String,Object>();
        row.put("id",object.getId());
        row.put("title",object.getTitle());
        row.put("author",object.getAuthor());
        return row;
    }




}
