package com.osama.frontend;

import com.osama.backend.Interface.Drawer;
import com.osama.backend.Interface.UIController;
import com.osama.backend.Interface.Winner;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.jetbrains.annotations.Contract;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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

    private Canvas canvas;
    private UIController controller;

    private int clickedX;
    private int clickedY;
    public static String player1Name;
    public static String player2Name;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller=new UIController();
        canvas = controller.drawGame();
        box.getChildren().addAll(canvas);
        canvas.setOnMouseClicked(event -> {
            Platform.runLater(() -> {
                clickedX = (int) event.getX();
                clickedY = (int) event.getY();
                if(controller.determineMove(clickedX,clickedY)){
                    setWinner(controller.getWinner());
                }
            });


        });
    }



    public void setWinner(int player){
        if(player==1){
            winner.setText("Winner: "+player1Name);
        }
        else{
            winner.setText("Winner: "+player2Name);
        }


    }

}
