package ba.unsa.etf.rpr.dao;


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


}
