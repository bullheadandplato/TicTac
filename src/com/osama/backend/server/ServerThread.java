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
                        busyClients.remove(i);
                        clientNames.remove(socket);
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
            //write names of each other
           try {
                DataOutputStream temp=new DataOutputStream(a.getOutputStream());
                temp.writeUTF("name"+clientNames.get(b));
                temp=null;
                temp=new DataOutputStream(b.getOutputStream());
                temp.writeUTF("name"+clientNames.get(a));

               temp=null;
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

}
