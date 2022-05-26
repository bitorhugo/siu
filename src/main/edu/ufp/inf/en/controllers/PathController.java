package main.edu.ufp.inf.en.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.princeton.cs.algs4.DirectedEdge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
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

    @FXML
    private NumberAxis LxAxis;

    @FXML
    private NumberAxis LyAxis;

    @FXML
    private ScatterChart<Number, Number> nodesGraph;

    @FXML
    private NumberAxis SxAxis;

    @FXML
    private NumberAxis SyAxis;

    private Map map;
    private User user;
    private Integer from; // node index
    private Integer to; // node index
    private Iterable<DirectedEdge> path;
    private Iterable<Long>time;

    Stage stage;
    Scene scene;

    public PathController (Map map, User user, Integer from, Integer to) {
        this.map = map;
        this.user = user;
        this.from = from;
        this.to = to;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        displayDistanceToDestination();
            
        calculatePathDistance();
    
        drawScatterChart();
    
        drawLineChart();
            
        this.setChartBounds();
        
    
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
        
        List<Long> timestamps = new ArrayList<>();
        timestamps.add(ts);
        
        this.time.forEach(t -> {
            long timestamp = t += ts;
            timestamps.add(timestamp);
        });
        
        // must copy iterable values to arraylist to know list size
        ArrayList<DirectedEdge> arr = new ArrayList<>();
        this.path.forEach(arr::add);

        // save each route node to poisID
        ArrayList<Integer> poisID = new ArrayList<>();
        int i = 0; 
        for (var v : arr) {
            // convert map indeces to poiIDs
            if (i == (arr.size() - 1)) {
                poisID.add(this.map.getNodeFromIndex(v.from()).getNodeId());
                poisID.add(this.map.getNodeFromIndex(v.to()).getNodeId());
            }
            else {
                poisID.add(this.map.getNodeFromIndex(v.from()).getNodeId());
            }
            i++;
        }
    
        user.visitPoi(poisID, timestamps);
        
    }

    private void displayDistanceToDestination () {
        // calculate total distance
        DecimalFormat df = new DecimalFormat("#.##");
        double dist = this.map.shortestDistance(this.map.getNodeFromIndex(from), this.map.getNodeFromIndex(to));
        this.distanceText.setText(df.format(dist) + "m");
    }

    private void calculatePathDistance () {
        // calculate shortest path
        this.path = this.map.shortestPath(this.map.getNodes().get(from),this.map.getNodes().get(to));

        // calculate time it takes to travel each edge
        this.time = this.map.shortestPathTime(path, Transport.BUS);

        this.path.forEach(System.out::println); // debug purposes
    }

    private void setChartBounds () {

        double xmin = 0;
        double xmax = 0;
        double ymin = 0; 
        double ymax = 0;

        // iterate over coordinates to find upper and lower bounds
        for (var index : this.map.indeces()) {
            Point coordinates = this.map.getNodeFromIndex(index).getCoordinates();
            if (coordinates.getX() > xmax) xmax = coordinates.getX();
            if (coordinates.getX() < xmin) xmin = coordinates.getX();
            if (coordinates.getY() > ymax) ymax = coordinates.getY();
            if (coordinates.getY() < ymin) ymin = coordinates.getY();
        }

        // set a padding for chart
        double padding = 50;

        SxAxis.setAutoRanging(false);
        SxAxis.setLowerBound(xmin - padding);
        SxAxis.setUpperBound(xmax + padding);

        SyAxis.setAutoRanging(false);
        SyAxis.setLowerBound(ymin - padding);
        SyAxis.setUpperBound(ymax + padding);

        LxAxis.setAutoRanging(false);
        LxAxis.setLowerBound(xmin - padding);
        LxAxis.setUpperBound(xmax + padding);

        LyAxis.setAutoRanging(false);
        LyAxis.setLowerBound(ymin - padding);
        LyAxis.setUpperBound(ymax + padding);
    }

    private void drawScatterChart () {
        XYChart.Series<Number, Number> nodeSeries = new XYChart.Series<>();
        XYChart.Series<Number, Number> poiSeries = new XYChart.Series<>();
        for (var v : this.map.indeces()) { // get nodes
            Point point = this.map.getNodes().get(v).getCoordinates();
            if (this.map.getNodes().get(v) instanceof Poi) {
                poiSeries.getData().add(new XYChart.Data<>(point.getX(), point.getY()));
            }
            else {
                nodeSeries.getData().add(new XYChart.Data<>(point.getX(), point.getY()));
            }
        }
        nodesGraph.getData().add(nodeSeries);
        nodesGraph.getData().add(poiSeries);
        // set label names
        nodeSeries.setName("Nodes");
        poiSeries.setName("Point of Interest");
    }

    private void drawLineChart () {
        // create pathSeries containing all the edges to connect in chart
        ArrayList<XYChart.Series<Number, Number>> pathSeries = new ArrayList<>();

        int i = 0;
        for (var v : this.path) { // get path
            pathSeries.add(new XYChart.Series<>());
            
            Integer indexFrom = v.from();
            float xFrom = this.map.getNodes().get(indexFrom).getCoordinates().getX();
            float yFrom = this.map.getNodes().get(indexFrom).getCoordinates().getY();
            System.out.println("xFrom:" + xFrom + "yFrom:" + yFrom);
            pathSeries.get(i).getData().add(new XYChart.Data<>(xFrom, yFrom));
            
            Integer indexTo = v.to();
            float xTo = this.map.getNodes().get(indexTo).getCoordinates().getX();
            float yTo = this.map.getNodes().get(indexTo).getCoordinates().getY();
            System.out.println("xTo:" + xTo + "yTo:" + yTo);
            pathSeries.get(i).getData().add(new XYChart.Data<>(xTo, yTo));
            i++;
        }
        
        // add data to chart        
        pathGraph.getData().addAll(pathSeries);
    }


}
