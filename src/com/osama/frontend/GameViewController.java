package com.osama.frontend;

import com.osama.backend.Interface.*;
import com.sun.javafx.tk.Toolkit;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;


import javax.swing.*;
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
    private Text showPlayerName;
    @FXML
    private ImageView playerIcon;
    @FXML
    private Text winCount;
    @FXML
    private VBox chatBox;
    @FXML
    private SplitPane splitPane;
    @FXML
    private  TextField chatTextArea;
    @FXML
    private ScrollPane chatList;
    @FXML
    private Button chatButton;
    @FXML
    private Button closeChatButton;
@FXML
private Button sendMessage;
    @FXML
    private VBox chatListBox;

    private Canvas canvas;
    private UIController controller;
    private int clickedX;
    private int clickedY;
    private int playerID=0;
    private Alert quit;
    private int win=0;
    private int totalGames=0;
    private static final int maxTextlength=9;
    private boolean isChatOpened=false;
    private Animations anim=new Animations();

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
        if(this.playerID==2)
            setClickable(false);
        else
            setClickable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restart.setText("Restart");
        restart.setVisible(false);
        controller=new UIController(this);
        showPlayerName.setText(Constants.player1Name);
        showPlayerName.setFill(Color.DODGERBLUE);
        winCount.setFill(Color.DEEPPINK);
        winCount.setText(win+"/"+totalGames);
        whichPlayer.setText("Waiting for someone to connect");
        sendMessage.setDisable(true);
        //create alerts to show when needed;
        createAlerts();
        splitPane.getItems().removeAll(chatBox);
        chatTextArea.textProperty().addListener(lis->{
            if(chatTextArea.getText().length()>maxTextlength){
                chatTextArea.setText(chatTextArea.getText(0,chatTextArea.getText().length()-1));

            }
            if(chatTextArea.getText().length()>0){
                sendMessage.setDisable(false);
            }
            else {
                sendMessage.setDisable(true);
            }
        });
        chatTextArea.setOnMouseClicked(event->{
            chatTextArea.clear();
        });

            }

    public void setStatus(int player){
        whichPlayer.setVisible(true);
        whichPlayer.setTextFill(Color.RED);
        whichPlayer.setText("Game Over");
        winner.setVisible(true);
        if(player==1){
            winner.setText("Winner: "+ Constants.player1Name);
            playerText1.setText("You Win :)");
            anim.animateNodebyFade(box);
            anim.animateNodeByScale(playerText1);
            win++;
            winCount.setText(win+"/"+totalGames);
            anim.changeMatchcount(winCount);
        }

        else if(player==2){
            winner.setText("Winner: "+Constants.player2Name);
            playerText1.setText("You lose :(");
            anim.animateNodebyFade(box);
            anim.animateNodeByScale(playerText1);
        }
        else{
            winner.setText("Draw");
            playerText1.setVisible(false);
        }
        restart.setVisible(true);

    }

    @FXML
    public void clearEverything() {
        box.getChildren().remove(canvas);
        controller.clearGameDrawing();
        this.canvas=null;
        playerText1.setVisible(false);
        whichPlayer.setVisible(true);
        playerIcon.setImage(null);

    }

    public void startGame(){
        whichPlayer.setVisible(true);
        if(winner.getText().length()>0){
            winner.setText("");
        }
        box.setVisible(true);
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
                    controller.matchDraw();
                    setStatus(0);
                }

            });


        });

    }
    public  void  setEnable(){
        chatButton.setDisable(false);
        totalGames++;
        winCount.setText(win+"/"+totalGames);
        whichPlayer.setTextFill(Color.GREEN);
        whichPlayer.setText(Constants.player1Name+ " vs "+ Constants.player2Name);
        box.getChildren().remove(canvas);
        playerText1.setVisible(true);
        if(playerID==2){
            playerIcon.setImage(new Image("images/zero.png"));
        }
        else{
            playerIcon.setImage(new Image("images/cross.png"));
        }
        startGame();

    }

    public void setClickable(boolean status){
        if(status){
            box.setDisable(false);
            playerText1.setTextFill(Color.GREEN);
            playerText1.setText("Your Turn");

        }
        else{
            box.setDisable(true);
            playerText1.setTextFill(Color.RED);
            playerText1.setText("Opponent's Turn");
        }
    }
    private void createAlerts(){
        quit=new Alert(Alert.AlertType.INFORMATION);
        quit.setHeaderText("Other Quit");
        quit.setContentText("Other player quit.");

    }
    public void showAlert(){
        box.setVisible(false);
        whichPlayer.setText("Waiting for someone to connect");
        whichPlayer.setTextFill(Color.DARKSALMON);
        restart.setVisible(false);
        winner.setVisible(false);
        quit.showAndWait();
        clearChat();
        clearEverything();
    }

    private void clearChat() {
        chatButton.setDisable(true);
        closeChatButton.fire();

    }

    @FXML
    public void reMatch() {
        clearEverything();
        controller.writeRematch();
        Alert x=new Alert(Alert.AlertType.INFORMATION);
        x.setContentText("Waiting for other player response");
        x.setHeaderText("waiting for response");
        x.showAndWait();
        if(x.getResult()==ButtonType.OK){
            restart.setVisible(false);
            winner.setVisible(false);
            anim.cancelTransationEffectsScale(playerText1);
            anim.cancelTransationEffectsFade(box);
        }

    }

    public void illegalMove() {
        Alert abc=new Alert(Alert.AlertType.ERROR);
        abc.setContentText("You clicked at illegal area. Please click at more clearer place");
        abc.setHeaderText("Illegal Move");
        abc.showAndWait();
    }

    public void reArrangeMatch() {
        Alert abc=new Alert(Alert.AlertType.CONFIRMATION);
        abc.setHeaderText("Want rematch?");
        abc.setContentText("Player 2 want rematch. Yes/NO");
        abc.showAndWait();
        if(abc.getResult()== ButtonType.OK){
            clearEverything();
            controller.startReMatch();
            anim.cancelTransationEffectsScale(playerText1);
            anim.cancelTransationEffectsFade(box);

        }
        else {
            System.out.println("Google is okay");
        }
    }

    public void resetCount(ActionEvent actionEvent) {
        totalGames=0;
        win=0;
        winCount.setText(totalGames+"/"+win);
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void alertServerDown() {
        Alert x=new Alert(Alert.AlertType.INFORMATION);
        x.setContentText("Server shut down");
        x.setHeaderText("Server down");
        x.showAndWait();
        try {
            Main.changeScene("preStart.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openChat(ActionEvent actionEvent) {
        isChatOpened=true;
        Node source=(Node)actionEvent.getSource();
        Stage c=(Stage)source.getScene().getWindow();
        c.setWidth(600);
        splitPane.setDividerPositions(.30);
        splitPane.getItems().addAll(chatBox);
        chatBox.setVisible(true);
        FadeTransition x=new FadeTransition(new Duration(500),chatBox);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.play();
    }

    public void sendMessage() {
        String message=chatTextArea.getText();
        Label temp=new Label(message);
        temp.setPadding(new Insets(5,5,5,5));
        temp.setStyle("-fx-background-color: green; -fx-background-radius: 10px; -fx-text-fill: white;-fx-font-size: 12px;");
        chatListBox.getChildren().addAll(temp);
        //send message now
        controller.sendMessagetoServer("chat"+message);
        chatTextArea.clear();


    }

    public void messageRecieved(String message) {
        if(!isChatOpened){
            chatButton.fire();
        }
        HBox fix=new HBox();
        fix.setAlignment(Pos.CENTER_RIGHT);
        Label temp=new Label(message);
        fix.getChildren().addAll(temp);
        temp.setPadding(new Insets(5,5,5,5));
        temp.setStyle("-fx-background-color: lightgrey; -fx-background-radius: 10px; -fx-text-fill: white;-fx-font-size: 12px;");
       chatListBox.getChildren().addAll(fix);
    }

    public void closeChat(ActionEvent actionEvent) {
        isChatOpened=false;
        FadeTransition x=new FadeTransition(new Duration(500),chatBox);
        x.setFromValue(100);
        x.setToValue(0);
        x.setCycleCount(1);
        x.play();
        x.setOnFinished(event -> {
            Node source=(Node)actionEvent.getSource();
            Stage c=(Stage)source.getScene().getWindow();
            splitPane.getItems().removeAll(chatBox);
            c.setWidth(450);

        });

    }
}
