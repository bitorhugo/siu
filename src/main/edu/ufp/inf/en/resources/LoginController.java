package main.edu.ufp.inf.en.resources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.edu.ufp.inf.en.siu.IO.Upload;
import main.edu.ufp.inf.en.siu.database.DataBase;
import main.edu.ufp.inf.en.siu.user.User;


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

    private DataBase database = new DataBase();

    /**
     *
     * @param event
     */
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Upload.Users(database);
        Upload.Nodes(database);
        Upload.Ways(database);
        String id = usernameField.getText();
        String password = passwordField.getText();

        User u = database.searchUser(id);

        if (u != null && u.getPassword().equals(password)) {
            textActionTarget.setText("Valid credentials");
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
}
