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
public class Drawer extends View {
    public Canvas canvas1 = new Canvas();
    public float x_axis, y_axis;
    int height, width;
    Bitmap mFinalbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cross);
    public int[] arr = new int[9];
private Controller controller=new Controller();
    public float firstcord = 0f;
    public float secndcord = 0f;
    public float thirdcord = 0f;
    public float forthcord = 0f;
    public float fifthcord = 0f;
    public float sixcord = 0f;
    public float sevencord = 0f;
    public float eightcord = 0f;
    public float widthborder = 0f;
    public float h = getContext().getResources().getDisplayMetrics().heightPixels;
    public float w = getContext().getResources().getDisplayMetrics().widthPixels;

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
        Log.i("Tag", "Rana asad");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN://avc
                controller.Boxtrack(x_axis,y_axis);
                invalidate();
                break;
        }


        return true;
    }

    public void ShowimageA11() {

        mFinalbitmap = Bitmap.createScaledBitmap(mFinalbitmap, width, height, false);
        canvas1.drawBitmap(mFinalbitmap, thirdcord, fifthcord, null);
    }

    public void ShowimageA12() {
        mFinalbitmap = Bitmap.createScaledBitmap(mFinalbitmap, width, height, false);
        canvas1.drawBitmap(mFinalbitmap, (firstcord+25), fifthcord, null);
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
            }


        }

    }
}