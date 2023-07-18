package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.domain.User;

import java.sql.*;
import java.util.List;

public class UserDaoSQLImpl implements UserDao{

    private Connection connection;



    public UserDaoSQLImpl(){
        try{
            this.connection= DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7633145?serverTimeZone=UTC","sql7633145","jk7sNa4jhC");
        } catch (SQLException e) {
            System.out.println("Greška u radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }
    @Override
    public User getById(int id) {
        String query="SELECT * FROM users WHERE id = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                rs.close();
                return user;
            }else{
                return null;   //if result set is empty then return null
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(User item) {

    }

    @Override
    public void update(User item) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
