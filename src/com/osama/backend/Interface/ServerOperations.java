package com.osama.backend.Interface;

/**
 * Created by osama on 5/25/16.
 */
public interface ServerOperations {
    public boolean createConnection();
    public int arrangeMatch();
    public boolean drawMove(int box);


    public void wins(int[] winBoxes);


}
