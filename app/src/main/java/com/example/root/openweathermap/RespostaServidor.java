package com.example.root.openweathermap;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class RespostaServidor {
    @SerializedName("list")
    public ArrayList<CityInformation> cities;

}