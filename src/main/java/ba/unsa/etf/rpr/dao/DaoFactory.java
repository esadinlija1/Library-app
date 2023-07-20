package ba.unsa.etf.rpr.dao;

/***
 * Factory method for singleton implementation of DAOs
 */
public class DaoFactory {

    private static final BookDao bookDao=new BookDaoSQLImpl();
    private static final UserDao userDao=new UserDaoSQLImpl();
    private static final IssuedBookDao issuedBookDao=new IssuedBookDaoSQLImpl();

    private DaoFactory(){

    }

    public static BookDao bookDao(){
        return bookDao;
    }
    public static UserDao userDao(){
        return userDao;
    }
    public static IssuedBookDao issuedBooksDao(){
        return issuedBookDao;
    }



}
