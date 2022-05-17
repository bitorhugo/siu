import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{

    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        // load fxml file
        Parent root = FXMLLoader.load(getClass().getResource("main/edu/ufp/inf/en/resources/Login.fxml"));

        // set scene
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    
    
    public static void main(String[] args) throws Exception {    
        launch();
    }

}
