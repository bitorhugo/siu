package main.edu.ufp.inf.en.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.edu.ufp.inf.en.models.siu.user.*;

public class AdminController extends BasicController{

    public final double WIDTH = 700;
    public final double HEIGHT = 700;

    @FXML
    private Button editButton;   

    public AdminController (User user) {
        super(user);
        if (!(this.getUser() instanceof Admin))
            throw new IllegalArgumentException("argument to AdminController constructor is not an Admin");
    }
    
    @FXML
    public void handleEditButtonAction (ActionEvent event) throws IOException {
        
    }


}
