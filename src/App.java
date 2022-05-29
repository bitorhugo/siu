import java.io.IOException;

import java.net.URL;

import java.awt.Image;
import java.awt.Taskbar;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.edu.ufp.inf.en.controllers.LoginController;
import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;


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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main/edu/ufp/inf/en/resources/login.fxml"));

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
