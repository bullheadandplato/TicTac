package com.osama.frontend;

import com.osama.backend.Interface.Drawer;
import com.osama.backend.Interface.UIController;
import com.osama.backend.Interface.Winner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    private Canvas canvas;
    private UIController controller;

    private int clickedX;
    private int clickedY;
    public static String player1Name;
    public static String player2Name;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startGame();
    }



    public void setStatus(int player){
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
        restart.setText("Restart");

    }

    @FXML
    public void clearEverything() {
        controller.clearGameDrawing();
        this.canvas=null;
        startGame();
    }
    private void startGame(){
        whichPlayer.setText(player1Name+"'s Turn");
        if(winner.getText().length()>0){
            winner.setText("");
        }
        if (controller!=null){
            controller.clearGameDrawing();
            controller=null;
        }
        controller=new UIController();
        restart.setVisible(false);
        canvas = controller.drawGame();
        box.getChildren().clear();
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
                else{
                    if(controller.getPlayer()==2){
                        whichPlayer.setText(player1Name+"'s Turn");
                    }
                    else{
                        whichPlayer.setText(player2Name+"'s Turn");
                    }
                }
            });


        });
    }
}
