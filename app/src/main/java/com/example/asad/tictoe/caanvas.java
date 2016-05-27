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
public class caanvas extends View  {
public Canvas canvas1=new Canvas();
    public float x_axis,y_axis;
    int height,width;
    Bitmap mFinalbitmap= BitmapFactory.decodeResource(getResources(), R.drawable.cross);
    public int []arr=new int[9];

   public  float firstcord=0f,secndcord=0f,thirdcord=0f,forthcord=0f,fifthcord=0f,sixcord=0f,sevencord=0f,eightcord=0f;
    public float widthborder=0f;
   public float h = getContext().getResources().getDisplayMetrics().heightPixels;
   public  float w = getContext().getResources().getDisplayMetrics().widthPixels;
    public caanvas(Context context) {
        super(context);

        widthborder=w/72f;
        firstcord=w/2.88f;
        secndcord=w/1.5f;
        thirdcord=w/18f;
        forthcord=w/1.035f;
        fifthcord=h/4.2f;
        sixcord=h/2.46f;
        sevencord=h/1.63f;
        eightcord=h/1.28f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas1=canvas;
        canvas.save();
        Paint paint = new Paint();
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(widthborder);
        canvas.drawLine(firstcord, fifthcord, firstcord, eightcord, paint);
        canvas.drawLine(secndcord, fifthcord, secndcord, eightcord, paint);
        canvas.drawLine(thirdcord, sixcord, forthcord, sixcord, paint);
        canvas.drawLine(thirdcord, sevencord, forthcord, sevencord, paint);
        Boxtrack();


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        x_axis = ev.getX();
        y_axis = ev.getY();
        Log.i("Tag","Rana asad");
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN://avc

                ;
                invalidate();
                break;
        }


        return true;
    }

    public void Showimage()
    {

       mFinalbitmap = Bitmap.createScaledBitmap(mFinalbitmap, width, height, false);
        canvas1.drawBitmap(mFinalbitmap, thirdcord, fifthcord, null);
    }
    public  void Showimage2()
    {
        mFinalbitmap = Bitmap.createScaledBitmap(mFinalbitmap, width, height, false);
        canvas1.drawBitmap(mFinalbitmap, firstcord, fifthcord, null);
    }
public void Boxtrack()//This give the value to array e.g if first box is touch then arr[0]=1
{
    if(thirdcord<x_axis&&x_axis<firstcord&&fifthcord<y_axis&&y_axis<sixcord)//For A11 Box
    {
        arr[0]=1;
        width=(int)(firstcord-thirdcord);
        height=(int)(sixcord-fifthcord);

    }
    else if(firstcord<x_axis&&x_axis<secndcord&&fifthcord<y_axis&&y_axis<sixcord)
        arr[1]=2;
    else if(secndcord<x_axis&&x_axis<forthcord&&fifthcord<y_axis&&y_axis<sixcord)
        arr[2]=3;
    else if(thirdcord<x_axis&&x_axis<firstcord&&sixcord<y_axis&&y_axis<sevencord)
        arr[3]=4;
    else if(firstcord<x_axis&&x_axis<secndcord&&sixcord<y_axis&&y_axis<sevencord)
        arr[4]=5;
    else if (secndcord<x_axis&&x_axis<forthcord&&sixcord<y_axis&&y_axis<sevencord)
        arr[5]=6;
    else if (thirdcord<x_axis&&x_axis<firstcord&&sevencord<y_axis&&y_axis<eightcord)
        arr[6]=7;
    else  if (firstcord<x_axis&&x_axis<secndcord&&sevencord<y_axis&&y_axis<eightcord)
        arr[7]=8;
    else if(secndcord<x_axis&&x_axis<forthcord&&sevencord<y_axis&&y_axis<eightcord)
        arr[8]=9;


}
    public  void Place_Images_In_Boxes()//This check the which box is touched and which is not and fill the touched boxes with image
    {

for(int arrtem:arr)
{
    if(arrtem==1)



}

    }
}