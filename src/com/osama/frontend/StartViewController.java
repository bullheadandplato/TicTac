package com.osama.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osama on 5/13/16.
 */
public class StartViewController implements Initializable {
    @FXML
    public TextField player1Name;
    @FXML
    private TextField player2Name;
    @FXML
            private Text error;

    Main obj=new Main();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startGame(ActionEvent actionEvent) {
            if (player1Name.getText().length() > 0 && player2Name.getText().length() > 0) {
                GameViewController.player2Name = player2Name.getText();
                GameViewController.player1Name = player1Name.getText();
                try {
                    obj.changeScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        else{
                error.setText("Please enter the name of both players.");
            }
        }
}