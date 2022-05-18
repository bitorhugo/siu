package main.edu.ufp.inf.en.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.models.siu.IO.Upload;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.map.Map;

public class MapController implements Initializable{

    public final double WIDTH = 700;
    public final double HEIGHT = 700;

    @FXML
    private ChoiceBox<String> menuChoiceBox;

    private String[] menuChoices = {"EDIT", "HISTORY", "EXPORT", "LOGOUT"};

    @FXML
    private LineChart<Number,Number> mapGraph;

    private Stage stage;
    private Scene scene;
    private Parent loginGUI;

    private Map map;
    private DataBase database;

    public MapController () {}

    public MapController (Map map) {
        this.map = map;
    }

    public MapController (DataBase database) {
        this.database = database;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set string values for menu options
        menuChoiceBox.getItems().addAll(menuChoices);
        menuChoiceBox.setOnAction(choice -> {
            try {
                getMenuOption(choice);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.map = new Map();
        
        
    }

    public void getMenuOption (ActionEvent event) throws IOException {
        String selectedOption = menuChoiceBox.getValue();

        switch(selectedOption) {
            case "EDIT": handleEditChoice(event);
                break;
            case "HISTORY": handleHistoryChoice(event);
                break;
            case "EXPORT": handleExportChoice(event);
                break;
            case "LOGOUT": handleLogoutChoice(event);
                break;
            default:
                break;
        }

    }

    public void handleEditChoice (ActionEvent event) {

    }

    public void handleHistoryChoice (ActionEvent event) {

    }

    public void handleExportChoice (ActionEvent event) {

    }

    public void handleLogoutChoice (ActionEvent event) throws IOException {
        // load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/Login.fxml"));
        // inject constructor into controller
        loader.setControllerFactory(c -> {
            return new LoginController(this.database);
        });
        // set stage and scene
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

}
