package com.osama.backend.Interface;

import com.osama.frontend.GameViewController;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

/**
 * Created by osama on 5/25/16.
 */
public class ServerConnector extends Thread implements ServerOperations {
    Socket s;
    DataInputStream br;
    DataOutputStream bw;
    UIController a;
    ServerConnector(UIController x){
        a=x;
    }
    @Override
    public boolean createConnection() {
        try {
            s=new Socket("127.0.0.1",5001);
            br=new DataInputStream(s.getInputStream());
            bw=new DataOutputStream(s.getOutputStream());
            bw.writeUTF(GameViewController.player1Name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
        return false;

    }

    @Override
    public int arrangeMatch() {
        try {
            bw.writeUTF("match");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean drawMove(int box) {
        try {
            bw.writeUTF("move"+box);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

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
                System.out.println("Run called in ServerConnector");
                int box=br.read();
                a.drawBox(box);
                if(br.readUTF().startsWith("wins")){
                    a.wins(Integer.parseInt(br.readUTF().substring(4,br.readUTF().length())));
                }
                if(br.readUTF().startsWith("name")){
                    System.out.println("Run name 123");
                    GameViewController.player2Name=br.readUTF().substring(4,br.readUTF().length());
                }
                if(br.readUTF().equalsIgnoreCase("match")){
                    a.startGame();
                }

            }
        }catch (Exception e){

        }
    }
}
