package ba.unsa.etf.rpr.domain;

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


    public String toString(){
        return "Book{" +
                "id=" + id +
                ", title=" + title +
                ", author=" + author +
                '}';
    } //For now, I can't figure out when exactly I will need toString method, so for beginnig, it should return something
    //similar to a row in table




}
