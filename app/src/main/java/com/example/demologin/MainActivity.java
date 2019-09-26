package com.example.demologin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    boolean logoIsShowing = false;

    ImageView imageView;

    public void downloadImage(View view) {
        Log.i("Info", "Button pressed!");
        ImageDowloader task = new ImageDowloader();
        Bitmap myImage;
        try {
            myImage = task.execute("https://www.pinclipart.com/picdir/middle/89-891532_android-logo-png-android-logo-hd-png-clipart.png").get();
            imageView.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public class ImageDowloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream in = connection.getInputStream();
                Bitmap dowloadedBitmap = BitmapFactory.decodeStream(in);
                return dowloadedBitmap;
            } catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
