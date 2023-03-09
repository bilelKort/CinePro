/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;


import cinerpo.services.BadgeCRUD;
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
public class StatbController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */   
    @FXML
    private BarChart<?, ?> barChart;
  
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BadgeCRUD bcd=new BadgeCRUD();
        XYChart.Series c=bcd.chart();
        barChart.getData().add(c);
    }    
        // TODO
       
    
}
