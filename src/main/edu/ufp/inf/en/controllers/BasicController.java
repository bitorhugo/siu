package main.edu.ufp.inf.en.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
//import main.edu.ufp.inf.en.models.siu.IO.Arquive;
import main.edu.ufp.inf.en.models.siu.user.*;

public class BasicController implements UserControllerI{

    public final double WIDTH = 700;
    public final double HEIGHT = 700;

    @FXML
    private Button mapButton;
    
    @FXML
    private Button historyButton;
    
    @FXML
    private Button exportButton;

    @FXML
    private Button logoutButton;
    
    private Stage stage;
    
    private Scene scene;

    private User user;

    public BasicController (User user) {
        this.user = user;    
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public void handleMapButtonAction (ActionEvent event) throws IOException {
        // load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/Map.fxml"));
        // inject constructor into controller
        loader.setControllerFactory(c -> {
            return new MapController(user);
        });
        // set stage and scene
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleHistoryButtonAction (ActionEvent event) throws IOException {
        // load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/History.fxml"));
        // inject constructor into controller
        loader.setControllerFactory(c -> {
            return new HistoryController(this.user);
        });
        // set stage and scene
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleExportButtonAction (ActionEvent event) throws IOException {
        //Arquive.Nodes(user.getDb());
        //Arquive.Ways(user.getDb());
        //Arquive.Users(user.getDb());
    }

    @FXML
    public void handleLogoutButtonAction (ActionEvent event) throws IOException {
        // load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/Login.fxml"));
        // inject constructor into controller
        loader.setControllerFactory(c -> {
            return new LoginController(user.getDb());
        });
        // set stage and scene
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }


}
