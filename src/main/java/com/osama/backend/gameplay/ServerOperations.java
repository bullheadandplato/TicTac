package com.osama.backend.gameplay;

/**
 * Created by osama on 5/25/16.
 */
public interface ServerOperations {
    public boolean createConnectionStart();
    public boolean createConnection();
    public int arrangeMatch();
    public boolean drawMove(int box);


    public void wins(int[] winBoxes);


    void reMatch();

    void startMatchAgain();
}
