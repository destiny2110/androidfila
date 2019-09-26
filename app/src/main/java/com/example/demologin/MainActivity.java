package com.example.demologin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean logoIsShowing = false;

    public void fade (View view) {
        ImageView image = (ImageView)findViewById(R.id.androidImageView);
        ImageView logo = (ImageView)findViewById(R.id.logoImageView);
        if (!logoIsShowing) {
            image.animate().rotation(360).alpha(0).setDuration(1000);
            logo.animate().alpha(1).setDuration(2000);
        } else {
            image.animate().alpha(1).setDuration(2000);
            logo.animate().rotation(360).alpha(0).setDuration(1000);
        }
        logoIsShowing = !logoIsShowing;

        // Creat countdown timer here
        CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
            public void onTick(long tick) {
                Log.i("Info", String.valueOf(tick));
            }
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Finished!", Toast.LENGTH_SHORT);
                // Log.i("Info", "Finished!");
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }
}
