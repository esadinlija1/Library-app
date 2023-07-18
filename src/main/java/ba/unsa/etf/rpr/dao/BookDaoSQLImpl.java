package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Book;

import java.sql.*;
import java.util.List;


/***
 * Implementation of basic CRUD operations for Book beans, defined in Dao interface
 */
public class BookDaoSQLImpl implements BookDao{

    private Connection connection;


    /***
     * Constructor in which we define connection property, which is needed to access our database.
     * Throws exception if something went wrong with connecting to database
     */
    public BookDaoSQLImpl(){
        try{
            this.connection= DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7633145?serverTimeZone=UTC","sql7633145","jk7sNa4jhC");
        } catch (SQLException e) {
            System.out.println("Greška u radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Book getById(int id) {
        String query="SELECT * FROM categories WHERE id = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                Book book=new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                rs.close();
                return book;
            }else{
                return null;   //if result set is empty then return null
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Book item) {

        String insert="INSERT INTO books(id,title,author) VALUES(?)";
        try{
            PreparedStatement stmt=connection.prepareStatement(insert);
            stmt.setInt(1,item.getId());
            stmt.setString(2,item.getTitle());
            stmt.setString(3,item.getAuthor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Greška u radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Book item) {
       String update="UPDATE books SET title=?,author=? WHERE id=?";
       try{
           PreparedStatement stmt=this.connection.prepareStatement(update);
           stmt.setObject(1,item.getTitle());
           stmt.setObject(2,item.getAuthor());
           stmt.setObject(3,item.getId());
           stmt.executeUpdate();
       }catch (SQLException e) {
           throw new RuntimeException(e);
       }

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Book> getAll() {
        return null;
    }
}
