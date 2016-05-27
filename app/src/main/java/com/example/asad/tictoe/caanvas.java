package com.example.asad.tictoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.nfc.Tag;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by asad on 5/25/16.
 */
public class caanvas extends View {
    ;

    public caanvas(Context context) {
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float firstcord,secndcord,thirdcord,forthcord,fifthcord,sixcord,sevencord,eightcord;
        float widthborder;

        Paint paint = new Paint();
        paint.setColor(Color.LTGRAY);

        float h = getContext().getResources().getDisplayMetrics().heightPixels;
        float w = getContext().getResources().getDisplayMetrics().widthPixels;
        widthborder=w/72f;
        paint.setStrokeWidth(widthborder);
        firstcord=w/2.88f;
        secndcord=w/1.5f;
        thirdcord=w/18f;
        forthcord=w/1.035f;
        fifthcord=h/4.2f;
        sixcord=h/2.46f;
        sevencord=h/1.63f;
        eightcord=h/1.28f;
        canvas.drawLine(firstcord, fifthcord, firstcord, eightcord, paint);
        canvas.drawLine(secndcord, fifthcord, secndcord, eightcord, paint);
        canvas.drawLine(thirdcord, sixcord, forthcord, sixcord, paint);
        canvas.drawLine(thirdcord, sevencord, forthcord, sevencord, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();

        return true;
    }
}