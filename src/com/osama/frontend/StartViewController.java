package com.osama.frontend;

import com.osama.backend.Interface.Constants;
import com.osama.backend.Interface.ServerConnector;
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
            private Text error;
    @FXML
            private TextField serverIP;

    Main obj=new Main();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startGame(ActionEvent actionEvent) {
        if(serverIP.getText().length()<=0){
            serverIP.setText("169.254.215.65");
        }
            if (player1Name.getText().length() > 0 ) {
                Constants.player1Name = player1Name.getText();
                //Will define regex for server ip later TODO
                Constants.ServerIP=serverIP.getText();
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
