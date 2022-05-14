
import java.io.IOException;
import java.time.LocalDate;

import main.edu.ufp.inf.en.siu.IO.Arquive;
import main.edu.ufp.inf.en.siu.IO.ArquiveBIN;
import main.edu.ufp.inf.en.siu.IO.Upload;
import main.edu.ufp.inf.en.siu.IO.UploadBIN;
import main.edu.ufp.inf.en.siu.database.DataBase;
import main.edu.ufp.inf.en.siu.database.poi.Poi;
import main.edu.ufp.inf.en.siu.map.Map;
import main.edu.ufp.inf.en.siu.user.*;
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
        
        Upload.Users(db);

        Arquive.Users(db);

        /*
        Upload.Nodes(db);
        db.listNodes();
        Upload.Ways(db);
        Arquive.Nodes(db);

        ArquiveBIN.NodesBIN(db);
        UploadBIN.NodesBIN(db);
        
        
        /*
        db.listNodes();
        db.listWays();
        
        Map map = new Map();
        Upload.Graph(map);
        System.out.println(map.getGraph());*/
    }

}
