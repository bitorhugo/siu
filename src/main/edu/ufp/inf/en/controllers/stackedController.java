package main.edu.ufp.inf.en.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class stackedController implements Initializable{
    
    @FXML
    private LineChart<Number, Number> linechart;

    @FXML
    private NumberAxis SxAxis;

    @FXML
    private NumberAxis SyAxis;

    @FXML
    private NumberAxis LxAxis;

    @FXML
    private NumberAxis LyAxis;

    @FXML
    private ScatterChart<Number, Number> scatterchart;

    public stackedController () {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setChartBounds();

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        
        for (int i = 0; i < 10; i++) {
            
            series1.getData().add(new XYChart.Data<>(i * 2, i * 2));
            series2.getData().add(new XYChart.Data<>(i * 5, i * 5));
        }

        linechart.getData().add(series1);
        scatterchart.getData().add(series2);
    }

    private void setChartBounds () {
        SxAxis.setAutoRanging(false);
        SxAxis.setLowerBound(-100);
        SxAxis.setUpperBound(100);

        SyAxis.setAutoRanging(false);
        SyAxis.setLowerBound(-100);
        SyAxis.setUpperBound(100);

        LxAxis.setAutoRanging(false);
        LxAxis.setLowerBound(-100);
        LxAxis.setUpperBound(100);

        LyAxis.setAutoRanging(false);
        LyAxis.setLowerBound(-100);
        LyAxis.setUpperBound(100);
    }
    
}
