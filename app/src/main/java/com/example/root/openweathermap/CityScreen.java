package com.example.root.openweathermap;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CityScreen extends AppCompatActivity {

    String city,maxTemp,minTemp,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_screen);
        Intent intent = getIntent();

        city = intent.getStringExtra("com.example.openweathermap.NOME");
        maxTemp = intent.getStringExtra("com.example.openweathermap.MAX_TEMPERATURA");
        minTemp = intent.getStringExtra("com.example.openweathermap.MIN_TEMPERATURA");
        description = intent.getStringExtra("com.example.openweathermap.DESCRICAO");

        TextView tcity = (TextView) findViewById(R.id.textView);
        tcity.setText("City: " + city);

        TextView tmaxTemp = (TextView) findViewById(R.id.textView2);
        tmaxTemp.setText("Max: " + maxTemp + "°");

        TextView tminTemp = (TextView) findViewById(R.id.textView3);
        tminTemp.setText("Min: "+ minTemp + "°");

        TextView tdescription = (TextView) findViewById(R.id.textView4);
        tdescription.setText("Description: " + description);



    }
}
