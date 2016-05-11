package com.osama.frontend;

import com.osama.backend.Interface.Drawer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import org.jetbrains.annotations.Contract;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osama on 5/11/16.
 * This file will control the interface
 */
public class MainViewController implements Initializable {
    @FXML
    private HBox box;
    private Canvas canvas;
    private Drawer drawer;
    private int[] clickedBoxes=new int[9];
    private int index=0;
    private int clickedX;
    private int clickedY;
    private static final int Box1X = 40;
    private static final int Box1Y = 40;
    private static final int Box2Y = 130;
    private static final int Box3Y = 190;
    private static final int Box2X = 130;
    private static final int Box3X = 190;
    private static int player=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawer = new Drawer();
        canvas = drawer.drawGame();
        box.getChildren().addAll(canvas);
        canvas.setOnMouseClicked(event -> {
            Platform.runLater(() -> {
                if(index<=clickedBoxes.length){
                    clickedX = (int) event.getX();
                    clickedY = (int) event.getY();
                    if (clickedX < Box1X && clickedY < Box1Y) {
                        if(!isClicked(1)){
                            clickedBoxes[index++]=1;
                            drawer.drawImage(1,togglePlayer());
                        }
                    } else if (clickedX < Box1X && clickedY < Box2Y) {
                        if(!isClicked(2)){
                            clickedBoxes[index++]=2;
                            drawer.drawImage(2,togglePlayer());
                        }

                    } else if (clickedX < Box1X && clickedY < Box3Y) {
                        if(!isClicked(3)){
                            clickedBoxes[index++]=3;
                            drawer.drawImage(3,togglePlayer());

                        }

                    } else if (clickedX < Box2X && clickedY < Box1Y) {
                        if(!isClicked(4)){
                            clickedBoxes[index++]=4;
                            drawer.drawImage(4,togglePlayer());

                        }

                    } else if (clickedX < Box2X && clickedY < Box2Y) {
                        if(!isClicked(5)){
                            clickedBoxes[index++]=5;
                            drawer.drawImage(5,togglePlayer());

                        }
                    } else if (clickedX < Box2X && clickedY < Box3Y) {
                        if(!isClicked(6)){
                            clickedBoxes[index++]=6;
                            drawer.drawImage(6,togglePlayer());
                        }
                    } else if (clickedX < Box3X && clickedY < Box1Y) {
                        if(!isClicked(7)){
                            clickedBoxes[index++]=7;
                            drawer.drawImage(7,togglePlayer());
                        }
                    } else if (clickedX < Box3X && clickedY < Box2Y) {
                        if(!isClicked(8)){
                            clickedBoxes[index++]=8;
                            drawer.drawImage(8,togglePlayer());

                        }
                    } else if (clickedX < Box3X && clickedY < Box3Y) {
                        if(!isClicked(9)){
                            clickedBoxes[index++]=9;
                            drawer.drawImage(9,togglePlayer());
                        }
                    }
                }
                else{
                    //do nothing yet
                }
            });


        });
    }
    private int togglePlayer(){
        if(player==0){
            player=1;
        }
        else if(player==1){
            player=2;
        }
        else if(player==2){
            player=1;
        }
        return player;
    }
    @Contract(pure = true)
    private boolean isClicked(int boxNumber){
        boolean status=false;
        for (int a:
             clickedBoxes) {
            if(a==boxNumber){
                status=true;
            }
        }
        return status;
    }
}
