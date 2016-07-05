package com.osama.frontend;

import com.osama.backend.Interface.Constants;
import com.osama.backend.Interface.Drawer;
import com.osama.backend.Interface.SharedData;
import com.osama.backend.Interface.Winner;
import javafx.scene.canvas.Canvas;

import java.util.Random;

/**
 * Created by osama on 6/26/16.
 */
public class SingleUIController {
    private int index=0;
    private String winner;
    private Drawer drawer;
    private SingleViewController manageInterface;
    private SharedData data;
    private  int AIplayerID=2;
    private boolean lastCalledMoved=false;

    public void setHumanPlayerID(int humanPlayerID) {
        this.humanPlayerID = humanPlayerID;
    }

    public void setAIplayerID(int AIplayerID) {
        this.AIplayerID = AIplayerID;
    }

    public int getHumanPlayerID() {
        return humanPlayerID;
    }

    public int getAIplayerID() {
        return AIplayerID;
    }

    private int  humanPlayerID=1;

    public void setNotClickedBoxes(int nowClicked) {
        for (int i = 0; i <notClickedBoxes.length ; i++) {
            if(notClickedBoxes[i]==nowClicked){
                //swap it wih the last element
                int lastIndex=notClickedBoxes.length-1;
                int tmp=notClickedBoxes[lastIndex];
                notClickedBoxes[lastIndex]=nowClicked;
                notClickedBoxes[i]=tmp;
                //create a new array of size notClickedBoxes.length -1
                int[] x=new int[notClickedBoxes.length-1];
                //copy the contents of notClickedBoxes into new array
                for (int j = 0; j <x.length ; j++) {
                    x[j]=notClickedBoxes[j];
                }
                //assign the new array to notClickedBoxes.
                //All the hustle is for shrinking the array size by 1 i.e deleting the last element previously swaped.
                notClickedBoxes=x;
                break;
            }
        }

    }

    private int[] notClickedBoxes;
    public SingleUIController(SingleViewController x){
        drawer=new Drawer();
        manageInterface=x;
        data=new SharedData();
        notClickedBoxes= new int[]{-1, 1, 2, 3, 4, 5, 6, 7, 8};
    }
    public Canvas getCanvas(){
        return drawer.drawGame();
    }

    public boolean isLastCalledMoved() {
        return lastCalledMoved;
    }

