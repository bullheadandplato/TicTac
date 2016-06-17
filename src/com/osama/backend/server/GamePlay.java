package com.osama.backend.server;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by osama on 5/25/16.
 */
public class GamePlay {
    Socket player1;
    Socket player2;
    private int player1id;
    private int player2id;
    private DataOutputStream bw1;
    private DataOutputStream bw2;
    public GamePlay(Socket player1,Socket player2){
        this.player1=player1;
        this.player2=player2;
        try{
           bw1=new DataOutputStream(player1.getOutputStream());
            bw2=new DataOutputStream(player2.getOutputStream());


        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void move(int box,Socket player){
            try {
                if(player==player1) {
                    bw2.writeUTF("move"+box);
                }
                else if(player==player2){
                    bw1.writeUTF("move"+box);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    public void wins(Socket player,int winSum){
        try{
            if(player==player1){
                bw2.writeUTF("wins"+winSum);
            }
            else if(player==player2){
                bw1.writeUTF("wins"+winSum);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setPlayerIDs(){
        if(player1id==1){
            player1id=2;
            player2id=1;
        }
        else{
            player1id=1;
            player2id=2;
        }

        try{
            bw1.writeUTF("player"+player1id);
            bw2.writeUTF("player"+player2id);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void writePlayerQuit(Socket player){
        if(player==player1){
            try {
                bw2.writeUTF("quit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(player==player2){
            try {
                bw1.writeUTF("quit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void startMatch(){
        try{            setPlayerIDs();

            bw2.writeUTF("match");
            bw1.writeUTF("match");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void writeName(String name, Socket player){
        if(player==player1)
            try {
                bw2.writeUTF("name"+name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        else if(player==player2){
            try {
                bw1.writeUTF("name"+name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void reMatch(Socket player1) {
        if(player1==this.player1){
            try {
                bw1.writeUTF("rematch");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(player1==player2){
            try {
                bw2.writeUTF("rematch");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startMatchAgain() {
        startMatch();
    }

    public void writeDraw(Socket player1) {
        if(player1==this.player1){
            try {
                bw1.writeUTF("draw");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(player1==player2){
            try {
                bw2.writeUTF("draw");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
