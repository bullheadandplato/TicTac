package com.osama.backend.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by osama on 5/24/16.
 */
public class Server {
   ServerSocket serverSocket;
    private void listen(){
        try {
            serverSocket=new ServerSocket(5001);
            while (true){
                while (true){
                    Socket s=serverSocket.accept();
                    System.out.println("Client connected");
                    new ServerThread(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException{
        System.out.println("Server is running....");
        Server a=new Server();
        a.listen();


    }
}
