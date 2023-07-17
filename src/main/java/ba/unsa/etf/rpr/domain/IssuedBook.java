package ba.unsa.etf.rpr.domain;

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

    public String toString(){
        return "IssueOfBook{" +
                "id=" + id +
                ", bookID=" + bookID +
                ", userID=" + userID +
                ", issueDate=" + issueDate +
                '}';
    } //Although in general it doesn't make sense to implement toString method for this specific class, it will probably be used at some
    //point for testing purposes




}
