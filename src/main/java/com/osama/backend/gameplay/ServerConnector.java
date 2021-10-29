package com.osama.backend.gameplay;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by osama on 5/25/16.
 */
public class ServerConnector extends Thread implements ServerOperations{
    Socket s;
    DataInputStream br;
    DataOutputStream bw;
    UIController aController;


    /**
     * It will set the controller class instance
     * because I have to callback some methods.
     * @param a is the instance of controller
     */
    public void setaController(UIController a) {
        aController=a;
    }

    /**
     * Connect to server by calling this method
     * @return true if connection is established.
     */
    @Override
    public boolean createConnectionStart() {
        try {
            s=new Socket(Constants.ServerIP,5001);
            br=new DataInputStream(s.getInputStream());
            bw=new DataOutputStream(s.getOutputStream());
            //tell the server who you are
            bw.writeUTF(Constants.player1Name);

        } catch (IOException e) {
            return false;
        }
        start();
        return true;

    }

    @Override
    public boolean createConnection() {
        try {
            s=new Socket(Constants.ServerIP,5001);
            br=new DataInputStream(s.getInputStream());
            bw=new DataOutputStream(s.getOutputStream());
            //tell the server who you are
            bw.writeUTF(Constants.player1Name);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Ask server to arrangeMatch this is called whenever a user connect to server
     * It is necessary for user to call this method
     * @return
     */
    @Override
    public int arrangeMatch() {
        try {
            bw.writeUTF("match");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * When a user click any box this method is called to draw move on the other side.
     * i.e If player one clicks this method will tell the server to draw one the player two
     * game.
     * @param box is the number of box which player clicks
     * @return true if move successfully drawn
     */
    @Override
    public boolean drawMove(int box) {
        try {
            bw.writeUTF("move"+box);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param winBoxes
     */
    @Override
    public void wins(int[] winBoxes) {
        try {
            int sum=0;
            if(winBoxes[0]==0 && winBoxes[2]==2)
                sum=3;
            else if(winBoxes[0]==3 && winBoxes[2]==5)
                sum=13;
            else if(winBoxes[0]==6 && winBoxes[2]==8)
                sum=21;
            else if(winBoxes[0]==0 && winBoxes[2]==6)
                sum=9;
            else if(winBoxes[0]==1 && winBoxes[2]==7)
                sum=12;
            else if(winBoxes[0]==2 && winBoxes[2]==8)
                sum=15;
            else if(winBoxes[0]==0 && winBoxes[2]==8)
                sum=14;
            else if(winBoxes[0]==2 && winBoxes[2]==6)
                sum=16;
            bw.writeUTF("wins"+sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reMatch() {
        try {
            bw.writeUTF("rematch");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startMatchAgain() {
        try {
            bw.writeUTF("startmatchagain");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            while (true){
                String command=br.readUTF();
                if(command.startsWith("wins")){
                    Platform.runLater(()->{
                        aController.wins(Integer.parseInt(command.substring(4,command.length())));
                    });
                }
                else if(command.startsWith("name")){

                            Constants.player2Name=command.substring(4,command.length());


                }
                else if(command.startsWith("match")){
                    Platform.runLater(()->{
                        aController.setGameStatus(true);
                    });

                }
                else if(command.startsWith("move")){
                    int box=Integer.parseInt(command.substring(4,command.length()));
                    Platform.runLater(()->{
                        aController.drawBox(box);

                    });
                }
               else if(command.startsWith("player")){
                    int player=Integer.parseInt(command.substring(6,command.length()));
                    Platform.runLater(()->{
                        aController.setPlayer(player);
                    });
                }
                else if(command.startsWith("quit")){
                    Platform.runLater(()->{
                        aController.otherPlayerQuit();
                    });
                }
                else if (command.startsWith("rematch")){
                    Platform.runLater(()->{
                        aController.reMatch();

                    });
                }
                else if(command.startsWith("closing")){
                    Platform.runLater(()->{
                        aController.serverDown();
                    });
                }
                else if(command.startsWith("draw")){
                    Platform.runLater(()->{
                        aController.matchDrawnfromServer();
                    });
                }
                else if(command.startsWith("chat")){
                    Platform.runLater(()->{
                        aController.writeRecievedMessage(command.substring(4,command.length()));
                    });
                }

            }

        }catch (Exception e){

        }
    }
    public void closeConnection(){
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean serverCheck(){
        Socket a;
        try{
            a=new Socket(Constants.ServerIP,5001);
            a.close();
        }catch (IOException e){
            return false;
        }finally {
            a=null;
        }
        return true;
    }

    public void writeDraw() {
        try{
            bw.writeUTF("draw");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void writeMessage(String message){
        try{
            bw.writeUTF(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
