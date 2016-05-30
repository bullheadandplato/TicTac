package com.osama.backend.Interface;

import com.osama.frontend.GameViewController;
import javafx.application.Platform;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

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
    public boolean createConnection() {
        try {
            s=new Socket(Constants.ServerIP,5001);
            br=new DataInputStream(s.getInputStream());
            bw=new DataOutputStream(s.getOutputStream());
            //tell the server who you are
            bw.writeUTF(GameViewController.player1Name);

        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
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
    public void run(){
        try{
            while (true){
                String command=br.readUTF();
                if(command.startsWith("wins")){
                    aController.wins(Integer.parseInt(command.substring(4,command.length())));
                }
                if(command.startsWith("name")){
                    GameViewController.player2Name=command.substring(4,command.length());

                }
                if(command.startsWith("match")){
                   aController.setGameStatus(true);
                    
                }
                if(command.startsWith("move")){
                    int box=Integer.parseInt(command.substring(4,command.length()));
                    aController.drawBox(box);
                }
                if(command.startsWith("player")){
                    int player=Integer.parseInt(command.substring(6,command.length()));
                    aController.setPlayer(player);
                }
                if(command.startsWith("quit")){
                    System.out.println("Google hss to quit this");
                    aController.otherPlayerQuit();
                }

            }

        }catch (Exception e){

        }
    }

}
