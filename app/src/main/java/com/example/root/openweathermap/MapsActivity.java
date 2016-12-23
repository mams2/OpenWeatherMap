package com.example.root.openweathermap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void buscarInformação(View view) {
        if(marker == null){
            Toast.makeText(MapsActivity.this, "Você precisa colocar um marcador no mapa antes de buscar",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, ListCities.class);
            String url = "http://api.openweathermap.org/data/2.5/find?lat="+marker.getPosition().latitude
                    +"&lon="+ marker.getPosition().longitude +"&cnt=15&APPID=fb83342443d6727211b36da403438b02";
            intent.putExtra("com.example.openweathermap.BUSCAR",url);
            startActivity(intent);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in recife and move the camera
        LatLng recife = new LatLng(-8.046390, -34.888228);
//        mMap.addMarker(new MarkerOptions().position(recife).title("Marker in Recife")).setDraggable(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(recife));

        //eventos
        mMap.setOnMarkerClickListener (new GoogleMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker arg0) {
                Toast.makeText(MapsActivity.this, "O marcador esta na posição " + arg0.getPosition().toString(),
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this, "Você adicinou o marcador na posição " + latLng.toString(),
                        Toast.LENGTH_SHORT).show();
                if(marker!=null) marker.remove();
                marker = mMap.addMarker(new MarkerOptions().position(latLng));
            }
        });

    }
}
