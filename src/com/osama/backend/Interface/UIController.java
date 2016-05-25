package com.osama.backend.Interface;

import javafx.scene.canvas.Canvas;
import org.jetbrains.annotations.Contract;

/**
 * Created by osama on 5/13/16.
 * Controls the interface explicitly.
 */
public class UIController {
    private int[] clickedBoxes = new int[9];
    private static final int Box1X = 40;
    private static final int Box1Y = 40;
    private static final int Box2Y = 130;
    private static final int Box3Y = 190;
    private static final int Box2X = 130;
    private static final int Box3X = 190;
    private int player = 0;
    private boolean notWinner=true;
    private Drawer drawer;
    private int index = 0;

    public UIController(){
        drawer=new Drawer();
    }
    public boolean determineMove(int clickedX,int clickedY){
        boolean status=false;
        if (index <= clickedBoxes.length && notWinner) {

            if (clickedX < Box1X && clickedY < Box1Y) {
                if (!isClicked(-1)) {    //because by default array is initialized to 0 so can't use zero
                    clickedBoxes[index++] = -1;
                    drawer.drawImage(0, togglePlayer());
                    if(Winner.isWinner(0,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status=true;

                    }
                }
            } else if (clickedX < Box2X && clickedY < Box1Y) {
                if (!isClicked(1)) {
                    clickedBoxes[index++] = 1;
                    drawer.drawImage(1, togglePlayer());
                    if (Winner.isWinner(1, player)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status=true;

                    }
                }
            }else if (clickedX < Box3X && clickedY < Box1Y) {
                if (!isClicked(2)) {
                    clickedBoxes[index++] = 2;
                    drawer.drawImage(2, togglePlayer());
                    if(Winner.isWinner(2,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status=true;

                    }

                }
            } else if (clickedX < Box1X && clickedY < Box2Y) {
                if (!isClicked(3)) {
                    clickedBoxes[index++] = 3;
                    drawer.drawImage(3, togglePlayer());
                    if(Winner.isWinner(3,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status=true;

                    }

                }
            } else if (clickedX < Box2X && clickedY < Box2Y) {
                if (!isClicked(4)) {
                    clickedBoxes[index++] = 4;
                    drawer.drawImage(4, togglePlayer());
                    if(Winner.isWinner(4,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status=true;

                    }


                }
            } else if (clickedX < Box3X && clickedY < Box2Y) {
                if (!isClicked(5)) {
                    clickedBoxes[index++] = 5;
                    drawer.drawImage(5, togglePlayer());
                    if(Winner.isWinner(5,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status=true;

                    }

                }
            } else if (clickedX < Box1X && clickedY < Box3Y) {
                if (!isClicked(6)) {
                    clickedBoxes[index++] = 6;
                    drawer.drawImage(6, togglePlayer());
                    if(Winner.isWinner(6,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status=true;

                    }

                }

            } else if (clickedX < Box2X && clickedY < Box3Y) {
                if (!isClicked(7)) {
                    clickedBoxes[index++] = 7;
                    drawer.drawImage(7, togglePlayer());
                    if(Winner.isWinner(7,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status=true;

                    }

                }
            } else if (clickedX < Box3X && clickedY < Box3Y) {
                if (!isClicked(8)) {
                    clickedBoxes[index++] = 8;
                    drawer.drawImage(8, togglePlayer());
                    if(Winner.isWinner(8,player)){
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status=true;

                    }

                }
            }
        } else {
            //do nothing yet
        }
        return status;
    }
    private int togglePlayer() {
        if (player == 0) {
            player = 1;
        } else if (player == 1) {
            player = 2;
        } else if (player == 2) {
            player = 1;
        }
        return player;
    }

    @Contract(pure = true)
    private boolean isClicked(int boxNumber) {
        boolean status = false;
        for (int a :
                clickedBoxes) {
            if (a == boxNumber) {
                status = true;
            }
        }
        return status;
    }
    public Canvas drawGame(){
        return drawer.drawGame();
    }
    public int getWinner(){
        notWinner=false;
        return player;
    }
    public int getPlayer(){
        return player;
    }

    public int getIndex() {
        return index;
    }

    public void clearGameDrawing(){
        Winner.resetAll();
        notWinner=true;
        player=0;
        clickedBoxes=null;
    }

}
