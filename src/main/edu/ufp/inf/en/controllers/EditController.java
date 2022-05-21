package main.edu.ufp.inf.en.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.models.siu.user.User;

public class EditController implements Initializable{
    @FXML
    private Button backButton;

    @FXML
    private TextField addNode;

    @FXML
    private TextField removeNode;

    @FXML
    private TextField editNode;

    @FXML
    private TextField addWay;

    @FXML
    private TextField removeWay;

    @FXML
    private TextField editWay;


    private Stage stage;

    private Scene scene;

    private User user;

    public EditController (User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }

    public void handleBackButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/AdminGUI.fxml"));;

        loader.setControllerFactory(c -> {
            return new AdminController(this.user);
        });
        
        // set stage and scene
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(loader.load());
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void handleAddNodeAction(ActionEvent event) {

    }

    public void handleRemoveNodeAction(ActionEvent event) {
        
    }

    public void handleEditNodeAction(ActionEvent event) {
        
    }

    public void handleAddWayAction(ActionEvent event) {
        
    }

    public void handleRemoveWayAction(ActionEvent event) {
        
    }

    public void handleEditWayAction(ActionEvent event) {
        
    }


}
