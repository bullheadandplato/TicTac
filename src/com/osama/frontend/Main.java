package com.osama.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by osama on 5/11/16.
 */
public class Main extends Application {
    private static Stage stage;
    static Parent root;
    public Main(){
        stage=new Stage();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        root= FXMLLoader.load(Main.class.getResource("StartView.fxml"));
        Scene scene=new Scene(root,400,400);
        stage.setScene(scene);
        stage.setTitle("Tic Tac");
        stage.show();
    }
    public static void changeScene() throws IOException{
        root=FXMLLoader.load(Main.class.getResource("GameView.fxml"));
        stage.getScene().setRoot(root);
    }
    public static void main(String[] args){
        launch(args);
    }
}
