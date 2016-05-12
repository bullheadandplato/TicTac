package com.osama.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.applet.Applet;

/**
 * Created by osama on 5/11/16.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(Main.class.getResource("MainView.fxml"));
        Scene scene=new Scene(root,400,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac");
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
