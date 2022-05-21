package main.edu.ufp.inf.en.models.siu.database.poi;

public class PoiNotFoundException extends Exception{
    
    public PoiNotFoundException() {}

    public PoiNotFoundException (String message) {
        super(message);
    }

}
