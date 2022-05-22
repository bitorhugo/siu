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
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.edu.ufp.inf.en.models.lp2._1_intro.geometric_figures.Point;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.map.Map;
import main.edu.ufp.inf.en.models.siu.user.Admin;
import main.edu.ufp.inf.en.models.siu.user.User;

public class MapController implements Initializable{

    public final double WIDTH = 700;
    public final double HEIGHT = 700;

    @FXML
    private ChoiceBox<String> menuChoiceBox;

    private String[] adminMenuChoices = {"EDIT", "HISTORY", "EXPORT", "LOGOUT"};
    private String[] basicMenuChoices = {"HISTORY", "EXPORT", "LOGOUT"};

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
        Map newMap = new Map(this.map, Tag.HIGHWAY);
        // set string values for menu options
        if (this.user instanceof Admin) menuChoiceBox.getItems().addAll(this.adminMenuChoices);
        else menuChoiceBox.getItems().addAll(basicMenuChoices);

        menuChoiceBox.setOnAction(choice -> {
            try {
                getMenuOption(choice);
            } catch (IOException e) {
                System.out.println("Not able to select choice from menu");
                e.printStackTrace();
            }
        });

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        for (var v : this.map.indeces()) {
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

    public void handleEditChoice (ActionEvent event) throws IOException {
        // load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/Edit.fxml"));
        // inject constructor into controller
        loader.setControllerFactory(c -> {
            return new EditController(this.user);
        });
        // set stage and scene
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void handleHistoryChoice (ActionEvent event) throws IOException {
    // load fxml
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/History.fxml"));
    // inject constructor into controller
    loader.setControllerFactory(c -> {
        return new HistoryController(this.user);
    });
    // set stage and scene
    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(loader.load());
    stage.setScene(scene);
    stage.show();
    }

    public void handleExportChoice (ActionEvent event) {

    }

    public void handleLogoutChoice (ActionEvent event) throws IOException {
        // load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/Login.fxml"));
        // inject constructor into controller
        loader.setControllerFactory(c -> {
            return new LoginController(user.getDb());
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

    public void handlePathButton(ActionEvent event) {
        try {
            invalidText.setText("");
            Integer from = handleFromTextField(event);
            Integer to = handleToTextField(event);

            switchToPathScene(event, from, to);

            
        } catch (Exception e) {
            invalidText.setText("INVALID");
            e.printStackTrace();
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

}
