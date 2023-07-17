package ba.unsa.etf.rpr.domain;

public class User implements IDable{

    private int id;
    private String name;
    private String email;
    private String phone; //For now, phone number will be String, maybe it will be changed to something else later
    @Override
    public void setId(int id) {
         this.id=id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name=name;
    }
    public void setEmail(String email) {
        this.email=email;
    }
    public void setPhone(String phone) {
        this.phone=phone;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhone(){
        return this.phone;
    }



}
