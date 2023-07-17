package ba.unsa.etf.rpr.domain;

import java.util.Objects;


/***
 * POJO bean for books table. Contains data for each book, which include unique ID.
 * Number of books with same title and author can have different IDs, which is case because in real libraries
 * there are plenty of same books, so multiple users can borrow the same book
 */
public class Book implements IDable{

    private int id;
    private String title;
    private String author;

    @Override
    public void setId(int id) {
         this.id=id;
    }

    @Override
    public int getId() {
        return this.id;
    }


    public void setAuthor(String author){
        this.author=author;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(){
        this.title=title;
    }


    @Override
    public String toString(){
        return "Book{" +
                "id=" + id +
                ", title=" + title +
                ", author=" + author +
                '}';
    } //For now, I can't figure out when exactly I will need toString method, so for beginnig, it should return something
    //similar to a row in table

    @Override
    public boolean equals(Object o){
        if(this==o) return true; //In case that the same instance on which method was called is passed as argument
        if(o==null || o.getClass()!=getClass()) return false; //In case that null-object or object that is not of Book type is passed as argument
        Book book=(Book) o; // o downcasted as Book
        return this.id==book.id; //check if id of current object and o are equal
    }

    @Override
    public int hashCode(){
        return Objects.hash(id,title,author);
    }



}
