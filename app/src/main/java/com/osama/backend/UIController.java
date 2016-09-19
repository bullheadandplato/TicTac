package com.osama.backend;

import android.app.Activity;

import com.example.asad.tictoe.Controller;

/**
 * Created by osama on 9/19/16.
 * the backend handler for getting moves
 */
public class UIController {

    ServerConnector connector;
    Controller manageInterface;
    private int player;
    private Boolean gameStatus;
    public UIController(Activity act,Controller con){
        connector=new ServerConnector();
        connector.setActivity(act);
        //try to start match
        connector.arrangeMatch();

        manageInterface=con;
    }
    public void wins(int i) {
    }

    public void setGameStatus(boolean b) {
        this.gameStatus=b;
        check();
    }

    private void check() {
        if(gameStatus){
            manageInterface.setEnable();
        }
    }

    public void drawBox(int box) {
    }

    public void setPlayer(int player) {
        this.player=player;
        manageInterface.setPlayerID(player);
    }

    public void otherPlayerQuit() {
    }

    public void reMatch() {
    }

    public void serverDown() {
    }

    public void matchDrawnfromServer() {
    }
}
