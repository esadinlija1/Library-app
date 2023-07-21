package ba.unsa.etf.rpr.exceptions;

public class LibraryException extends Exception{
    public LibraryException(String msg, Exception reason){
        super(msg, reason);
    }

    public LibraryException(String msg){
        super(msg);
    }
}
