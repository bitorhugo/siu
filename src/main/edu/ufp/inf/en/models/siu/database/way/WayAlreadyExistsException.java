package main.edu.ufp.inf.en.models.siu.database.way;

public class WayAlreadyExistsException extends Exception {
    public WayAlreadyExistsException() {}

    public WayAlreadyExistsException(String message) {
        super(message);
    }
}
