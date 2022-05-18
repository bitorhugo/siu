package main.edu.ufp.inf.en.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.map.Map;

public class MapController implements Initializable{

    public final double WIDTH = 700;
    public final double HEIGHT = 700;

    @FXML
    private ChoiceBox<String> menuChoiceBox;

    private String[] menuChoices = {"EDIT", "HISTORY", "EXPORT", "LOGOUT"};

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField toTextField;

    @FXML
    private Text invalidText;

    @FXML
    private ScatterChart<Number, Number> nodesMap;

    private Stage stage;
    private Scene scene;

    private Map map;
    private DataBase database;

    public MapController () {}

    public MapController (Map map) {
        this.map = map;
    }

    public MapController (DataBase database) {
        this(new Map(database));
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

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        for (var v : this.map.getNodes().keys()) {
            Point point = this.map.getNodes().get(v).getCoordinates();
            series1.getData().add(new XYChart.Data<>(point.getX(), point.getY()));
        }
        nodesMap.getData().add(series1);
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

    public Integer handleFromTextField (ActionEvent event) {
        return Integer.parseInt(fromTextField.getText()); 
        
    }

    public Integer handleToTextField (ActionEvent event) {
        return Integer.parseInt(toTextField.getText()); 
    }

    public void handleGOButton(ActionEvent event) {
        try {
            invalidText.setText("");
            Integer from = handleFromTextField(event);
            Integer to = handleToTextField(event);

            switchToPathScene(event);

            System.out.println("FROM: " + from);
            System.out.println("TO: " + to);
            System.out.println("GO");
        } catch (Exception e) {
            invalidText.setText("INVALID");
        }
        
    }

    public void switchToPathScene (ActionEvent event) throws IOException {
        // load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/Path.fxml"));
        loader.setControllerFactory(c -> {
            return new PathController(map);
        });
        // set stage and scene
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

}
