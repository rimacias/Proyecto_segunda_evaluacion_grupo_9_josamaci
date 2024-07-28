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
import javafx.scene.layout.VBox;

public class CreditsController implements Initializable {

    @FXML
    private VBox vbCredits;
    @FXML
    private Label lblWinLose;
    @FXML
    private Button btMainMenu;
    @FXML
    private Button btShowRecord;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblWinLose.setText(Reader.getGameResult());
    }

    @FXML
    private void backToTheTitle(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }

    @FXML
    private void showGameRecord(ActionEvent event) throws IOException{
        App.setRoot("GameRecord");
    }

}
