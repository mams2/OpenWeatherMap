package com.example.root.openweathermap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ListCities extends AppCompatActivity {

    public static class HTTPUtils {
        public static String acessar(String endereco){
            try {
                URL url = new URL(endereco);
                URLConnection conn = url.openConnection();
                InputStream is = conn.getInputStream();
                Scanner scanner = new Scanner(is);
                String conteudo = scanner.useDelimiter("\\A").next();
                scanner.close();
                return conteudo;
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cities);

        Intent intent = getIntent();
        String message = intent.getStringExtra("com.example.openweathermap.BUSCAR");
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_list_cities);
        layout.addView(textView);
    }


}
