package main.edu.ufp.inf.en.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import main.edu.ufp.inf.en.models.siu.database.way.Way;
import main.edu.ufp.inf.en.models.siu.user.Admin;
import main.edu.ufp.inf.en.models.siu.user.Basic;
import main.edu.ufp.inf.en.models.siu.user.User;


public class EditController implements Initializable {

    private static final String INVALID = "INVALID";
    private static final String VALIDATED = "VALIDATED";

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

    @FXML
    private TextField addUser;

    @FXML
    private TextField removeUser;

    @FXML
    private TextField editUser;

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
            else {
                this.user.addNode(n);
            }
            this.addNode.setText(VALIDATED);
            this.addNode.selectAll();
        } catch (Exception e) {
            this.addNode.setText(INVALID);
            this.addNode.selectAll();
        }
    }

    public void handleRemoveNodeAction(ActionEvent event) {
        String data[] = this.removeNode.getText().split(";");
        Integer id = Integer.parseInt(data[0]);
        try {
            if (data.length > 3) {
                this.user.removePoi(id);
            }
            else {
                this.user.removeNode(id);
            }
            this.user.getDb().updateWays(id);
            this.removeNode.setText(VALIDATED);
            this.removeNode.selectAll();
        } catch (Exception e) {
            this.removeNode.setText(INVALID);
            this.removeNode.selectAll();
        }
    }

    public void handleEditNodeAction(ActionEvent event) {
        String data[] = editNode.getText().split(";");
       try {
           Point p = new Point (Integer.valueOf(data[1]), Integer.valueOf(data[2]));
           user.editNode(Integer.valueOf(data[0]), p);
           editNode.setText(VALIDATED);
           editNode.selectAll();
       } catch (Exception e) {
           editNode.setText(INVALID);
           editNode.selectAll();
       }
    }

    public void handleAddWayAction(ActionEvent event) {
        String data[] = this.addWay.getText().split(";");
        try {
            Way w = new Way(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Double.parseDouble(data[3]));
                    // check to see if line contains key-value pairs
                    if (data.length > 4) {
                        SeparateChainingHashST<Tag, String> tags = new SeparateChainingHashST<>();
                        // iterate over remaining key value pairs of line
                        for (int i = 4; i < data.length - 1; i+=2) {
                            if (data[i].contains(":")) {
                                data[i] = data[i].replace(':', '_');
                            }
                            tags.put(Tag.valueOf(data[i].toUpperCase()), data[i + 1]);
                        }
                        w.setTags(tags);
                    }
                    user.addWay(w);
            this.addWay.setText(VALIDATED);
            this.addWay.selectAll();
        } catch (Exception e) {
            this.addWay.setText(INVALID);
            this.addWay.selectAll();
        }      
    }

    public void handleRemoveWayAction(ActionEvent event) {
        String data[] = this.removeWay.getText().split(";");
        Integer id = Integer.parseInt(data[0]);
        try {
            this.user.removeWay(id);
            this.removeWay.setText(VALIDATED);
            this.removeWay.selectAll();
        } catch (Exception e) {
            this.removeWay.setText(INVALID);
            this.removeWay.selectAll();
        }
    }

    public void handleEditWayAction(ActionEvent event) {
        String data[] = editWay.getText().split(";");
        try {
            int wayID = Integer.valueOf(data[0]);
            Node origin = this.user.getDb().searchNode(Integer.valueOf(data[1]));
            Node destination = this.user.getDb().searchNode(Integer.valueOf(data[2]));
            double weight = Double.valueOf(data[3]);
            this.user.editWay(wayID, origin, destination, weight);
            editWay.setText(VALIDATED);
            editWay.selectAll();
        } catch (Exception e) {
            e.getMessage();
            editWay.setText(INVALID);
            editWay.selectAll();
        }
    }

    public void handleAddUserAction(ActionEvent event) {
        String []data = addUser.getText().split(";");
        try {
            String type = data[0].toUpperCase();
            String name = data[1];
            String address = data[2];
            String idNumber = data[3];
            String birthday = data[4];
            String email = data[5];
            String password = data[6];
            String []birthData = birthday.split("-");
            LocalDate birth = LocalDate.of(Integer.valueOf(birthData[2]), Integer.valueOf(birthData[1]), Integer.valueOf(birthData[0]));
            if (type.equals("ADMIN")) {
                user.addUser(new Admin(name, address, idNumber, birth, email, password));
            }
            if (type.equals("BASIC")) {
                user.addUser(new Basic(name, address, idNumber, birth, email, password));
            }
            addUser.setText(VALIDATED);
            addUser.selectAll();
        } catch (Exception e) {
            addUser.setText(INVALID);
            addUser.selectAll();
        }
    }

    public void handleRemoveUserAction(ActionEvent event) {
        String data[] = removeUser.getText().split(";");
        try {
            this.user.removeUser(data[0]);
            removeUser.setText(VALIDATED);
            removeUser.selectAll();
        } catch (Exception e) {
            e.getMessage();
            removeUser.setText(INVALID);
            removeUser.selectAll();
        }
    }

    public void handleEditUserAction(ActionEvent event) {
        String data[] = removeUser.getText().split(";");
        try {
            String userID = data[0];
            String name = data[1];
            String address = data[2];
            String birthday = data[3];
            String []birthData = birthday.split("-");
            LocalDate birth = LocalDate.of(Integer.valueOf(birthData[2]), Integer.valueOf(birthData[1]), Integer.valueOf(birthData[0]));
            this.user.editUser(userID, name, address, birth);
            editUser.setText(VALIDATED);
            editUser.selectAll();
        } catch (Exception e) {
            e.getMessage();
            editUser.setText(INVALID);
            editUser.selectAll();
        }
    }

}
