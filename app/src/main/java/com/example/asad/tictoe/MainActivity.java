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

import com.osama.backend.UIController;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller controller=new Controller();
       final
       Drawer Gameshow=new Drawer(this);
        controller.Accept_Drawer_Class_Instance(Gameshow);

        UIController uiController=new UIController(this);
        Button playbtn=(Button)findViewById(R.id.button1);

playbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        setContentView(Gameshow);
    }
});
       // Getstart();

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
