package main.edu.ufp.inf.en.models.siu.database.tag;

public class TagNotFoundException extends Exception{
    public TagNotFoundException () {}

    public TagNotFoundException(String message) {
        super(message);
    }
}
