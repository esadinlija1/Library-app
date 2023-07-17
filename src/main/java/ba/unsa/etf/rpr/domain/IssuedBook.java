package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/***
 * POJO bean that represents data for a single record in table of issued books.
 * For each record there is ID of book that was borrowed and ID of user that borrowed it, both are foreign keys from 'books' and 'users'
 * tables respectively. We will use them to get some useful data later.
 */

public class IssuedBook implements IDable {

    private int id;
    private int bookID;
    private int userID;
    private String issueDate;

    @Override
    public void setId(int id) {
        this.id=id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setBookId(int bookID) {
        this.bookID=bookID;
    }


    public int getBookID() {
        return this.bookID;
    }

    public void setUserID(int userID) {
        this.userID=userID;
    }


    public int getUserID() {
        return this.userID;
    }

    public void setIssueDate(String issueDate){
        this.issueDate=issueDate;
    }

    public String getIssueDate(){
        return this.issueDate;
    }



     @Override
    public String toString(){
        return "IssueOfBook{" +
                "id=" + id +
                ", bookID=" + bookID +
                ", userID=" + userID +
                ", issueDate=" + issueDate +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true; //In case that the same instance on which method was called is passed as argument
        if(o==null || o.getClass()!=getClass()) return false; //In case that null-object or object that is not of Book type is passed as argument
        IssuedBook issuedBook=(IssuedBook) o; // o downcasted as Book
        return this.id==issuedBook.id; //check if id of current object and o are equal
    }

    @Override
    public int hashCode(){
        return Objects.hash(id,bookID,userID,issueDate);
    }



}
