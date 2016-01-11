package seriestracker.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import seriestracker.Main;

import java.io.IOException;

/**
 * Created by rkorff on 1/8/2016.
 */
public class mainController {
    @FXML
    private BorderPane root;

    @FXML
    private void editGenre() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/editGenreView.fxml"));
            AnchorPane editGenre = (AnchorPane) loader.load();

            root.setCenter(editGenre);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
