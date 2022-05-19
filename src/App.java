import java.io.IOException;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdRandom;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.controllers.LoginController;
import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.IO.Arquive;
import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.map.Map;


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
        primaryStage.setTitle("SIU");
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
