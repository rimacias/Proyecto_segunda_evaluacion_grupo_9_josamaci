package espol.grupo_09;

import System.Reader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class SettingCharController implements Initializable {

    @FXML
    private Pane pSettings;
    @FXML
    private ToggleButton btX;
    @FXML
    private ToggleButton btO;
    @FXML
    private Button btSettingFirst;
    @FXML
    private ToggleGroup btGroupChar;
    @FXML
    private Button btBackToGameMode;
    @FXML
    private Label lblChar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btSettingFirst.setDisable(true);
        switch(Reader.getGameMode()){
            case -1: lblChar.setText("Choose the character\nfor PC1!");
                break;
            case 0: lblChar.setText("Choose your character!");
                break;
            case 1: lblChar.setText("Choose the character\nfor the Player1!");
                break;
        }
    }

    @FXML
    private void charX(ActionEvent event) {
        if (!btX.isSelected()) {
            btSettingFirst.setDisable(true);
        } else {
            Reader.getPlayer1().setCharacter(btX.getText().charAt(0));
            Reader.getPlayer2().setCharacter(btO.getText().charAt(0));
            btSettingFirst.setDisable(false);
        }

    }

    @FXML
    private void charO(ActionEvent event) {
        if (!btO.isSelected()) {
            btSettingFirst.setDisable(true);
        } else {
            Reader.getPlayer1().setCharacter(btO.getText().charAt(0));
            Reader.getPlayer2().setCharacter(btX.getText().charAt(0));
            btSettingFirst.setDisable(false);
        }
    }

    @FXML
    private void switchToFirst(ActionEvent event) throws IOException {
        App.setRoot("SettingFirst");
    }

    @FXML
    private void switchToGameMode(ActionEvent event) throws IOException {
        App.setRoot("SettingGameMode");
    }


}
