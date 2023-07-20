package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.domain.IssuedBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IssuedBookDaoSQLImpl extends AbstractDao<IssuedBook> implements IssuedBookDao{

    public IssuedBookDaoSQLImpl(){
        super("issued_books");
    }

    @Override
    public IssuedBook row2object(ResultSet rs) {
        try {
            IssuedBook issuedBook = new IssuedBook();
            issuedBook.setId(rs.getInt("id"));
            issuedBook.setBookId(rs.getInt("bookID"));
            issuedBook.setUserID(rs.getInt("userID"));
            issuedBook.setIssueDate(rs.getString("issueDate"));
            return issuedBook;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> object2row(IssuedBook object) {
            Map<String,Object> row=new TreeMap<String,Object>();
            row.put("id",object.getId());
            row.put("bookID",object.getBookID());
            row.put("userID",object.getUserID());
            row.put("issueDate",object.getIssueDate());
            return row;
    }
}

