package main.edu.ufp.inf.en.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.user.Admin;
import main.edu.ufp.inf.en.models.siu.user.User;


public class EditController implements Initializable{
    @FXML
    private Button backButton;

    @FXML
    private TextField addNode;

    @FXML
    private TextField removeNode;

    @FXML
    private TextField editNode;

    @FXML
    private TextField addWay;

    @FXML
    private TextField removeWay;

    @FXML
    private TextField editWay;

    private Stage stage;

    private Scene scene;

    private Admin user;

    public EditController (User user) {
        this.user = (Admin) user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }

    public void handleBackButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/AdminGUI.fxml"));;

        loader.setControllerFactory(c -> {
            return new AdminController(this.user);
        });
        
        // set stage and scene
        this.stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(loader.load());
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void handleAddNodeAction(ActionEvent event) {
        String data[] = this.addNode.getText().split(";");
        try {
            Node n = new Node(Integer.parseInt(data[0]), new Point(Float.parseFloat(data[1]), Float.parseFloat(data[2])));    
            if (data.length > 3) {
                Poi p = new Poi(n);
                SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
                    // iterate over remaining key value pairs of line
                    for (int i = 3; i < data.length - 1; i+=2) {
                        if (data[i].contains(":")) {
                            data[i] = data[i].replace(':', '_');
                        }
                        tags.put(Tag.valueOf(data[i].toUpperCase()), data[i + 1]);
                    }
                p.setTags(tags);
                this.user.addPoi(p);
            }
            this.user.addNode(n);
        } catch (Exception e) {
            this.addNode.setText("INVALID INPUT");
            this.addNode.selectAll();
        }
    }

    public void handleRemoveNodeAction(ActionEvent event) {
        
    }

    public void handleEditNodeAction(ActionEvent event) {
        
    }

    public void handleAddWayAction(ActionEvent event) {
        
    }

    public void handleRemoveWayAction(ActionEvent event) {
        
    }

    public void handleEditWayAction(ActionEvent event) {
        
    }


}
