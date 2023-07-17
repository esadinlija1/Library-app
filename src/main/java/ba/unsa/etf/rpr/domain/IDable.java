package ba.unsa.etf.rpr.domain;

/***
 * This is an interface that POJO beans will implement, and it will force them to have ID field, together with getter and setter for it.
 * @author Emin Šadinlija
 */
public interface IDable {

    void setId(int id);

    int getId();
}
