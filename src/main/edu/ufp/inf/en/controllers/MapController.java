package main.edu.ufp.inf.en.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.map.Map;
import main.edu.ufp.inf.en.models.siu.user.Admin;
import main.edu.ufp.inf.en.models.siu.user.User;

public class MapController implements Initializable{

    public final double WIDTH = 700;
    public final double HEIGHT = 700;

    @FXML 
    private Button backButton;

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField toTextField;

    @FXML
    private Text invalidText;

    @FXML
    private ScatterChart<Number, Number> scatterChart;

    @FXML
    private NumberAxis SxAxis;

    @FXML
    private NumberAxis SyAxis;


    private Stage stage;
    private Scene scene;

    private Map map;
    private User user;

    public MapController () {}

    public MapController (Map map, User user) {
        this.map = map;
        this.user = user;
    }

    public MapController (Map map) {
        this.map = map;
    }

    public MapController (User user) {
        this(new Map(user.getDb()));
        this.user = user;
    }
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        drawScatter();

        setChartBounds();

    }

    @FXML
    public void handleBackButton (ActionEvent event) throws IOException {
        FXMLLoader loader;

        if (this.user instanceof Admin) {
            loader = new FXMLLoader(getClass().getResource("../resources/AdminGUI.fxml"));
            loader.setControllerFactory(c -> {
                return new AdminController(this.user);
            });
        }
        else {
            loader = new FXMLLoader(getClass().getResource("../resources/BasicGUI.fxml"));
            loader.setControllerFactory(c -> {
                return new BasicController(this.user);
            });
        }
        
        // set stage and scene
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(loader.load());
        this.stage.setScene(scene);
        this.stage.show();
    }

    @FXML
    public Integer handleFromTextField (ActionEvent event) {
        return Integer.parseInt(fromTextField.getText()); 
        
    }

    @FXML
    public Integer handleToTextField (ActionEvent event) {
        return Integer.parseInt(toTextField.getText()); 
    }

    @FXML
    public void handlePathButton(ActionEvent event) {
        try {
            invalidText.setText("");
            Integer from = handleFromTextField(event);
            Integer to = handleToTextField(event);

            switchToPathScene(event, from, to);

            
        } catch (Exception e) {
            invalidText.setText("INVALID");
            System.out.println("Invalid input for path");
        }
        
    }

    public void switchToPathScene (ActionEvent event, Integer from, Integer to) throws IOException {
        // load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/Path.fxml"));
        loader.setControllerFactory(c -> {
            return new PathController(map, user, from, to);
        });
        // set stage and scene
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    private void drawScatter() {
        Series<Number, Number> nodeSeries = new Series<>(createData().get(0));
        Series<Number, Number> poiSeries = new Series<>(createData().get(1));
        
        nodeSeries.setName("Node");
        poiSeries.setName("Point of Interest");
        
        scatterChart.getData().add(nodeSeries);
        scatterChart.getData().add(poiSeries);
    }

    private List<ObservableList<Data<Number, Number>>> createData() {
        List<ObservableList<Data<Number, Number>>> returnList = new ArrayList<>();
        
        var nodeList = FXCollections.<Data<Number, Number>>observableArrayList();
        var poiList = FXCollections.<Data<Number, Number>>observableArrayList();

        // iterate over this map indeces and spilt nodes from poi
        for (var v : this.map.indeces()) {
            Point point = this.map.getNodes().get(v).getCoordinates();
            var data = new Data<Number, Number>(point.getX(), point.getY());
            if (this.map.getNodes().get(v) instanceof Poi) { // collect nodes
                nodeList.add(data);
                data.setNode(createDataNodeSymbol(v));
            } 
            else {
                poiList.add(data); // collect pois
                data.setNode(createDataPoiSymbol(v));
            } 
        }

        // add nodeList and poiList to list to return
        returnList.add(nodeList);
        returnList.add(poiList);

        return returnList;
    }

    private Node createDataNodeSymbol(int id) {
        var label = new Label();
        label.textProperty().set(String.valueOf(id));

        var pane = new Pane(label);
        pane.setShape(new Circle(4.0));
        pane.setScaleShape(false);

        label.translateYProperty().bind(label.heightProperty().divide(-1.5));

        return pane;
    }

    private Node createDataPoiSymbol(int id) {
        var label = new Label();
        label.textProperty().set(String.valueOf(id));

        var pane = new Pane(label);
        pane.setShape(new Rectangle(8.0, 8.0));
        pane.setScaleShape(false);

        label.translateYProperty().bind(label.heightProperty().divide(-1.5));

        return pane;
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
        double padding = 150;

        SxAxis.setAutoRanging(false);
        SxAxis.setLowerBound(xmin - padding/2);
        SxAxis.setUpperBound(xmax + padding/2);

        SyAxis.setAutoRanging(false);
        SyAxis.setLowerBound(ymin - padding);
        SyAxis.setUpperBound(ymax + padding);
    }

}
