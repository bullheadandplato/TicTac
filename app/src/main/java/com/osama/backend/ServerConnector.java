package com.osama.backend;

import android.app.Activity;


import java.io.*;
import java.net.Socket;

/**
 * Created by osama on 5/25/16.
 */
public class ServerConnector extends Thread implements ServerOperations{
    Socket s;
    DataInputStream br;
    DataOutputStream bw;
    UIController aController;
    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

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
            s=new Socket(Constants.serverIP,Constants.serverPort);
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
            s=new Socket(Constants.serverIP,5001);
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
                final  String  command=br.readUTF();
                if(command.startsWith("wins")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            aController.wins(Integer.parseInt(command.substring(4,command.length())));

                        }
                    });
                }
                else if(command.startsWith("name")){

                            Constants.player2Name=command.substring(4,command.length());


                }
                else if(command.startsWith("match")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            aController.setGameStatus(true);

                        }
                    });

                }
                else if(command.startsWith("move")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int box=Integer.parseInt(command.substring(4,command.length()));
                            aController.drawBox(box);
                        }
                    });
                }
               else if(command.startsWith("player")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int player = Integer.parseInt(command.substring(6, command.length()));
                            aController.setPlayer(player);
                        }
                    });

                }
                else if(command.startsWith("quit")){
                   activity.runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           aController.otherPlayerQuit();

                       }
                   });
                }
                else if (command.startsWith("rematch")){
                       activity.runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               aController.reMatch();
                           }
                       });

                }
                else if(command.startsWith("closing")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            aController.serverDown();
                        }
                    });
                }
                else if(command.startsWith("draw")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            aController.matchDrawnfromServer();
                        }
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
            a=new Socket(Constants.serverIP,Constants.serverPort);
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
