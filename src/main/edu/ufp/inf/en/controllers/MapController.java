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
import main.edu.ufp.inf.en.models.siu.map.Map;

public class MapController implements Initializable{

    @FXML
    private ChoiceBox<String> menuChoiceBox;

    private String[] menuChoices = {"EDIT", "HISTORY", "EXPORT", "LOGOUT"};

    @FXML
    private LineChart<Number,Number> mapGraph;

    private Stage stage;
    private Scene scene;
    private Parent loginGUI;

    private Map map;

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
        loginGUI = FXMLLoader.load(getClass().getResource("../resources/Login.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loginGUI, 700, 700);
        stage.setScene(scene);
        stage.show();
    }

}
