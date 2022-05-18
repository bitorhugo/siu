import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;


public class App extends Application{

    
    @Override
    public void start(Stage primaryStage) throws IOException {

        DataBase db = new DataBase();
        Upload.Users(db);
        Upload.Nodes(db);
        Upload.Ways(db);
        
        // load fxml file
        Parent loginGUI = FXMLLoader.load(getClass().getResource("main/edu/ufp/inf/en/resources/Login.fxml"));
        
        // set db as primaryStage data
        primaryStage.setUserData(db);
        
        // set scene
        Scene scene = new Scene(loginGUI, 700, 700);
        primaryStage.setTitle("SIU LOGIN");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    
    
    public static void main(String[] args) throws Exception {    
        launch();
    }

}
