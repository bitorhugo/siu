package main.edu.ufp.inf.en.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Node;
import main.edu.ufp.inf.en.models.siu.user.Admin;
import main.edu.ufp.inf.en.models.siu.user.User;

public class HistoryController implements Initializable {

    private final String NEWLINE = "\n";

    @FXML
    private Button backButton;;

    @FXML
    private TextArea textArea;

    private User user;

    private Stage stage;
    private Scene scene;

    public HistoryController (User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String data[] = this.user.history().split(";");

        for (var d : data) {
            this.textArea.appendText(d + NEWLINE);
        }
    }


    public void handleBackButton (ActionEvent event) throws IOException {
        
        FXMLLoader loader;

        if (this.user instanceof Admin) {
            loader = new FXMLLoader(getClass().getResource("../resources/AdminGUI.fxml"));
            loader.setControllerFactory(c -> {
                return new AdminController(this.user);
            });
        }
        else {
            loader = new FXMLLoader(getClass().getResource("../resources/BasicGUI.fxml"));
            loader.setControllerFactory(c -> {
                return new BasicController(this.user);
            });
        }
        
        
        // set stage and scene
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(loader.load());
        this.stage.setScene(scene);
        this.stage.show();
    }

}
