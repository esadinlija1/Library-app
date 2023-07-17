package ba.unsa.etf.rpr.domain;

public class User implements IDable{

    private int id;
    private String name;
    private String email;
    private String phone; //For now, phone number will be String, maybe it will be changed to something else later
    @Override
    public void setId(int id) {

    }

    @Override
    public int getId() {
        return 0;
    }
}
