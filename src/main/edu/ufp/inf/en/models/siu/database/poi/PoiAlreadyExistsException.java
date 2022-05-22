package main.edu.ufp.inf.en.models.siu.database.poi;

public class PoiAlreadyExistsException extends Exception{
    public PoiAlreadyExistsException () {}

    public PoiAlreadyExistsException (String message) {
        super(message);
    }
}
