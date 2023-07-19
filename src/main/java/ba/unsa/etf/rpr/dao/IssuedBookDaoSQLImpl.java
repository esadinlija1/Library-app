package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.domain.IssuedBook;

import java.sql.*;
import java.util.ArrayList;
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
        String query="SELECT * FROM issued_books WHERE issueID = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
               IssuedBook issuedBook=new IssuedBook();
                issuedBook.setId(rs.getInt("issueID"));
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
        String insert="INSERT INTO issued_books(issueID,bookID,userID,issueDate) VALUES(?,?,?,?)";
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
        String update="UPDATE issued_books SET bookID=?,userID=?,issueDate=? WHERE issueID=?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(update);
            stmt.setObject(1,item.getBookID());
            stmt.setObject(2,item.getUserID());
            stmt.setObject(3,item.getIssueDate());
            stmt.setObject(4,item.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String delete="DELETE FROM issued_books WHERE issueID = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(delete);
            stmt.setObject(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<IssuedBook> getAll() {
        String query="SELECT * FROM issued_books";
        List<IssuedBook> issuedBooks=new ArrayList<IssuedBook>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                IssuedBook issuedBook= new IssuedBook();
                issuedBook.setId(rs.getInt("issuedID"));
                issuedBook.setBookId(rs.getInt("bookID"));
                issuedBook.setUserID(rs.getInt("userID"));
                issuedBook.setIssueDate(rs.getString("issueDate"));
                issuedBooks.add(issuedBook);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return issuedBooks;
    }
}

