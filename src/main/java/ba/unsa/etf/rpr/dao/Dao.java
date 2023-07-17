package ba.unsa.etf.rpr.dao;


import java.util.List;

/***
 * This is core interface that will define methods necessary for all other interfaces in DAO layer.
 * Here we will define methods for basic CRUD operations
 * @param <T>
 */
public interface Dao<T> {

    /***
     * This method gets an item from database based on its id. Applicable on all domain classes
     * @param id
     * @return
     */
    T getById(int id);

    /***
     * 'add' method, as its name suggests, adds an item to database. Based on the time of object that is passed as argument,
     * it will be added to its matching table
     * @param item
     */
    void add(T item);

    /***
     * 'update' method will use an object passed as methods argument, and it will update a row in table based on id attribute
     * of object, in way that object which is currently in database will be completely replaced by other
     * @param item
     */
    void update(T item);


    /***
     * 'delete' method removes item from database based on its id
     * @param id
     */

    void delete(int id);

    /***
     * Lists all entities of type T from database
     * @return List of entities
     */
    List<T> getAll();


}

