package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.LibraryException;

import java.util.List;


/***
 * Dao interface for User domain bean
 */
public interface UserDao extends Dao<User>{

    List<User> searchByName(String name) throws LibraryException;
}
