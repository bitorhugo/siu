package main.edu.ufp.inf.en.models.siu.database.node;

public class NodeNotFoundException extends Exception{
    
    public NodeNotFoundException () {}

    public NodeNotFoundException (String message) {
        super (message);
    }
}
