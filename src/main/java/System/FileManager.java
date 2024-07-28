/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author kevin
 */
public class FileManager {

    public static void saveGame(Game game) throws FileNotFoundException, IOException {
        try {
            ObjectOutputStream charger = new ObjectOutputStream(new FileOutputStream("src/main/resources/savedata/game.bin"));
            charger.writeObject(game.getResult());
            charger.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("File not loaded");
        }
    }

    public static String loadGame() {
        try {
            ObjectInputStream loader = new ObjectInputStream(new FileInputStream("src/main/resources/savedata/game.bin"));
            String a = (String) loader.readObject();
            loader.close();
            return a;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("File not loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("File corrupted");
        }
        return null;
    }

    public static void checkLastSave() throws IOException {
        if (FileManager.loadGame() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hey!");
            alert.setHeaderText("The last game wasn't finished, wanna reload it?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                System.out.println("Loading last save...");
                System.out.println(FileManager.loadGame());
            }
        }

    }
}
