package com.osama.backend;

import android.app.Activity;

/**
 * Created by osama on 9/19/16.
 * the backend handler for getting moves
 */
public class UIController {

    ServerConnector connector;

    public UIController(Activity act){
        connector=new ServerConnector();
        connector.setActivity(act);
    }
    public void wins(int i) {
    }

    public void setGameStatus(boolean b) {
    }

    public void drawBox(int box) {

    }

    public void setPlayer(int player) {
    }

    public void otherPlayerQuit() {
    }

    public void reMatch() {
    }

    public void serverDown() {
    }

    public void matchDrawnfromServer() {
    }
    public void drawMove(int box){

    }
}
