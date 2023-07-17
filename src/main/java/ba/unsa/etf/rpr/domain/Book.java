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


}
