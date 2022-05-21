package main.edu.ufp.inf.en.models.siu.database.way;

public class WayNotFoundException extends Exception{
    
    public WayNotFoundException () {}

    public WayNotFoundException (String message) {
        super (message);
    }

}
