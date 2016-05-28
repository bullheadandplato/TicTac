package com.example.asad.tictoe;

import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by asad on 5/28/16.
 */
public class Controller {
    private static Drawer instace1;
    private float  x_axis,y_axis;
    public Controller()
    {}
    public void Accept_Drawer_Class_Instance(Drawer canvas )
    {
        instace1=canvas;

    }
    public void Boxtrack(float x,float y)//This give the value to array e.g if first box is touch then arr[0]=1
    { x_axis=x;
        y_axis=y;
        if (instace1.thirdcord < x_axis && x_axis < instace1.firstcord &&instace1.fifthcord < y_axis && y_axis <instace1.sixcord)//For A11 Box
            instace1.arr[0] = 1;
         else if (instace1.firstcord < x_axis && x_axis < instace1.secndcord && instace1.fifthcord < y_axis && y_axis < instace1.sixcord)
            instace1.arr[1] = 2;
         else if (instace1.secndcord < x_axis && x_axis < instace1.forthcord && instace1.fifthcord < y_axis && y_axis < instace1.sixcord)
            instace1.arr[2] = 3;
        else if (instace1.thirdcord < x_axis && x_axis < instace1.firstcord && instace1.sixcord < y_axis && y_axis < instace1.sevencord)
            instace1.arr[3] = 4;
        else if (instace1.firstcord < x_axis && x_axis < instace1.secndcord && instace1.sixcord < y_axis && y_axis < instace1.sevencord)
            instace1.arr[4] = 5;
        else if (instace1.secndcord < x_axis && x_axis < instace1.forthcord && instace1.sixcord < y_axis && y_axis < instace1.sevencord)
            instace1.arr[5] = 6;
        else if (instace1.thirdcord < x_axis && x_axis < instace1.firstcord && instace1.sevencord < y_axis && y_axis < instace1.eightcord)
            instace1.arr[6] = 7;
        else if (instace1.firstcord < x_axis && x_axis < instace1.secndcord && instace1.sevencord < y_axis && y_axis < instace1.eightcord)
            instace1.arr[7] = 8;
        else if (instace1.secndcord < x_axis && x_axis < instace1.forthcord && instace1.sevencord < y_axis && y_axis < instace1.eightcord)
            instace1.arr[8] = 9;


    }

}
