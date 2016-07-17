package com.osama.frontend;

import com.osama.backend.server.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        root= FXMLLoader.load(Main.class.getResource("fxml/preStart.fxml"));
        Scene scene=new Scene(root,400,500);
        stage.setScene(scene);
        stage.setTitle("Tic Tac");
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(event->{
            if(Server.isRunning()){
                Server.close();
            }
            System.exit(0);
        });
    }
    public static void changeScene(String formName) throws IOException{
        root=FXMLLoader.load(Main.class.getResource("fxml/"+formName));
        stage.getScene().setRoot(root);
    }
    public static void main(String[] args){

        launch(args);
    }
}