    public boolean determineMove(int clickedX, int clickedY) {
        boolean status = false;
        if (index <= data.getClickedBoxes().length && data.isNotWinner()) {
            winner="human";
            lastCalledMoved=true;
            if (clickedX < Constants.Box1X && clickedY < Constants.Box1Y) {
                if (!data.isClicked(-1) ){    //because by default array is initialized to 0 so can't use zero
                    manageInterface.setClickable(false);
                    data.setClickedBoxes(index++,-1);
                    setNotClickedBoxes(-1);
                    drawer.drawImage(0, humanPlayerID);
                    if (Winner.isWinner(0, humanPlayerID)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status = true;

                    }
                    if(!status && index!=9)
                        AIPlayer();
                }
            } else if (clickedX < Constants.Box2X && clickedY < Constants.Box1Y) {
                if (!data.isClicked(1)) {
                    data.setClickedBoxes(index++,1);
                    drawer.drawImage(1, humanPlayerID);
                    manageInterface.setClickable(false);
                    setNotClickedBoxes(1);

                    if (Winner.isWinner(1, humanPlayerID)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status = true;

                    }
                    if(!status && index!=9)
                        AIPlayer();
                }
            } else if (clickedX < Constants.Box3X && clickedY < Constants.Box1Y) {
                if (!data.isClicked(2)) {
                    manageInterface.setClickable(false);
                    data.setClickedBoxes(index++,2);
                    drawer.drawImage(2, humanPlayerID);
                    setNotClickedBoxes(2);


                    if (Winner.isWinner(2, humanPlayerID)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status = true;

                    }
                    if(!status && index!=9)
                        AIPlayer();

                }
            } else if (clickedX < Constants.Box1X && clickedY < Constants.Box2Y) {
                if (!data.isClicked(3)) {
                    manageInterface.setClickable(false);
                    data.setClickedBoxes(index++,3);
                    drawer.drawImage(3, humanPlayerID);
                    setNotClickedBoxes(3);


                    if (Winner.isWinner(3, humanPlayerID)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status = true;

                    }
                    if(!status && index!=9)
                        AIPlayer();

                }
            } else if (clickedX < Constants.Box2X && clickedY < Constants.Box2Y) {
                if (!data.isClicked(4)) {
                    manageInterface.setClickable(false);
                    data.setClickedBoxes(index++,4);
                    drawer.drawImage(4, humanPlayerID);
                    setNotClickedBoxes(4);


                    if (Winner.isWinner(4, humanPlayerID)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status = true;

                    }
                    if(!status && index!=9)
                        AIPlayer();


                }
            } else if (clickedX < Constants.Box3X && clickedY < Constants.Box2Y) {
                if (!data.isClicked(5)) {
                    manageInterface.setClickable(false);
                    data.setClickedBoxes(index++,5);
                    drawer.drawImage(5, humanPlayerID);
                    setNotClickedBoxes(5);

                    if (Winner.isWinner(5, humanPlayerID)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status = true;

                    }
                    if(!status && index!=9)
                        AIPlayer();

                }
            } else if (clickedX < Constants.Box1X && clickedY < Constants.Box3Y) {
                if (!data.isClicked(6)) {
                    manageInterface.setClickable(false);
                    data.setClickedBoxes(index++,6);
                    drawer.drawImage(6, humanPlayerID);
                    setNotClickedBoxes(6);

                    if (Winner.isWinner(6, humanPlayerID)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status = true;

                    }
                    if(!status && index!=9)
                        AIPlayer();

                }

            } else if (clickedX < Constants.Box2X && clickedY < Constants.Box3Y) {
                if (!data.isClicked(7)) {
                    manageInterface.setClickable(false);
                    data.setClickedBoxes(index++,7);
                    drawer.drawImage(7, humanPlayerID);
                    setNotClickedBoxes(7);
                    if (Winner.isWinner(7, humanPlayerID)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status=true;
                    }
                    if(!status && index!=9)
                        AIPlayer();
                }
            } else if (clickedX < Constants.Box3X && clickedY < Constants.Box3Y) {
                if (!data.isClicked(8) ) {
                    manageInterface.setClickable(false);
                    data.setClickedBoxes(index++,8);
                    drawer.drawImage(8, humanPlayerID);
                    setNotClickedBoxes(8);
                    if (Winner.isWinner(8, humanPlayerID)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        status = true;

                    }
                    if(!status && index!=9)
                        AIPlayer();

                }
            } else {
                manageInterface.illegalMove();
                lastCalledMoved=false;

                return false;
            }

        }
        if(status){
            data.setNotWinner(false);
        }
        return status;
    }

    public String getWinner() {
        return winner;
    }

    public int getIndex() {
        return index;
    }

    public boolean AIPlayer(){
        boolean status=false;
        //generate a random integer for the index of not clicked boxes
        int randomIndex;
        randomIndex = new Random().nextInt(notClickedBoxes.length);

        int box=notClickedBoxes[randomIndex];
        data.setClickedBoxes(index++,box);
        if(box==-1){
            drawer.drawImage(0,AIplayerID);
        }else {
            drawer.drawImage(box,AIplayerID);

        }
        setNotClickedBoxes(box);
        if (Winner.isWinner(box, AIplayerID)) {
            drawer.drawWinLine(Winner.getWinBoxes());
            winner="ai";
            manageInterface.setResult(winner);
            status = true;
            data.setNotWinner(false);

        }else if(index==9){
            manageInterface.setResult("draw");
        }
        else {
            manageInterface.setClickable(true);
        }
        return status;
    }


    public void clearData() {
        index=0;
        data.setNotWinner(true);
        notClickedBoxes=new int[]{-1,1,2,3,4,5,6,7,8};
        data.setClickedBoxesasArray(new int[9]);
        Winner.resetAll();

    }
}
