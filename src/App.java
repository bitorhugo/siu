
import java.io.IOException;
import java.time.LocalDate;

import main.edu.ufp.inf.en.siu.IO.Upload;
import main.edu.ufp.inf.en.siu.database.DataBase;
import main.edu.ufp.inf.en.siu.map.Map;
import main.edu.ufp.inf.en.siu.user.Admin;
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
        
        //launch();

        DataBase db = new DataBase();
        
        Admin b = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(b);
        Upload.Nodes(db);
        Upload.Ways(db);
        db.listNodes();
        db.listWays();
        
        Map map = new Map();
        Upload.Graph(map);
        System.out.println(map.getGraph());
    }

}
