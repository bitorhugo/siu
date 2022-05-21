package main.edu.ufp.inf.en.models.siu.database.node;

public class NodeNotPresentException extends Exception{
    
    public NodeNotPresentException () {}

    public NodeNotPresentException (String message) {
        super (message);
    }
}
