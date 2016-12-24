package com.example.root.openweathermap;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class CityInformation {
    public String name;
    public ArrayList<Weather> weather;
    @SerializedName("main")
    public TemperatureInformation temperature;

}
