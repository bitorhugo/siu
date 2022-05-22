package main.edu.ufp.inf.en.models.siu.database.node;

public class NodeAlreadyExistsException extends Exception{
    public NodeAlreadyExistsException(){}

    public NodeAlreadyExistsException(String message) {
        super(message);
    }
}
