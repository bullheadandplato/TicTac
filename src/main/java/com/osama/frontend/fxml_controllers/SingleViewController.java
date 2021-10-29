package com.osama.frontend.fxml_controllers;

import com.osama.backend.gameplay.Animations;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osama on 6/26/16.
 */
public class SingleViewController  implements Initializable{
    @FXML
    private HBox statusBox;
    @FXML
    private ImageView playerIcon;
    @FXML
    private Text winCount;
    @FXML
    private Button restart;
    @FXML
    private Text winner;
    @FXML
    private HBox box;
    @FXML
    private Label playerText1;
    @FXML
    private VBox mainNode;
    @FXML
    private Label whichPlayer;
    @FXML
    private GridPane mainPane;

    private Canvas canvas;
    private SingleUIController controller;
    private int clickedX=0;
    private int clickedY=0;
    Animations animations=new Animations();

    public void reMatch(ActionEvent actionEvent) {
        //change the first turn now.
        int tmp=controller.getAIplayerID();
        controller.setAIplayerID(controller.getHumanPlayerID());
        controller.setHumanPlayerID(tmp);


        box.getChildren().removeAll(canvas);
        canvas=controller.getCanvas();
        playAnimationsatStart();
        setIcons();
        clear();
        addListener(canvas);
        if(controller.getAIplayerID()==1){
            controller.AIPlayer();
        }else{
            setClickable(true);
        }

    }

    private void clear() {

        //hide necessary things
        playerText1.setVisible(false);
        restart.setVisible(false);
        winner.setVisible(false);
        controller.clearData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller=new SingleUIController(this);
        //set Icons
        setIcons();
        canvas=controller.getCanvas();
        addListener(canvas);
    }

    private void setIcons() {
        if(controller.getAIplayerID()==2){
            playerIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/cross.png")));

        }
        else
            playerIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/zero.png")));
    }

    public void setResult(String x) {

        switch (x) {
            case "draw" -> {
                //match is drawn
                playerText1.setText("Match draw");
                whichPlayer.setVisible(true);
                winner.setText("Draw");
                winner.setVisible(true);
            }
            case "human" -> {
                playerText1.setText("You win :)");
                playerText1.setVisible(true);
                winner.setText("You wins");
                winner.setVisible(true);
            }
            case "ai" -> {
                playerText1.setText("You lose :(");
                playerText1.setVisible(true);
                winner.setText("Machine wins");
                winner.setVisible(true);
            }
        }
        restart.setVisible(true);
        playAnimationatEnd();

    }


    public void setClickable(boolean b) {
        box.setDisable(!b);
    }

    public void illegalMove() {
        Alert abc=new Alert(Alert.AlertType.ERROR);
        abc.setContentText("You clicked at illegal area. Please click at more clearer place");
        abc.setHeaderText("Illegal Move");
        abc.showAndWait();
    }
    private void playAnimationatEnd(){
        //hide the box
        animations.animateNodebyFade(box);
        //change the win count
        animations.changeMatchcount(winCount);
        animations.animateNodeByScale(playerText1);
    }
    private void playAnimationsatStart(){
        animations.cancelTransationEffectsFade(box);
        animations.cancelTransationEffectsScale(playerText1);
    }
    private void addListener(Canvas can){
        box.getChildren().addAll(can);
        canvas.setDisable(false);
        can.setOnMouseClicked(event -> {
            Platform.runLater(() -> {
                clickedX = (int) Math.round(event.getX());
                clickedY = (int) Math.round(event.getY());
                if(controller.determineMove(clickedX,clickedY)){
                    setResult(controller.getWinner());
                }
                else if(controller.getIndex()==9 &&controller.getAIplayerID()==1){
                    setResult("draw");
                }

            });
        });
    }
}

