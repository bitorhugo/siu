package main.edu.ufp.inf.en.models.siu.user;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(){}

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
