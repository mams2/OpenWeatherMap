package com.example.root.openweathermap;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void buscarNome(View view) {
        if(!isNetworkConnected()){
            Toast.makeText(MapsActivity.this, "É preciso estar conectado a internet para usar o serviço. Por favor se conecte a internet!",
                    Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, FindByName.class);
            startActivity(intent);
        }
    }


    public void buscarInformação(View view) {
        if(!isNetworkConnected()){
            Toast.makeText(MapsActivity.this, "É preciso estar conectado a internet para usar o serviço. Por favor se conecte a internet!",
                    Toast.LENGTH_LONG).show();
        }
        else if(marker == null){
            Toast.makeText(MapsActivity.this, "Você precisa colocar um marcador no mapa antes de buscar," +
                    " para fazer isso pressiona a tela no local que você deseja inserir o marcador.",
                    Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent = new Intent(this, ListCities.class);
            String url = "http://api.openweathermap.org/data/2.5/find?lat="+marker.getPosition().latitude
                    +"&lon="+ marker.getPosition().longitude +"&cnt=15&APPID=fb83342443d6727211b36da403438b02";
            intent.putExtra("com.example.openweathermap.BUSCAR",url);
            startActivity(intent);
        }
    }

    private boolean isNetworkConnected(){
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnected()){
            return true;
        }
        else {
            return false;
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng recife = new LatLng(-8.046390, -34.888228);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(recife));

        //eventos
        mMap.setOnMarkerClickListener (new GoogleMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker arg0) {
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
