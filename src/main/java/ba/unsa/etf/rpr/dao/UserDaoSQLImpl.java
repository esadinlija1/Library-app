package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoSQLImpl implements UserDao{

    private Connection connection;



    public UserDaoSQLImpl() throws ClassNotFoundException {


        try{
            this.connection= DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7633145?serverTimeZone=UTC","sql7633145","jk7sNa4jhC");
        } catch (SQLException e) {
            System.out.println("Greška u radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }
    @Override
    public User getById(int id) {
        String query="SELECT * FROM users WHERE userID = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                User user=new User();
                user.setId(rs.getInt("userID"));
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
        String insert="INSERT INTO users(userID,name,email,phone) VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt=connection.prepareStatement(insert);
            stmt.setInt(1,item.getId());
            stmt.setString(2,item.getName());
            stmt.setString(3,item.getEmail());
            stmt.setString(4,item.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Greška u radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(User item) {
        String update="UPDATE users SET name=?,email=?,phone=? WHERE userID=?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(update);
            stmt.setObject(1,item.getName());
            stmt.setObject(2,item.getEmail());
            stmt.setObject(3,item.getPhone());
            stmt.setObject(4,item.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String delete="DELETE FROM users WHERE userID = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(delete);
            stmt.setObject(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        String query="SELECT * FROM users";
        List<User> users=new ArrayList<User>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                User user = new User();
                user.setId(rs.getInt("userID"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                users.add(user);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
