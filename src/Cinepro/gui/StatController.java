/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

import cinerpo.services.AbonnementCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class StatController implements Initializable {

    @FXML
    private BarChart<?, ?> barChart;

    /**
     * Initializes the controller class.
     */
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AbonnementCRUD acd=new AbonnementCRUD();
        XYChart.Series c=acd.chart();
        System.out.println(c);
        barChart.getData().add(c);
    }    
    
}
