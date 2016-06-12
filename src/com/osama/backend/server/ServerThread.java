package com.osama.backend.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by osama on 5/24/16.
 */
public class ServerThread extends Thread {
    Socket socket;
    static final String matchTAG="match";
    static final String moveTAG="move";
    static final String WINTAG="wins";
    private static ConcurrentHashMap<Socket,String> clientNames=new ConcurrentHashMap<>();
    static List<Socket> connectedClients=new ArrayList<>();
    static List<Socket> busyClients=new ArrayList<>();
    DataInputStream bufferedReader;
    DataOutputStream bufferedWriter;
    static List<GamePlay> onGoingMatches=new ArrayList<>();
    private Random random=new Random();
    ServerThread(Socket socket){
        this.socket=socket;
        connectedClients.add(socket);

        start();
    }
    public void run(){
        try{
            bufferedReader=new DataInputStream(socket.getInputStream());
            bufferedWriter=new DataOutputStream(socket.getOutputStream());
            while (true){
                    String command=bufferedReader.readUTF();
                    if(!clientNames.containsKey(socket)){
                        clientNames.put(socket,command);
                    }
                    if(command.startsWith(matchTAG)){
                        arrangeMatch();

                    }
                    else if(command.startsWith(moveTAG)){
                        for (GamePlay s:
                                onGoingMatches) {
                            if(s.player1==this.socket){
                                s.move(Integer.parseInt(command.substring(4,command.length())),socket);
                            }
                            if(s.player2==this.socket){

                                s.move(Integer.parseInt(command.substring(4,command.length())),socket);
                            }
                        }
                }
                else if(command.startsWith(WINTAG)){
                        int sum=Integer.parseInt(command.substring(4,command.length()));
                        for (GamePlay s:
                                onGoingMatches){
                            if(s.player1==socket){
                                s.wins(socket,sum);
                            }
                            else if(s.player2==socket){
                                s.wins(socket,sum);
                            }
                        }
                    }
                else if(command.startsWith("rematch")){
                        for (GamePlay player:
                             onGoingMatches) {
                            if(player.player1==socket){
                                player.reMatch(player.player2);
                            }else if(player.player2==socket){
                                player.reMatch(player.player1);
                            }
                        }
                    }
                else if(command.startsWith("startmatchagain")){
                        for (GamePlay pl :
                                onGoingMatches) {
                            if (pl.player1==socket){
                                pl.startMatchAgain();
                            }else if(pl.player2==socket){
                                pl.startMatchAgain();
                            }
                        }
                    }
                }
        }catch (Exception e){
            int i=0;
            for (Socket a:
                 connectedClients) {
                if(a==socket){
                    connectedClients.remove(i);
                    System.out.println("Client removed");
                    break;
                }
                i++;
            }
            if(i==0){
                i=0;
                for (Socket a:
                     busyClients) {
                    if(a==socket){
                        for (GamePlay player:
                             onGoingMatches) {
                            if(player.player2==socket){
                                player.writePlayerQuit(socket);
                                busyClients.remove(player.player1);
                                busyClients.remove(player.player2);
                                clientNames.remove(player.player2);
                                connectedClients.add(player.player1);
                                onGoingMatches.remove(player);
                                player=null;
                                break;
                            }else if(player.player1==socket){
                                player.writePlayerQuit(socket);
                                busyClients.remove(player.player2);
                                busyClients.remove(player.player1);
                                clientNames.remove(player.player2);
                                connectedClients.add(player.player2);
                                onGoingMatches.remove(player);
                                player=null;
                                break;
                            }
                        }
                        System.out.println("Client remove");
                        break;
                    }
                    i++;
                }
            }
        }
    }
    private void arrangeMatch(){
        if (connectedClients.size() >= 2) {
            int one = random.nextInt(connectedClients.size());
            Socket a = connectedClients.get(one);
            busyClients.add(a);
            connectedClients.remove(one);
            int two = random.nextInt(connectedClients.size());
            Socket b = connectedClients.get(two);
            busyClients.add(b);
            connectedClients.remove(two);
                GamePlay abc=new GamePlay(a,b);
                onGoingMatches.add(abc);
            abc.writeName(clientNames.get(a),a);
            abc.writeName(clientNames.get(b),b);
            abc.startMatch();
        }


    }


}
