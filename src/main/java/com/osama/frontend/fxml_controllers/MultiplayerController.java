package com.osama.frontend.fxml_controllers;

import com.osama.backend.gameplay.Constants;
import com.osama.backend.gameplay.ServerConnector;
import com.osama.backend.server.Server;
import com.osama.frontend.Main;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osama on 6/14/16.
 */
public class MultiplayerController implements Initializable {
    @FXML
    private GridPane mainPane;
    @FXML
    private VBox mainBox;

    public TextField player1Name;
    @FXML
    private Text error;
    @FXML
    private TextField serverIP;
    @FXML
    private VBox runBox;
    @FXML
    private HBox ipBox;
    @FXML
          private  HBox footer;
    @FXML
            private Button gameLaunch;
    int popupTracker = 0;
    boolean runServerCalled=false;
    Label ipLabel = new Label();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getChildren().removeAll(runBox);
        serverIP.setOnMouseClicked(event -> {
            clearStyle();
        });
    }

    public void runServer(ActionEvent actionEvent) {
        playAnimationandUpdate(mainBox, runBox, 0);
        if(!runServerCalled){
            Server server = new Server();
            server.listen();


        }
        ipLabel.setText("Give your friend this IP: " + "TODO");
        runBox.getChildren().removeAll(ipBox);
        runBox.getChildren().addAll(ipLabel);
        serverIP.setText("127.0.0.1");
        runServerCalled=true;

        popupTracker++;

    }

    public void serverRunning(ActionEvent actionEvent) {
        if(runServerCalled){
            runBox.getChildren().removeAll(ipLabel,gameLaunch);
            runBox.getChildren().addAll(ipBox,gameLaunch);
        }
        playAnimationandUpdate(mainBox, runBox, 0);

        popupTracker++;


    }

    private void playAnimationandUpdate(Node toBeAnimated, Node addNode, int updationType) {
        if (!toBeAnimated.isVisible()) {
            toBeAnimated.setVisible(true);
        }
        int time = 500;
        Duration abc = new Duration(time);
        TranslateTransition animation = new TranslateTransition(abc, toBeAnimated);
        animation.setToY(-50);
        animation.setInterpolator(Interpolator.LINEAR);
        animation.play();
        animation.setOnFinished(event -> {
            if (updationType == 0)
                mainPane.getChildren().removeAll(toBeAnimated);
                playAnimationandAdd(addNode);


        });

    }


    public void startGame(ActionEvent actionEvent) {
        Node source=(Node)actionEvent.getSource();
        Stage c=(Stage)source.getScene().getWindow();
        c.setWidth(450);
        error.setVisible(false);
        if (player1Name.getText().length() > 0) {
            Constants.player1Name = player1Name.getText();
            //Will define regex for server ip later TODO
            if(!(serverIP.getText().matches("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-" +
                    "9]|[01]?[0-9][0-9]?)"))){
                Alert x=new Alert(Alert.AlertType.ERROR);
                x.setContentText("Ip address is not valid");
                x.setHeaderText("Invalid IP");
                x.showAndWait();
                serverIP.setStyle("-fx-background-color: red;");
                return;
            }
            Constants.ServerIP = serverIP.getText();
            if(ServerConnector.serverCheck()){
                try {
                    Main.changeScene("game-view.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else{
                Alert x=new Alert(Alert.AlertType.ERROR);
                x.setHeaderText("Cannot connect to server");
                x.setContentText("Cannot connect to server. Please ensure that server is running and Server IP is correct.");
                x.showAndWait();
                serverIP.setStyle("-fx-background-color: red;");
            }

        } else {
            error.setVisible(true);
            error.setText("Please enter in the both fields.");
        }
    }

    public void popupOneBox(ActionEvent actionEvent) {
        if (popupTracker > 0) {
            System.out.println("Okay poping in");
            playAnimationandUpdate(runBox, mainBox, 0);
            popupTracker = 0;

        }
        else{
            try {
                Main.changeScene("preStart.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void playAnimationandAdd(Node a) {
        mainPane.getChildren().addAll(a);
        if(!a.isVisible())
            a.setVisible(true);
        TranslateTransition abc = new TranslateTransition(new Duration(500), a);
        abc.setToY(50);
        abc.setInterpolator(Interpolator.LINEAR);
        abc.setCycleCount(1);
        abc.play();
    }

    public void clearStyle() {
        serverIP.setStyle("-fx-background-color: transparent");
        serverIP.setText("");
    }
}
