package com.osama.backend.Interface;

import org.jetbrains.annotations.Contract;

/**
 * Created by osama on 6/26/16.
 */
public class SharedData {


    private int[] clickedBoxes = new int[9];
    private boolean notWinner = true;

    public void setClickedBoxes(int index,int clickedBoxes) {
        this.clickedBoxes[index] = clickedBoxes;
    }

    public void setNotWinner(boolean notWinner) {
        this.notWinner = notWinner;
    }

    public boolean isNotWinner() {

        return notWinner;
    }

    @Contract(pure = true)
    public boolean isClicked(int boxNumber) {
        boolean status = false;
        for (int a :
                clickedBoxes) {
            if (a == boxNumber) {
                status = true;
            }
        }
        return status;
    }
    public int[] getClickedBoxes() {
        return clickedBoxes;
    }
    public void setClickedBoxesasArray(int[] a){
        this.clickedBoxes=a;
    }
    public SharedData(){

    }
}
