package com.example.asad.tictoe;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new caanvas(this));


        final caanvas nextshow=new caanvas(this);
        Button playbtn=(Button)findViewById(R.id.button1);
/*
playbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        setContentView(nextshow);
    }
});
       // Getstart();
*/
    }
   /* public void Getstart()
    { String getcoins=null;
        int coinsamount=0;
        if(coins.getText().equals(null))
        {
            coins.setText("1k");
        }
        getcoins=coins.getText().toString().replaceAll("[^0-9]", "");
        coinsamount=Integer.parseInt(getcoins);


    }*/
}
