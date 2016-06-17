package com.osama.frontend;

import com.osama.backend.Interface.ServerConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osama on 6/14/16.
 */
public class PreStartController implements Initializable {
    @FXML
    private Button single;
    @FXML
    private Button multi;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        single.setOnAction(event -> {
            //TODO
        });
        multi.setOnAction(event -> {
            try {

                Main.changeScene("multiPlayer.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
