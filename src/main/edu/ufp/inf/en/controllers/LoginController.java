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

    private DataBase database;

    
    public LoginController (DataBase db) {
        this.database = db;
    }

    public DataBase getDatabase() {
        return database;
    }

    public void setDatabase(DataBase database) {
        this.database = database;
    }

    /**
     * handler for submitButtonAction
     * @param event event
     * @throws IOException IO exception
     */
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException {

        String id = usernameField.getText();
        String password = passwordField.getText();

        User u = database.searchUser(id);

        // surround with try/catch in case user is null
        // otherwise nullPointerException will rise and throw an error
        try {
            if (u.getPassword().equals(password)) {
                //textActionTarget.setText("Valid credentials");
                if (u instanceof Admin)
                    switchToAdminGUI(event, (Admin)u);
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
    public void switchToAdminGUI(ActionEvent event, Admin admin) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/AdminGUI.fxml"));
        
        // inject constructor
        loader.setControllerFactory(c -> {
            return new AdminController(admin);
        });

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
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
        scene = new Scene(BasicGUI, 700, 700);
        stage.setScene(scene);
        stage.show();
    }

}
