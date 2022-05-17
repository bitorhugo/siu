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
import main.edu.ufp.inf.en.models.siu.user.Admin;
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
    private Parent BasicGUI;

    private DataBase database = new DataBase();

    /**
     * handler for submitButtonAction
     * @param event event
     * @throws IOException IO exception
     */
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        Upload.Users(database);
        Upload.Nodes(database);
        Upload.Ways(database);
        String id = usernameField.getText();
        String password = passwordField.getText();

        User u = database.searchUser(id);

        // surround with try/catch in case user is null
        // otherwise nullPointerException will rise and throw an error
        try {
            if (u.getPassword().equals(password)) {
                //textActionTarget.setText("Valid credentials");
                if (u instanceof Admin)
                    switchToAdminGUI(event);
                else
                    switchToBasicGUI(event);
            }
            else {
                textActionTarget.setText("Invalid credentials");
            }
        } catch (Exception e) {
            textActionTarget.setText("user not found");
        }
        
        
    }

    /**
     * switches scene to AdminGUI
     * @param event event
     * @throws IOException IO exception
     * @author Vitor Hugo
     */
    public void switchToAdminGUI(ActionEvent event) throws IOException {
        AdminGUI = FXMLLoader.load(getClass().getResource("../resources/AdminGUI.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(AdminGUI, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switches scene to BasicGUI
     * @param event event
     * @throws IOException IO exception
     * @author Vitor Hugo
     */
    private void switchToBasicGUI(ActionEvent event) throws IOException {
        BasicGUI = FXMLLoader.load(getClass().getResource("../resources/BasicGUI.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(BasicGUI, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

}
