package com.osama.frontend;

import com.osama.backend.Interface.Drawer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
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
                clickedX = (int) event.getX();
                clickedY = (int) event.getY();
                if (clickedX < Box1X && clickedY < Box1Y) {

                    drawer.drawImage(1,togglePlayer());
                } else if (clickedX < Box1X && clickedY < Box2Y) {
                    drawer.drawImage(2,togglePlayer());

                } else if (clickedX < Box1X && clickedY < Box3Y) {
                    drawer.drawImage(3,togglePlayer());

                } else if (clickedX < Box2X && clickedY < Box1Y) {
                    drawer.drawImage(4,togglePlayer());

                } else if (clickedX < Box2X && clickedY < Box2Y) {
                    drawer.drawImage(5,togglePlayer());
                } else if (clickedX < Box2X && clickedY < Box3Y) {
                    drawer.drawImage(6,togglePlayer());
                } else if (clickedX < Box3X && clickedY < Box1Y) {
                    drawer.drawImage(7,togglePlayer());
                } else if (clickedX < Box3X && clickedY < Box2Y) {
                    drawer.drawImage(8,togglePlayer());
                } else if (clickedX < Box3X && clickedY < Box3Y) {
                    drawer.drawImage(9,togglePlayer());
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
}
