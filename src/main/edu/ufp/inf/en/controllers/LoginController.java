package main.edu.ufp.inf.en.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.user.User;


/**
 * Use the FXML Controller to make the Text control display a message when the user presses the button.
 *
 */
public class LoginController {

    /**
     * For the controller to be able to access the Text label, its name should be the same in the fxml and css
     */
    @FXML
    private Text textActionTarget;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private Stage stage;
    private Scene scene;
    private Parent AdminGUI;

    private DataBase database = new DataBase();

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        Upload.Users(database);
        Upload.Nodes(database);
        Upload.Ways(database);
        String id = usernameField.getText();
        String password = passwordField.getText();

        User u = database.searchUser(id);

        if (u != null && u.getPassword().equals(password)) {
            //textActionTarget.setText("Valid credentials");
            AdminGUI = FXMLLoader.load(getClass().getResource("../resources/AdminGUI.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(AdminGUI);
            stage.setScene(scene);
            stage.show();
        }
        else {
            textActionTarget.setText("Invalid credentials");
        }
        
    }

    /**
     *
     * @param event
     */
    @FXML
    protected void handleSubmitButtonAction2(ActionEvent event) {
        textActionTarget.setText("Sign in button calling Controller->handleSubmitButtonAction2()");
    }

    public void switchToAdminGUI(ActionEvent event) throws IOException {
        AdminGUI = FXMLLoader.load(getClass().getResource("main/edu/ufp/inf/en/resources/AdminGUI.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(AdminGUI);
        stage.setScene(scene);
        stage.show();
    }
}
