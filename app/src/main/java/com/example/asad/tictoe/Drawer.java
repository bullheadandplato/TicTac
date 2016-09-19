package com.example.asad.tictoe;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by asad on 5/25/16.
 */
public class Drawer extends View {/*
Drawer Class draw the whole UI and placing image in the boxes and
 also draw the lines when Player is won
*/
    public Canvas canvas1 = new Canvas();
    public float x_axis, y_axis;
    int height, width;
    Bitmap mFinalbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cross);
    Bitmap mFinalbitmap2=BitmapFactory.decodeResource(getResources(), R.drawable.zero);
    public int[] arr = new int[9];
    private Controller controller;
    //These are the coordinate of the screen cord is coordinate
    public float firstcord = 0f;
    public float secndcord = 0f;
    public float thirdcord = 0f;
    public float forthcord = 0f;
    public float fifthcord = 0f;
    public float sixcord = 0f;
    public float sevencord = 0f;
    public float eightcord = 0f;
    public float widthborder = 0f;//Width of the lines
    public  int[] arr2=new int[9];
    public float h = getContext().getResources().getDisplayMetrics().heightPixels;
    public float w = getContext().getResources().getDisplayMetrics().widthPixels;//This get the total height and width of screen
    private boolean getvalue=false;
    public Drawer(Context context) {
        super(context);

        widthborder = w / 72f;
        firstcord = w / 2.88f;
        secndcord = w / 1.5f;
        thirdcord = w / 18f;
        forthcord = w / 1.035f;
        fifthcord = h / 4.2f;
        sixcord = h / 2.46f;
        sevencord = h / 1.63f;
        eightcord = h / 1.28f;
        width = (int) (firstcord - (thirdcord + 25));
        height = (int) (sixcord - (fifthcord + 25));
        mFinalbitmap = Bitmap.createScaledBitmap(mFinalbitmap, width, height, false);//Set width and Height of image
        mFinalbitmap2 = Bitmap.createScaledBitmap(mFinalbitmap2, width, height, false);//Set width and Height of image
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas1 = canvas;
        canvas.save();
        Paint paint = new Paint();
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(widthborder);
        canvas.drawLine(firstcord, fifthcord, firstcord, eightcord, paint);
        canvas.drawLine(secndcord, fifthcord, secndcord, eightcord, paint);
        canvas.drawLine(thirdcord, sixcord, forthcord, sixcord, paint);
        canvas.drawLine(thirdcord, sevencord, forthcord, sevencord, paint);
        Place_Images_In_Boxes();


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        x_axis = ev.getX();
        y_axis = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                /*When Touch the Box and anywhere on the screen then x_axis an y-axis location is
                 pass to Boxtrack which is the method of #Controller Class.
                  */
                if (getvalue==false)
                {controller.Boxtrack(x_axis,y_axis);
                invalidate();//This Redraw the whole Graphics from starting
                break;}
        }


        return true;
    }

    public void ShowimageA11() {//This Method show the image for First one Box

        if (arr2[0]==1)
        canvas1.drawBitmap(mFinalbitmap, thirdcord, fifthcord, null);
        else if (arr2[0]==2)
        canvas1.drawBitmap(mFinalbitmap2, thirdcord, fifthcord, null);
        getvalue=controller.Check_Condition();
    }

    public void ShowimageA12() {//This Method show the image for Second Box
        if (arr2[1]==1)
        canvas1.drawBitmap(mFinalbitmap, (firstcord+25), fifthcord, null);
        else if (arr2[1]==2)
        canvas1.drawBitmap(mFinalbitmap2, (firstcord+25), fifthcord, null);
        getvalue=controller.Check_Condition();
    }
    public void ShowImageA13() {//This Mathod show the image for Third Box
        if (arr2[2]==1)
        canvas1.drawBitmap(mFinalbitmap, (secndcord+25), fifthcord, null);
        else if (arr2[2]==2)
        canvas1.drawBitmap(mFinalbitmap2, (secndcord+25), fifthcord, null);
        getvalue=controller.Check_Condition();
    }
    public  void ShowImageA21(){//This Method show the image for Forth Box
        if (arr2[3]==1)
        canvas1.drawBitmap(mFinalbitmap, (thirdcord), (sixcord+45), null);
        else if (arr2[3]==2)
        canvas1.drawBitmap(mFinalbitmap2, (thirdcord), (sixcord+45), null);
        getvalue=controller.Check_Condition();
    }
    public  void ShowImageA22(){//This Method show the image for Fifth Box
        if (arr2[4]==1)
        canvas1.drawBitmap(mFinalbitmap, (firstcord+25), (sixcord+45), null);
        else if (arr2[4]==2)
        canvas1.drawBitmap(mFinalbitmap2, (firstcord+25), (sixcord+45), null);
        getvalue=controller.Check_Condition();
    }
    public  void ShowImageA23(){//This Method show the image for Sixth Box
        if (arr2[5]==1)
        canvas1.drawBitmap(mFinalbitmap, (secndcord+25), (sixcord+45), null);
        else if (arr2[5]==2)
        canvas1.drawBitmap(mFinalbitmap2, (secndcord+25), (sixcord+45), null);
        getvalue=controller.Check_Condition();
    }

    public  void ShowImageA31(){//This Method show the image for Seventh Box
        if (arr2[6]==1)
        canvas1.drawBitmap(mFinalbitmap, (thirdcord), (sevencord+25), null);
        else if (arr2[6]==2)
        canvas1.drawBitmap(mFinalbitmap2, (thirdcord), (sevencord+25), null);
        getvalue=controller.Check_Condition();

    }
    public void ShowImmageA32() {//This Method show the image for Eighth Box
        if (arr2[7]==1)
        canvas1.drawBitmap(mFinalbitmap, (firstcord+25), (sevencord+25), null);
        else if(arr2[7]==2)
        canvas1.drawBitmap(mFinalbitmap2, (firstcord+25), (sevencord+25), null);
        getvalue=controller.Check_Condition();
    }
    public void ShowImageA33(){//This Method show the image for Ninth Box
        if (arr2[8]==1)
        canvas1.drawBitmap(mFinalbitmap, (secndcord+25), (sevencord+25), null);
        else  if (arr2[8]==2)
        canvas1.drawBitmap(mFinalbitmap2, (secndcord+25), (sevencord+25), null);
        getvalue=controller.Check_Condition();
    }


    public void Place_Images_In_Boxes()//This check the which box is touched and which is not and fill the touched boxes with image
    {

        for (int arrtem : arr) {
            switch (arrtem) {
                case 1:
                    ShowimageA11();
                    break;
                case 2:
                    ShowimageA12();
                    break;
                case 3:
                    ShowImageA13();
                    break;
                case 4:
                    ShowImageA21();
                    break;
                case  5:
                    ShowImageA22();
                    break;
                case 6:
                    ShowImageA23();
                    break;
                case 7:
                    ShowImageA31();
                    break;
                case  8:
                    ShowImmageA32();
                    break;
                case 9:
                    ShowImageA33();
                    break;
            }


        }

    }
    public  void Row_One()
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(widthborder);
        canvas1.drawLine((thirdcord+10), ((sixcord+fifthcord)/2), (forthcord-10),((sixcord+fifthcord)/2) , paint);
    }
    public void Row_Two()
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(widthborder);
        canvas1.drawLine((thirdcord+10), ((sixcord+sevencord)/2), (forthcord-10),((sixcord+sevencord)/2) , paint);
    }
    public  void Row_Three()
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(widthborder);
        canvas1.drawLine((thirdcord+10), ((sevencord+eightcord)/2), (forthcord-10),((sevencord+eightcord)/2) , paint);
    }
    public  void  Coulom_First()
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(widthborder);
        canvas1.drawLine(((thirdcord+firstcord)/2), (fifthcord+10),((thirdcord+firstcord)/2) ,(eightcord-10) , paint);
    }
    public void Coulom_Two()
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(widthborder);
        canvas1.drawLine(((secndcord+firstcord)/2), (fifthcord+10),((secndcord+firstcord)/2) ,(eightcord-10) , paint);

    }
    public void Coulom_Three()
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(widthborder);
        canvas1.drawLine(((forthcord+secndcord)/2), (fifthcord+10),((forthcord+secndcord)/2) ,(eightcord-10) , paint);
    }
    public void Daigonal_One()
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(widthborder);
        canvas1.drawLine(thirdcord, fifthcord,forthcord ,eightcord , paint);
    }
    public void Daigonal_Two()
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(widthborder);
        canvas1.drawLine(forthcord, fifthcord,thirdcord ,eightcord , paint);
    }
    public void Accept_Controller_Class_Instance(Controller con)
    {
        controller=con;
    }
    public void Accept_Box(int num)
    {


    }
}