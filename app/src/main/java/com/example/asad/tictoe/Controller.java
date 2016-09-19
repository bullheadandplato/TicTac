package com.example.asad.tictoe;

import android.util.Log;
import android.view.MotionEvent;

import com.osama.backend.UIController;

/**
 * Created by asad on 5/28/16.
 */
public class Controller {
    private static Drawer instace1;
    private float  x_axis,y_axis;
    private int[][] WinBox=new int[][]{{0,0,0},{0,0,0},{0,0,0}};//This save the box which is click
    private int id=1;
    private int playerID;
    public Controller()
    {}
    public void Accept_Drawer_Class_Instance(Drawer canvas )
    {
        instace1=canvas;

    }
    public void Boxtrack(float x,float y)//This give the value to array e.g if first box is touch then arr[0]=1 and also player @id
    {
        x_axis = x;
        y_axis = y;

        if (instace1.thirdcord < x_axis && x_axis < instace1.firstcord && instace1.fifthcord < y_axis && y_axis < instace1.sixcord)//For A11 Box
        {
            if (instace1.arr[0] == 0) {
                uiController.drawBox(1);
                if (id % 2 != 0) {
                    instace1.arr2[0] = 1;
                    WinBox[0][0] = 1;
                    id++;
                } else {
                    instace1.arr2[0] = 2;
                    WinBox[0][0] = 2;
                    id++;
                }
                instace1.arr[0] = 1;
            }
        } else if (instace1.firstcord < x_axis && x_axis < instace1.secndcord && instace1.fifthcord < y_axis && y_axis < instace1.sixcord) {

            if (instace1.arr[1] == 0) {
                uiController.drawBox(2);
                if (id % 2 != 0) {
                    instace1.arr2[1] = 1;
                    WinBox[0][1] = 1;
                    id++;
                } else {
                    instace1.arr2[1] = 2;
                    WinBox[0][1] = 2;
                    id++;
                }
                instace1.arr[1] = 2;
            }
        } else if (instace1.secndcord < x_axis && x_axis < instace1.forthcord && instace1.fifthcord < y_axis && y_axis < instace1.sixcord) {

            if (instace1.arr[2] == 0) {
                uiController.drawBox(3);
                if (id % 2 != 0) {
                    instace1.arr2[2] = 1;
                    WinBox[0][2] = 1;
                    id++;
                } else {
                    instace1.arr2[2] = 2;
                    WinBox[0][2] = 2;
                    id++;
                }
                instace1.arr[2] = 3;
            }
        } else if (instace1.thirdcord < x_axis && x_axis < instace1.firstcord && instace1.sixcord < y_axis && y_axis < instace1.sevencord) {

            if (instace1.arr[3] == 0) {
                uiController.drawBox(4);
                if (id % 2 != 0) {
                    instace1.arr2[3] = 1;
                    WinBox[1][0] = 1;
                    id++;
                } else {
                    instace1.arr2[3] = 2;
                    WinBox[1][0] = 2;
                    id++;
                }

                instace1.arr[3] = 4;
            }
        } else if (instace1.firstcord < x_axis && x_axis < instace1.secndcord && instace1.sixcord < y_axis && y_axis < instace1.sevencord) {

            if (instace1.arr[4] == 0) {
                uiController.drawBox(5);
                if (id % 2 != 0) {
                    instace1.arr2[4] = 1;
                    WinBox[1][1] = 1;
                    id++;
                } else {
                    instace1.arr2[4] = 2;
                    WinBox[1][1] = 2;
                    id++;
                }
                instace1.arr[4] = 5;
            }
        } else if (instace1.secndcord < x_axis && x_axis < instace1.forthcord && instace1.sixcord < y_axis && y_axis < instace1.sevencord) {

            if (instace1.arr[5] == 0) {
                uiController.drawBox(6);
                if (id % 2 != 0) {
                    instace1.arr2[5] = 1;
                    WinBox[1][2] = 1;
                    id++;
                } else {
                    instace1.arr2[5] = 2;
                    WinBox[1][2] = 2;
                    id++;
                }
                instace1.arr[5] = 6;
            }
        } else if (instace1.thirdcord < x_axis && x_axis < instace1.firstcord && instace1.sevencord < y_axis && y_axis < instace1.eightcord) {

            if (instace1.arr[6] == 0) {
                uiController.drawBox(7);
                if (id % 2 != 0) {
                    instace1.arr2[6] = 1;
                    WinBox[2][0] = 1;
                    id++;
                } else {
                    instace1.arr2[6] = 2;
                    WinBox[2][0] = 2;
                    id++;
                }
                instace1.arr[6] = 7;
            }
        } else if (instace1.firstcord < x_axis && x_axis < instace1.secndcord && instace1.sevencord < y_axis && y_axis < instace1.eightcord) {

            if (instace1.arr[7] == 0) {
                uiController.drawBox(8);
                if (id % 2 != 0) {
                    instace1.arr2[7] = 1;
                    WinBox[2][1] = 1;
                    id++;
                } else {
                    instace1.arr2[7] = 2;
                    WinBox[2][1] = 2;
                    id++;
                }
                instace1.arr[7] = 8;
            }
        } else if (instace1.secndcord < x_axis && x_axis < instace1.forthcord && instace1.sevencord < y_axis && y_axis < instace1.eightcord) {

            if (instace1.arr[8] == 0){
                uiController.drawBox(9);
                if (id % 2 != 0) {
                    instace1.arr2[8] = 1;
                    WinBox[2][2] = 1;
                    id++;
                } else {
                    instace1.arr2[8] = 2;
                    WinBox[2][2] = 2;
                    id++;
                }
            instace1.arr[8] = 9;
        }
    }


    }
    public  boolean Check_Condition() {
        if (WinBox[0][0] == 2 && WinBox[1][0] == 2 && WinBox[2][0] == 2) {
            instace1.Coulom_First();
            return true;
        } else if (WinBox[0][1] == 2 && WinBox[1][1] == 2 && WinBox[2][1] == 2) {
            instace1.Coulom_Two();
            return true;
        } else if (WinBox[0][2] == 2 && WinBox[1][2] == 2 && WinBox[2][2] == 2) {
            instace1.Coulom_Three();
            return true;
        } else if (WinBox[0][0] == 2 && WinBox[0][1] == 2 && WinBox[0][2] == 2) {
            instace1.Row_One();
            return true;
        } else if (WinBox[1][0] == 2 && WinBox[1][1] == 2 && WinBox[1][2] == 2) {
            instace1.Row_Two();
            return true;
        } else if (WinBox[2][0] == 2 && WinBox[2][1] == 2 && WinBox[2][2] == 2) {
            instace1.Row_Three();
            return true;
        } else if (WinBox[0][0] == 2 && WinBox[1][1] == 2 && WinBox[2][2] == 2) {
            instace1.Daigonal_One();
            return true;
        } else if (WinBox[0][2] == 2 && WinBox[1][1] == 2 && WinBox[2][0] == 2) {
            instace1.Daigonal_Two();
            return true;
        }
        if (WinBox[0][0] == 1 && WinBox[1][0] == 1 && WinBox[2][0] == 1) {
            instace1.Coulom_First();
            return true;
        } else if (WinBox[0][1] == 1 && WinBox[1][1] == 1 && WinBox[2][1] == 1) {
            instace1.Coulom_Two();
            return true;
        } else if (WinBox[0][2] == 1 && WinBox[1][2] == 1 && WinBox[2][2] == 1) {
            instace1.Coulom_Three();
            return true;
        } else if (WinBox[0][0] == 1 && WinBox[0][1] == 1 && WinBox[0][2] == 1) {
            instace1.Row_One();
            return true;
        } else if (WinBox[1][0] == 1 && WinBox[1][1] == 1 && WinBox[1][2] == 1) {
            instace1.Row_Two();
            return true;
        } else if (WinBox[2][0] == 1 && WinBox[2][1] == 1 && WinBox[2][2] == 1) {
            instace1.Row_Three();
            return true;
        } else if (WinBox[0][0] == 1 && WinBox[1][1] == 1 && WinBox[2][2] == 1) {
            instace1.Daigonal_One();
            return true;
        } else if (WinBox[0][2] == 1 && WinBox[1][1] == 1 && WinBox[2][0] == 1) {
            instace1.Daigonal_Two();
            return true;
        }
        return false;
    }
    public void drawBox(int box){
        switch (box) {
            case 1:
                 instace1.Accept_Box(1);
                break;
            case 2:
                instace1.Accept_Box(2);
                break;
            case 3:
                instace1.Accept_Box(3);
                break;
            case 4:
                instace1.Accept_Box(4);
                break;
            case 5:
                instace1.Accept_Box(5);
                break;
            case 6:
                instace1.Accept_Box(6);
                break;
            case 7:
                instace1.Accept_Box(7);
                break;
            case 8:
                instace1.Accept_Box(8);
                break;
            case 9:
                instace1.Accept_Box(9);
                break;
        }
    }
    UIController uiController;
    public void setUIController(UIController con){
        uiController=con;
    }

    public void setEnable() {
        //set the drawer to show it self.
        instace1.Set_Status(true);
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}
