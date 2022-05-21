package main.edu.ufp.inf.en.models.siu.user;

public class UserNotFoundException extends Exception{
    
    public UserNotFoundException() {}

    public UserNotFoundException(String message) {
        super (message);
    }

}
