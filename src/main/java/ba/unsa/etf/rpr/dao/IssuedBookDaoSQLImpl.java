package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.domain.IssuedBook;

import java.sql.*;
import java.util.List;

public class IssuedBookDaoSQLImpl implements IssuedBookDao{
    private Connection connection;



    public IssuedBookDaoSQLImpl(){
        try{
            this.connection= DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7633145?serverTimeZone=UTC","sql7633145","jk7sNa4jhC");
        } catch (SQLException e) {
            System.out.println("Greška u radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }
    @Override
    public IssuedBook getById(int id) {
        String query="SELECT * FROM issued_books WHERE id = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
               IssuedBook issuedBook=new IssuedBook();
                issuedBook.setId(rs.getInt("id"));
                issuedBook.setBookId(rs.getInt("bookID"));
                issuedBook.setUserID(rs.getInt("userID"));
                issuedBook.setIssueDate(rs.getString("issueDate"));
                rs.close();
                return issuedBook;
            }else{
                return null;   //if result set is empty then return null
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(IssuedBook item) {
        String insert="INSERT INTO issued_books(id,bookID,userID,issueDate) VALUES(?)";
        try{
            PreparedStatement stmt=connection.prepareStatement(insert);
            stmt.setInt(1,item.getId());
            stmt.setInt(2,item.getBookID());
            stmt.setInt(3,item.getUserID());
            stmt.setString(4,item.getIssueDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Greška u radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(IssuedBook item) {
        String update="UPDATE issued_books SET bookID=?,userID=?,issueDate=? WHERE id=?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(update);
            stmt.setObject(1,item.getBookID());
            stmt.setObject(2,item.getUserID());
            stmt.setObject(3,item.getIssueDate());
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<IssuedBook> getAll() {
        return null;
    }
}
