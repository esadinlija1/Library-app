package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.IDable;
import ba.unsa.etf.rpr.exceptions.LibraryException;

import java.sql.*;
import java.util.*;

/***
 * Abstract class that implements CRUD operations for every entity.
 * All operations that were previously implemented separately in SQLImpl classes
 * will now be implemented here
 * @param <T>
 */

public abstract  class AbstractDao<T extends IDable> implements Dao<T> {
    private Connection connection;
    private String tableName;


    /***
     * Constructor for AbstractDao takes name of entity as parameter. In each DAOSqlImpl
     * class constructor we will use this constructor
     * @param tableName
     */
    public AbstractDao(String tableName){
        try{
            this.tableName=tableName;
            Properties p=new Properties();
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            this.connection= DriverManager.getConnection(p.getProperty("db.connection_string"),p.getProperty("db.username"),p.getProperty("db.password"));
            //more sophisticated way to connect to database using properties file, no need to name parameters in method
        } catch (Exception e) {
            throw new RuntimeException(e); // catches every possible exception of constructor, no need to separately catch SQLException and IOException
        }

    }

    public Connection getConnection(){
        return this.connection;
    }


    /***
     * This method will adapt a row to object of type T, based on
     * from which entity the result set comes from
     * @param rs
     * @return
     */
    public abstract T row2object(ResultSet rs);


    public abstract Map<String,Object> object2row(T object);


    /***
     * Prepares insert statement. For each column that us to be inserted adds one questionmark, and returns string in format:
     * (column1,column2,...,columnN) ?,?,?
     * @param row
     * @return
     */
    private Map.Entry<String,String> prepareInsertParts(Map<String,Object> row){
        StringBuilder columns=new StringBuilder();
        StringBuilder questions=new StringBuilder();

        int counter=0;
        for(Map.Entry<String,Object> entry:row.entrySet()){
            counter++;
            if(entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if(row.size()!=counter){
                columns.append(",");
                questions.append(",");
            }
        }

        return new AbstractMap.SimpleEntry<String,String>(columns.toString(),questions.toString());
    }


    /***
     * Prepare update statement name=?,...
     * @param row
     * @return
     */
    private String prepareUpdateParts(Map<String,Object> row){
        StringBuilder columns=new StringBuilder();

        int counter=0;
        for(Map.Entry<String,Object> entry:row.entrySet()){
            counter++;
            if(entry.getKey().equals("id")) continue;   //updating id makes no sense so we will just skipp it
            columns.append(entry.getKey()).append("=?");
            if(row.size()!=counter){
                columns.append(",");
            }

        }

        return columns.toString();

    }

    public T add(T item) throws LibraryException {
        Map<String,Object> row=object2row(item);
        Map.Entry<String,String> columns=prepareInsertParts(row);

        StringBuilder builder=new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt=getConnection().prepareStatement(builder.toString(),Statement.RETURN_GENERATED_KEYS);

            int counter=1;
            for(Map.Entry<String,Object> entry:row.entrySet()){
                if(entry.getKey().equals("id")) continue;
                stmt.setObject(counter,entry.getValue());
                counter++;
            }

            stmt.executeUpdate();
            ResultSet rs=stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));

            return item;


        } catch (SQLException e) {
            throw new LibraryException(e.getMessage(),e);
        }

    }

    public T update(T item) throws LibraryException {
        Map<String,Object> row=object2row(item);
        String updateColumns=prepareUpdateParts(row);
        StringBuilder builder=new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(" WHERE id = ?");
        try{
            PreparedStatement stmt=getConnection().prepareStatement(builder.toString());
            int counter=1;
            for(Map.Entry<String,Object> entry:row.entrySet()){
                if(entry.getKey().equals("id")) continue; //skipping id column
                stmt.setObject(counter,entry.getValue());
                counter++;
            }
            stmt.setObject(counter,item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new LibraryException(e.getMessage(),e);
        }
    }

    public T getById(int id) {
        String query="SELECT * FROM " +this.tableName + "WHERE id = ?";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                T result=row2object(rs);
                rs.close();
                return result;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<T> getAll() {
        String query="SELECT * FROM " + tableName;
        List<T> results=new ArrayList<T>();
        try{
            PreparedStatement stmt=getConnection().prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                T object=row2object(rs);
                results.add(object);
            }
            rs.close();
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(int id) {
        String sql="DELETE FROM " + tableName + "WHERE id = ?";
        try{
            PreparedStatement stmt=getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /***
     * Method that will be used to execute any kind of query
     * @param query-SQL query
     * @param params-params for query
     * @return
     * @throws LibraryException
     */

    public List<T> executeQuery(String query, Object[] params) throws LibraryException{
        try{
            PreparedStatement stmt=getConnection().prepareStatement(query);
            if(params!=null){
                for(int i=1;i<=params.length;i++){
                    stmt.setObject(i,params[i-1]);
                }
            }

            ResultSet rs=stmt.executeQuery();
            ArrayList<T> resultList=new ArrayList<>();
            while(rs.next()){
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new LibraryException(e.getMessage());
        }
    }







}
