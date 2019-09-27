package com.example.demologin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        resultTextView = findViewById(R.id.weatherTextView);
    }

    public void getWeather(View view) {
        try {
            String cityName = URLEncoder.encode(editText.getText().toString(), "UTF-8");
            DownLoadWeb task = new DownLoadWeb();
            String actualURL = "https://openweathermap.org/data/2.5/weather?q="
                    + cityName + ",uk&appid=b6907d289e10d714a6e88b30761fae22";
            Log.i("URL", actualURL);
            task.execute(actualURL);
            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Cannot find this location weather :(", Toast.LENGTH_SHORT).show();
            resultTextView.setText("Opp!...");
        }
    }

    public class DownLoadWeb extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1) {
                    char currentChar = (char) data;
                    result += currentChar;
                    data = reader.read();
                }

                return result;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Cannot find this location weather :(", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");
                Log.i("JSON", weatherInfo);

                JSONArray jsonArray = new JSONArray(weatherInfo);

                String message = "";

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonPart = jsonArray.getJSONObject(i);
                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");

                    message = main + ": " + description + "\r\n";
                }

                if (!message.equals("")) {
                    resultTextView.setText(message);
                } else {
                    resultTextView.setText("Opp!...");
                }

            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Cannot find this location weather :(", Toast.LENGTH_SHORT).show();
                Log.i("Error", "Look below info");
                e.printStackTrace();
            }
        }
    }

}
