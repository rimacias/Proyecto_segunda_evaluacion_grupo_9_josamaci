package espol.grupo_09;

import System.Reader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class SettingFirstController implements Initializable {

    @FXML
    private Pane pCat;
    @FXML
    private ToggleGroup btGroupCategory;
    @FXML
    private Button btBackToChar;
    @FXML
    private ToggleButton btYou;
    @FXML
    private ToggleButton btPC;
    @FXML
    private Button btPlayboard;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btPlayboard.setDisable(true);
        switch(Reader.getGameMode()){
            case -1: btYou.setText("PC1"); btPC.setText("PC2");
                break;
            case 0: btYou.setText("YOU"); btPC.setText("PC");
                break;
            case 1: btYou.setText("Player 1"); btPC.setText("Player 2");
                break;
        }
    }

    @FXML
    private void selectYou(ActionEvent event) {
        if (!btYou.isSelected()) {
            btPlayboard.setDisable(true);
        } else {
            Reader.getPlayer1().setIsTurn(true);
            Reader.getPlayer2().setIsTurn(false);
            btPlayboard.setDisable(false);
        }

    }

    @FXML
    private void selectPC(ActionEvent event) {
        if (!btPC.isSelected()) {
            btPlayboard.setDisable(true);
        } else {
            Reader.getPlayer1().setIsTurn(false);
            Reader.getPlayer2().setIsTurn(true);
            btPlayboard.setDisable(false);
        }

    }

    @FXML
    private void switchToPlayboard(ActionEvent event) throws IOException {
        App.setRoot("Playboard");
    }

    @FXML
    private void switchBackToChar(ActionEvent event) throws IOException {
        App.setRoot("SettingChar");
    }

}
