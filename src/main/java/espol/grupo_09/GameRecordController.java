/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.grupo_09;

import System.Board;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
/**
 * FXML Controller class
 *
 * @author kevin
 */
public class GameRecordController implements Initializable {


    @FXML
    private TextArea txtMovements;
    @FXML
    private TextArea txtGeneratedTree;
    @FXML
    private Button btBackToCredits;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMovements.setEditable(false);
        txtGeneratedTree.setEditable(false);
        fillMovements();
        fillGeneratedTree();
    }    
   

    private void fillMovements() {
        String result = "";
        for(Board b: PlayboardController.boards){
            result=result+b+"\n";
        }
        txtMovements.setText(result);
    }

    private void fillGeneratedTree() {
        txtGeneratedTree.setText(PlayboardController.sTree);
        
    }

    @FXML
    private void swithToCredits(ActionEvent event) throws IOException {
        App.setRoot("Credits");
    }

}
