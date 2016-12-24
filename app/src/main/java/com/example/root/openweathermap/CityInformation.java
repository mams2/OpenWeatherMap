package com.example.root.openweathermap;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class CityInformation {
    private String name;
    private ArrayList<Weather> weather;
    @SerializedName("main")
    private TemperatureInformation temperature;

    @Override
    public String toString() {
        return name;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Weather> getWeather(){
        return weather;
    }

    public TemperatureInformation getTemperature(){
        return temperature;
    }
}
