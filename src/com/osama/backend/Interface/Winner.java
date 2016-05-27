package com.osama.backend.Interface;

import org.jetbrains.annotations.Contract;

/**
 * Created by osama on 5/12/16.
 * this file will implement methods which will determine winning move
 */
public class Winner {

    private static int index = 0;
    private static int index1 = 0;

    private static final int[][] WIN = {{0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };
    private static int[] playerOneChoice = {-1, -1, -1, -1, -1};// 0 has a technical problem while comparing.
    private static int[] playerTwoChoice = {-1, -1, -1, -1, -1};
    private static int[] winBoxes={-1,-1,-1};


    public static boolean isWinner(int lastMove, int player) {
        boolean status = false;
        if (player == 1) {
            playerOneChoice[index++] = lastMove;
           status=check(playerOneChoice);
        } if (player == 2) {
            playerTwoChoice[index1++] = lastMove;
            status=check(playerTwoChoice);
        }
        return status;
    }

    private static boolean check(int[] p) {
        if (index > 2 || index1>2) {

            for ( int[] a : WIN ) {
                System.out.println("Player: ");

                int winCount = 0;
                    for (int i = 0; i < p.length; i++) {
                        for (int j = 0; j < a.length; j++) {
                            System.out.println("Arrays value are: "+p[j]);
                            if (p[i] == a[j]) {
                                winBoxes[j]=a[j];
                                winCount++;
                            }
                        }
                        if (winCount == 3) {
                            return true;
                        }
                    }
                }

            }
        return false;

    }

    public static int[] getWinBoxes() {
        winBoxes=sort(winBoxes); //sort the numbers
        return winBoxes;
    }
    @Contract(pure = true)
    private static int[] sort(int[] win){
        int min=win[0];
        int temp;
        for(int i=1;i<win.length;i++){
            if(min>win[i]){
                temp=win[i];
                win[i]=min;
                min=temp;
            }
        }
        return win;
    }
    public static void resetAll(){
        index=0;
        index1=0;
        for(int i=0;i<playerOneChoice.length;i++){
            if(i<3){
                winBoxes[i]=-1;
            }
            playerOneChoice[i]=-1;
            playerTwoChoice[i]=-1;
        }
    }
}
