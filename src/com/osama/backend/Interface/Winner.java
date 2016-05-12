package com.osama.backend.Interface;

import org.jetbrains.annotations.Contract;

/**
 * Created by osama on 5/12/16.
 * this file will implement methods which will determine winning move
 */
public class Winner {



    private static int[] board=new int[9];
    private static int index=0;
    private static final int[][][] WinPATTERN={
            {{1, 2}, {4, 8}, {3, 6}},
            {{0, 2}, {4, 7}},
            {{0, 1}, {4, 6}, {5, 8}},
            {{4, 5}, {0, 6}},
            {{3, 5}, {0, 8}, {2, 6}, {1, 7}},
            {{3, 4}, {2, 8}},
            {{7, 8}, {2, 4}, {0, 3}},
            {{6, 8}, {1, 4}},
            {{6, 7}, {0, 4}, {2, 5}}
            };
    public static boolean isWinner(int lastMove,int player){
        boolean status=false;
        board[index++]=player;
        for (int a:
             board) {
            System.out.print(a);
        }

            int temp=board[lastMove];
            if(temp==0)
                return false;
            for(int i=0;i<WinPATTERN[lastMove].length;i++){
                int[] line=WinPATTERN[lastMove][i];
                if(temp==board[line[0]] && temp==board[line[1]]) {
                    System.out.println("Victory for player: " + temp);
                    status = true;

                }
        }

        return status;
    }
}
