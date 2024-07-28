package espol.grupo_09;

import System.FileManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainMenuController implements Initializable {

    @FXML
    private BorderPane bpMenu;
    @FXML
    private Button btPlay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void switchToGameMode(ActionEvent event) throws IOException {
        App.setRoot("SettingGameMode");
    }

}
