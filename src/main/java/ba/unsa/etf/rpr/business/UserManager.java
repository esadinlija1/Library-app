package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.exceptions.LibraryException;

public class UserManager {

    public void validateUserName(String name) throws LibraryException {
        if(name==null || name.length()<2 || name.length()>100)
            throw new LibraryException("Invalid name of user");
    }

    public void validateUserEmail(String email) throws LibraryException {

        int ioFampersand=email. indexOf("@");

        if(email==null || email.contains("@")==false || email.contains(".com")==false || email.contains("@.com")==true
        || email.substring(0,ioFampersand).length()==0)
            throw new LibraryException("Invalid email of user");
    }

    public void validateUserPhone(String phone) throws LibraryException{
        if(phone.matches("[a-zA-Z]+"))
            throw new LibraryException("Invalid phone number of user");
    }


}
