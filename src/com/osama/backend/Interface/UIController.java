package com.osama.backend.Interface;

import com.osama.frontend.GameViewController;
import javafx.animation.ScaleTransition;
import javafx.beans.binding.StringBinding;
import javafx.scene.canvas.Canvas;
import com.osama.backend.Interface.Constants;
import org.jetbrains.annotations.Contract;


/**
 * Created by osama on 5/13/16.
 * Controls the interface explicitly.
 */
public class UIController {
    private int player = 1;
    private ServerConnector server;
    private Drawer drawer;
    private int index = 0;
    private GameViewController manageInterface;
    private Boolean gameStatus;
    private SharedData sharedData=new SharedData();

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
        check();
    }

    public void setPlayer(int player) {
        this.player = player;
        manageInterface.setPlayerID(player);

    }

    private void check() {
        if (gameStatus) {
            manageInterface.setEnable();
        }
    }

    public UIController(GameViewController a) {
        gameStatus = false;
        manageInterface = a;
        server = new ServerConnector();
        server.createConnectionStart();

        server.setaController(this);
        server.arrangeMatch();
        drawer = new Drawer();

    }

    public boolean determineMove(int clickedX, int clickedY) {
        boolean status = false;
        if (index <= sharedData.getClickedBoxes().length && sharedData.isNotWinner()) {
            if (clickedX < Constants.Box1X && clickedY < Constants.Box1Y) {
                if (!isClicked(-1)) {    //because by default array is initialized to 0 so can't use zero
                    manageInterface.setClickable(false);
                    sharedData.setClickedBoxes(index++,-1);
                    drawer.drawImage(0, player);
                    server.drawMove(-1);
                    if (Winner.isWinner(0, player)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status = true;

                    }
                }
            } else if (clickedX < Constants.Box2X && clickedY < Constants.Box1Y) {
                if (!isClicked(1)) {
                    sharedData.setClickedBoxes(index++,1);
                    drawer.drawImage(1, player);
                    server.drawMove(1);
                    manageInterface.setClickable(false);

                    if (Winner.isWinner(1, player)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status = true;

                    }
                }
            } else if (clickedX < Constants.Box3X && clickedY < Constants.Box1Y) {
                if (!isClicked(2)) {
                    manageInterface.setClickable(false);
                    sharedData.setClickedBoxes(index++,2);
                    drawer.drawImage(2, player);
                    server.drawMove(2);
                    if (Winner.isWinner(2, player)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status = true;

                    }

                }
            } else if (clickedX < Constants.Box1X && clickedY < Constants.Box2Y) {
                if (!isClicked(3)) {
                    manageInterface.setClickable(false);
                    sharedData.setClickedBoxes(index++,3);
                    server.drawMove(3);
                    drawer.drawImage(3, player);
                    if (Winner.isWinner(3, player)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status = true;

                    }

                }
            } else if (clickedX < Constants.Box2X && clickedY < Constants.Box2Y) {
                if (!isClicked(4)) {
                    manageInterface.setClickable(false);
                    sharedData.setClickedBoxes(index++,4);
                    server.drawMove(4);
                    drawer.drawImage(4, player);
                    if (Winner.isWinner(4, player)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status = true;

                    }


                }
            } else if (clickedX < Constants.Box3X && clickedY < Constants.Box2Y) {
                if (!isClicked(5)) {
                    manageInterface.setClickable(false);
                    sharedData.setClickedBoxes(index++,5);
                    server.drawMove(5);
                    drawer.drawImage(5, player);
                    if (Winner.isWinner(5, player)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status = true;

                    }

                }
            } else if (clickedX < Constants.Box1X && clickedY < Constants.Box3Y) {
                if (!isClicked(6)) {
                    manageInterface.setClickable(false);
                    sharedData.setClickedBoxes(index++,6);
                    server.drawMove(6);
                    drawer.drawImage(6, player);
                    if (Winner.isWinner(6, player)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status = true;

                    }

                }

            } else if (clickedX < Constants.Box2X && clickedY < Constants.Box3Y) {
                if (!isClicked(7)) {
                    manageInterface.setClickable(false);
                    sharedData.setClickedBoxes(index++,7);
                    server.drawMove(7);
                    drawer.drawImage(7, player);
                    if (Winner.isWinner(7, player)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status = true;

                    }

                }
            } else if (clickedX < Constants.Box3X && clickedY < Constants.Box3Y) {
                if (!isClicked(8)) {
                    manageInterface.setClickable(false);
                    sharedData.setClickedBoxes(index++,8);
                    server.drawMove(8);
                    drawer.drawImage(8, player);
                    if (Winner.isWinner(8, player)) {
                        drawer.drawWinLine(Winner.getWinBoxes());
                        server.wins(Winner.getWinBoxes());
                        status = true;

                    }

                }
            } else {
                manageInterface.illegalMove();
                return false;
            }

        }
        return status;
    }

    @Contract(pure = true)
    private boolean isClicked(int boxNumber) {
        boolean status = false;
        for (int a :
                sharedData.getClickedBoxes()) {
            if (a == boxNumber) {
                status = true;
            }
        }
        return status;
    }

    public Canvas drawGame() {
        return drawer.drawGame();
    }

    public int getWinner() {
        sharedData.setNotWinner(false);
        return 1;
    }


    public int getIndex() {
        return index;
    }

    public void clearGameDrawing() {
        Winner.resetAll();
        sharedData.setNotWinner(true);
        sharedData.setClickedBoxesasArray(null);
        sharedData.setClickedBoxesasArray(new int[9]);
        index=0;
    }

    public void drawBox(int box) {
        if(!isClicked(box)){
            if(box==-1){
                manageInterface.setClickable(true);
                if (player == 1) {
                    drawer.drawImage(0, 2);
                } else
                    drawer.drawImage(0, 1);
                sharedData.setClickedBoxes(index++, box);
                return;
            }
            manageInterface.setClickable(true);
            if (player == 1) {
                drawer.drawImage(box, 2);
            } else
                drawer.drawImage(box, 1);
            sharedData.setClickedBoxes(index++,box);
        }


    }

    public void wins(int winBoxes) {
        int[] temp = new int[3];
        if (winBoxes == 3) {
            temp[0] = 0;
            temp[1] = 1;
            temp[2] = 2;
        } else if (winBoxes == 13) {
            temp[0] = 3;
            temp[1] = 4;
            temp[2] = 5;
        } else if (winBoxes == 21) {
            temp[0] = 6;
            temp[1] = 7;
            temp[2] = 8;
        } else if (winBoxes == 9) {
            temp[0] = 0;
            temp[1] = 3;
            temp[2] = 6;
        } else if (winBoxes == 12) {
            temp[0] = 1;
            temp[1] = 4;
            temp[2] = 7;
        } else if (winBoxes == 15) {
            temp[0] = 2;
            temp[1] = 5;
            temp[2] = 8;
        } else if (winBoxes == 14) {
            temp[0] = 0;
            temp[1] = 4;
            temp[2] = 8;
        } else if (winBoxes == 16) {
            temp[0] = 2;
            temp[1] = 4;
            temp[2] = 6;
        }
        drawer.drawWinLine(temp);
        //display winner name on screen
        manageInterface.setStatus(2);
    }

    public void otherPlayerQuit() {
        manageInterface.showAlert();
    }


    public void writeRematch() {
        server.reMatch();
    }

    public void reMatch() {
        manageInterface.reArrangeMatch();
    }

    public void startReMatch() {
        server.startMatchAgain();
    }

    public void serverDown() {
        manageInterface.alertServerDown();
    }

    public void matchDraw() {
        server.writeDraw();
    }

    public void matchDrawnfromServer() {
        manageInterface.setStatus(0);
    }

    public void writeRecievedMessage(String message) {
        manageInterface.messageRecieved(message);
    }
    public void sendMessagetoServer(String message){
        server.writeMessage(message);
    }
}
