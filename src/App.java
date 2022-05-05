
import java.io.IOException;
import java.time.LocalDate;

import main.edu.ufp.inf.en.siu.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{

    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("main/edu/ufp/inf/en/resources/test.fxml"));
        
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main(String[] args) throws Exception {    

        launch();
        
        DataBase db = new DataBase();
        
        Admin b = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(b);
        Arquive.User(db.removeUser(b));
        Upload.Nodes(db);
        Upload.Ways(db);
        db.removeWay(db.getWaysST().get(13797641));
        db.listWays();
        
        Map map = new Map(db);
        System.out.println(map.getGraph());
    }

}
