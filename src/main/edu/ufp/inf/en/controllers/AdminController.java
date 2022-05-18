package main.edu.ufp.inf.en.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.models.siu.user.Admin;

public class AdminController {

    @FXML
    private Button mapButton;
    @FXML
    private Button editButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button exportButton;
    @FXML
    private Button logoutButton;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Admin admin;

    @FXML
    protected void handleMapButtonAction (ActionEvent event) throws IOException {
        switchScene(event, "../resources/Map.fxml");
    }

    @FXML
    protected void handleEditButtonAction (ActionEvent event) throws IOException {
        switchScene(event, "../resources/Edit.fxml");
    }

    @FXML
    protected void handleHistoryButtonAction (ActionEvent event) throws IOException {
        switchScene(event, "../resources/History.fxml");
    }

    @FXML
    protected void handleExportButtonAction (ActionEvent event) throws IOException {
        switchScene(event, "../resources/Export.fxml");
    }

    @FXML
    protected void handleLogoutButtonAction (ActionEvent event) throws IOException {
        switchScene(event, "../resources/Login.fxml");
    }

   protected void switchScene (ActionEvent event, String fxmlPath) throws IOException {
    root = FXMLLoader.load(getClass().getResource(fxmlPath));
    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root, 700, 700);
    stage.setScene(scene);
    stage.show();
   }


}
