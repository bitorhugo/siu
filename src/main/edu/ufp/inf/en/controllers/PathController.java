package main.edu.ufp.inf.en.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.princeton.cs.algs4.DirectedEdge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.transport.Transport;
import main.edu.ufp.inf.en.models.siu.map.Map;
import main.edu.ufp.inf.en.models.siu.user.User;

public class PathController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button goButton;

    @FXML
    private Text distanceText;
    
    @FXML
    private LineChart<Number,Number> pathGraph;

    private Map map;
    private User user;
    private Integer from; // node index
    private Integer to; // node index
    private Iterable<DirectedEdge> path;
    private double time;

    Stage stage;
    Scene scene;

    public PathController (Map map, User user, Integer from, Integer to) {
        this.map = map;
        this.user = user;
        this.from = from;
        this.to = to;
        this.time = 0.0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DecimalFormat df = new DecimalFormat("#.##");
        double dist = this.map.shortestDistance(this.map.getNodeFromIndex(from), this.map.getNodeFromIndex(to));
        this.distanceText.setText(df.format(dist) + "m");
        
        this.time = this.map.shortestDistance(
            "bus", this.map.getNodeFromIndex(from), this.map.getNodeFromIndex(to)
            );

        this.path = this.map.shortestPath(this.map.getNodes().get(from),this.map.getNodes().get(to));

        path.forEach(System.out::println);

        // create an array of series
        ArrayList<XYChart.Series<Number, Number>> seriesArr = new ArrayList<>();

        int i = 0;
        for (var v : path) {
            seriesArr.add(new XYChart.Series<>());
            
            Integer indexFrom = v.from();
            float xFrom = this.map.getNodes().get(indexFrom).getCoordinates().getX();
            float yFrom = this.map.getNodes().get(indexFrom).getCoordinates().getY();
            System.out.println("xFrom:" + xFrom + "yFrom:" + yFrom);
            seriesArr.get(i).getData().add(new XYChart.Data<>(xFrom, yFrom));
            
            Integer indexTo = v.to();
            float xTo = this.map.getNodes().get(indexTo).getCoordinates().getX();
            float yTo = this.map.getNodes().get(indexTo).getCoordinates().getY();
            System.out.println("xTo:" + xTo + "yTo:" + yTo);
            seriesArr.get(i).getData().add(new XYChart.Data<>(xTo, yTo));
            i++;
        }
    
        pathGraph.getData().addAll(seriesArr);
    }

    public void handleBackButton (ActionEvent event) throws IOException {
        // load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/Map.fxml"));
        loader.setControllerFactory(c -> {
            return new MapController(map, user);
        });
        // set stage and scene
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void handleGoButton (ActionEvent event) {
        Long ts = (System.currentTimeMillis() / 1000L); // convert milliseconds to seconds
        // add time to poi to ts
        ts = (long) (ts + time);

        // convert map indeces to poiIDs'
        // must copy iterable values to arraylist to know list size
        ArrayList<DirectedEdge> arr = new ArrayList<>();
        this.path.forEach(arr::add);

        ArrayList<Integer> poisID = new ArrayList<>();
        int i = 0; 
        for (var v : arr) {
            if (i == (arr.size() - 1)) {
                poisID.add(this.map.getNodeFromIndex(v.from()).getNodeId());
                poisID.add(this.map.getNodeFromIndex(v.to()).getNodeId());
            }
            else {
                poisID.add(this.map.getNodeFromIndex(v.from()).getNodeId());
            }
            i++;
        }

        System.out.println(poisID);

        user.visitPoi(poisID, ts);
    }

}
