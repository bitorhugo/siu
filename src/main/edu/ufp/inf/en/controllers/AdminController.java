package main.edu.ufp.inf.en.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.models.siu.user.*;

public class AdminController extends BasicController{

    public final double WIDTH = 700;
    public final double HEIGHT = 700;

    private Stage stage;
    private Scene scene;

    @FXML
    private Button editButton;   

    public AdminController (User user) {
        super(user);
        if (!(this.getUser() instanceof Admin))
            throw new IllegalArgumentException("argument to AdminController constructor is not an Admin");
    }
    
    @FXML
    public void handleEditButtonAction (ActionEvent event) throws IOException {
        // load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/Edit.fxml"));
        // inject constructor into controller
        loader.setControllerFactory(c -> {
            return new EditController(super.getUser());
        });
        // set stage and scene
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }


}
