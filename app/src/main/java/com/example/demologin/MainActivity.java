package com.example.demologin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    public void login(View view) {
        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        EditText numberEditText = (EditText) findViewById(R.id.passwordEditText);
        ImageView image = (ImageView) findViewById(R.id.centerImageView);

        Double hidenNumber = Double.parseDouble(numberEditText.getText().toString());
        Double magicNumber = hidenNumber*1.8;

        Log.i("Info", "Button pressed!");
        Log.i("username", usernameEditText.getText().toString());
        Log.i("password", numberEditText.getText().toString());

        image.setImageResource(R.drawable.android);

        String messageToast = "Hello "+ usernameEditText.getText().toString() + " num: " +
                Double.toString(magicNumber);

        Toast.makeText(this, messageToast, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
