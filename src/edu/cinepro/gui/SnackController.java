/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class SnackController implements Initializable {

    private File f;
    @FXML
    private Button ftimage;
    @FXML
    private Label labelsinglefile;
    @FXML
    private TextField idnom;
    @FXML
    private TextField idprix;
    @FXML
    private TextField idquantite;
    @FXML
    private TextField idstock;
    @FXML
    private Button idbuttonsavesnack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void importimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers image", "*.png", "*.jpg", "*.gif", "*.bmp"));
        f = fc.showOpenDialog(null);
        if (f != null) {
            labelsinglefile.setText("Select file" + f.getAbsolutePath());
            System.out.println(f.getAbsolutePath());
        }

    }

    @FXML
    private void savesnack(ActionEvent event) {
        boolean conditionnom = false;
        boolean conditionprix = false;
        boolean conditionQuantite = false;
        boolean conditionurl = false;

        String resNom = idnom.getText();
        String resPrix = idprix.getText();
        String resQuantite = idquantite.getText();

        if (resNom.equals("")) {
            System.out.println("il faut entrer un nom");

        } else {
            conditionnom = true;
        }

        if (resPrix.equals("")) {
            System.out.println("il faut entrer prix");

        } else {
            try {
                float restPrixF = Integer.valueOf(resPrix);
                System.out.println(restPrixF);
                conditionprix = true;
            } catch (NumberFormatException ex) {
                System.out.println("il faut entrer un nombre valide ");
            }
        }

        if (resQuantite.equals("")) {
            System.out.println("il faut entrer la quantité");

        } else {
            try {
                int restquantiteF = Integer.valueOf(resQuantite);
                conditionQuantite = true;
                System.out.println(restquantiteF);
            } catch (NumberFormatException ex) {
                System.out.println("il faut entrer un nombre valide ");
            }
        }

        if (labelsinglefile.getText().equals("URL:")) {
            System.out.println("il faut entrer un url");

        } else {
            conditionurl = true;
        }

        if ((conditionQuantite) && (conditionnom) && (conditionprix) && (conditionurl)) {
            System.out.println("ok");
        }
    }

}
