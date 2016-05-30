package com.osama.frontend;

import com.osama.backend.Interface.Drawer;
import com.osama.backend.Interface.ServerConnector;
import com.osama.backend.Interface.UIController;
import com.osama.backend.Interface.Winner;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by osama on 5/11/16.
 * This file will control the interface
 */
public class GameViewController implements Initializable {
    @FXML
    private HBox box;
    @FXML
    private Text winner;
    @FXML
    private Label whichPlayer;
    @FXML
    private Button restart;
    @FXML
    private Label playerText1;
    @FXML
    private Label playerText2;

    private Canvas canvas;
    private UIController controller;
    private int clickedX;
    private int clickedY;
    public static String player1Name;
    public volatile static String player2Name;
    private int playerID=0;
    Alert quit;

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
        if(this.playerID==2)
            setClickable(false);
        else
            setClickable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller=new UIController(this);
        restart.setText("Restart");
        startGame();
        //create alerts to show when needed;
        createAlerts();
    }

    public void setStatus(int player){
        playerText2.setVisible(false);
        playerText1.setVisible(false);
        whichPlayer.setText("Game Over");
        if(player==1){
            winner.setText("Winner: "+player1Name);
        }
        else if(player==2){
            winner.setText("Winner: "+player2Name);
        }
        else{
            winner.setText("Draw");
        }
        restart.setVisible(true);

    }

    @FXML
    public void clearEverything() {
        controller.clearGameDrawing();
        this.canvas=null;
        startGame();
    }

    public void startGame(){
        box.setVisible(false);
        whichPlayer.setText("Waiting for someone to connect");
        if(winner.getText().length()>0){
            winner.setText("");
        }
        restart.setVisible(false);
        canvas = controller.drawGame();
        box.getChildren().addAll(canvas);
        canvas.setOnMouseClicked(event -> {
            Platform.runLater(() -> {

                clickedX = (int) event.getX();
                clickedY = (int) event.getY();
                if(controller.determineMove(clickedX,clickedY)){
                    setStatus(controller.getWinner());
                }
                else if(controller.getIndex()==9){
                    setStatus(0);
                }

            });


        });


    }
    public  void  setEnable(){
        System.out.println("SETTING BOX VISIBLE");
        whichPlayer.setVisible(false);
        box.setVisible(true);
    }

    public void setClickable(boolean status){

        if(status){
            box.setDisable(false);
            playerText2.setVisible(false);
            playerText1.setVisible(true);
        }
        else{
            box.setDisable(true);
            playerText1.setVisible(false);
            playerText2.setVisible(true);
        }
    }
    private void createAlerts(){
        quit=new Alert(Alert.AlertType.INFORMATION);
        quit.setHeaderText("Other Quit");
        quit.setContentText("Other player quit.");

    }
    public void showAlert(){
        whichPlayer.setVisible(true);
        box.setVisible(false);
        quit.showAndWait();
    }
}
