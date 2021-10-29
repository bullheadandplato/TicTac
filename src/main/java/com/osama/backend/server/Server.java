package com.osama.backend.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by osama on 5/24/16.
 */
public class Server extends Thread{
   private static ServerSocket serverSocket;
   private ServerThread serverThread;
    private static List<ServerThread> serverThreadList=new ArrayList<>();
    private static boolean running=false;
   public void listen(){
        try {
            serverSocket=new ServerSocket(5001);
            running=true;
            start();

        } catch (IOException e) {
            serverSocket=null;
        }

    }
    public void run(){
        try{
            while (true){
                while (true){
                    Socket s=serverSocket.accept();
                    System.out.println("Client connected");
                    serverThread=new ServerThread(s);
                    serverThreadList.add(serverThread);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException{
        System.out.println("Server is running....");
        Server a=new Server();
        a.listen();


    }
    public static boolean isRunning(){
        return running;
    }
    public static void close(){
        for (ServerThread s :
             serverThreadList) {
            s.writeCloseMessage();
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
