import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.controllers.LoginController;
import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;


public class App extends Application{

    public final double WIDTH = 700;
    public final double HEIGHT = 700;

    
    @Override
    public void start(Stage primaryStage) throws IOException {

        DataBase db = new DataBase();
        Upload.Users(db);
        Upload.Nodes(db);
        Upload.Ways(db);
        
        // load fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main/edu/ufp/inf/en/resources/Login.fxml"));
        
        // contructor injection
        loader.setControllerFactory(c -> {
            return new LoginController(db);
        });
        
        // stage config
        primaryStage.setTitle("SIU LOGIN");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    
    
    public static void main(String[] args) throws Exception {    
        launch();
    }

}
