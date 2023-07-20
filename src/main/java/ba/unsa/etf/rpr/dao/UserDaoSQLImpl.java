package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.domain.IssuedBook;
import ba.unsa.etf.rpr.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao{

    public UserDaoSQLImpl(){
        super("users");
    }

    @Override
    public User row2object(ResultSet rs) {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> object2row(User object) {
        Map<String,Object> row=new TreeMap<String,Object>();
        row.put("id",object.getId());
        row.put("name",object.getName());
        row.put("email",object.getEmail());
        row.put("phone",object.getPhone());
        return row;
    }
}
