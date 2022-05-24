import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.Toolkit;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdRandom;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.controllers.LoginController;
import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.IO.Arquive;
import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.database.way.Way;
import main.edu.ufp.inf.en.models.siu.map.Map;
import main.edu.ufp.inf.en.models.siu.user.Admin;
import main.edu.ufp.inf.en.models.siu.user.Basic;
import main.edu.ufp.inf.en.models.siu.user.User;

@SuppressWarnings("unused")
public class App extends Application {

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
        /*
        Group root = new Group(); 
        
        try {
            for (var V : db.nodesKeys()) {
                Node n = db.searchNode(V);
                float x = n.getCoordinates().getX();
                float y = n.getCoordinates().getY();
                
                x = (x - 0) * (350 - 0) / (350 - 0) + 0;
                y = (y - 0) * (350 - 0) / (350 - 0) + 0;

                root.getChildren().add(new Circle(x, y, 5f));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        //Creating a scene object 
        Scene scene = new Scene(root, WIDTH, HEIGHT);  
        //Setting title to the Stage 
        primaryStage.setTitle("Drawing a Circle"); 
            
        //Adding scene to the stage 
        primaryStage.setScene(scene); 
            
        //Displaying the contents of the stage 
        primaryStage.show();
        */
    }

    public static void main(String[] args) throws Exception {   
        
        //loading an image from a file
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final URL imageResource = App.class.getClassLoader().getResource("main/edu/ufp/inf/en/resources/icon.png");
        final Image icon = defaultToolkit.getImage(imageResource);
        final Taskbar taskbar = Taskbar.getTaskbar();

        try {
            //set icon for mac os (and other systems which do support this method)
            taskbar.setIconImage(icon);
        } catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
        } catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }

        // launch app
        launch();
    }

}
