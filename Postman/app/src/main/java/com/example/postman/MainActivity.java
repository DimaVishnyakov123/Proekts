package com.example.postman;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView txt, txt2, txt3, txt4, txt5, txt6, txt7;
    Button btn;

    String fuutureJokeString = "";
    String url_string = "";
    String updated_at = "";
    String id_string = "";
    String icon_url_string = "";
    String created_at_string = "";
    String categories_string ="";

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txtJoke);
        txt2 = findViewById(R.id.txtURL);
        txt3 = findViewById(R.id.updated_at);
        txt4 = findViewById(R.id.txtID);
        txt5 = findViewById(R.id.icon_url);
        txt6 =findViewById(R.id.created_at);
        txt7 = findViewById(R.id.categories);

        img = findViewById(R.id.image);

        btn = findViewById(R.id.btnClick);

        btn.setOnClickListener(view -> new JokeLoader().execute());


    }


    protected void image(){
        Glide
                .with(this)
                .load(icon_url_string)
                .into(img);
    }

    private  class JokeLoader extends AsyncTask<Void, Void, Void> implements com.example.postman.JokeLoader {

        @Override
        protected Void doInBackground(Void... voids) {
            String jsonString = getJson("https://api.chucknorris.io/jokes/random");
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                fuutureJokeString = jsonObject.getString("value");
                url_string = jsonObject.getString("url");
                updated_at = jsonObject.getString("updated_at");
                id_string = jsonObject.getString("id");
                icon_url_string = jsonObject.getString("icon_url");
                created_at_string = jsonObject.getString("created_at");
                categories_string = jsonObject.getString("categories");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            fuutureJokeString = "";
            url_string = "";
            updated_at = "";
            id_string = "";
            icon_url_string ="";
            created_at_string ="";
            categories_string = "";
            txt.setText("Loading...");
            txt2.setText("Loading...");
            txt3.setText("Loading...");
            txt4.setText("Loading...");
            txt5.setText("Loading...");
            txt6.setText("Loading...");
            txt7.setText("Loading...");
        }


        @Override
        public void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);

            //display the quote if any
            if(!fuutureJokeString.equals("") && !url_string.equals("") && !updated_at.equals("") && !id_string.equals("")
                && !icon_url_string.equals("") && !created_at_string.equals("") && !categories_string.equals("")){
                txt.setText(fuutureJokeString);
                txt2.setText(url_string);
                txt3.setText(updated_at);
                txt4.setText(id_string);
                txt5.setText(icon_url_string);
                txt6.setText(created_at_string);
                txt7.setText(categories_string);
                image();
            }
        }

        private String getJson(String link){
            String data = "";
            try {
                URL url = new URL(link);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    //
                    //
                    //
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
                    data = r.readLine();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }


        @Override
        public void onPostExecute() {

        }
    }


}