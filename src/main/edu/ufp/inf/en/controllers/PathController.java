package main.edu.ufp.inf.en.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.models.siu.map.Map;

public class PathController {

    @FXML
    private Button backButton;
    
    @FXML
    private LineChart<Number,Number> pathGraph;

    private Map map;

    Stage stage;
    Scene scene;

    public PathController (Map map) {
        this.map = map;
    }

    public void handleBackButton (ActionEvent event) throws IOException {
        // load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/Map.fxml"));
        loader.setControllerFactory(c -> {
            return new MapController(map);
        });
        // set stage and scene
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

}
