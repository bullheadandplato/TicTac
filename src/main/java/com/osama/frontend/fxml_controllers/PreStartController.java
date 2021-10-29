package com.osama.frontend.fxml_controllers;

import com.osama.frontend.Main;
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
            try{
                Main.changeScene("single-mode.fxml");
            }catch (IOException e){
                e.printStackTrace();
            }
        });
        multi.setOnAction(event -> {
            try {

                Main.changeScene("multiplayer-layout.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
