package com.example.root.openweathermap;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.DecimalFormat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ListCities extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private String url_request;
    private Intent telaCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cities);
        telaCidade = new Intent(this, CityScreen.class);

        Intent intent = getIntent();
        url_request = intent.getStringExtra("com.example.openweathermap.BUSCAR");

        GetHttp task = new GetHttp();
        task.execute();
    }

    public class GetHttp extends AsyncTask<String, Void, RespostaServidor> {
        private ProgressDialog load;
        private ListView lista;

        @Override
        protected void onPreExecute(){
            this.load = ProgressDialog.show(ListCities.this, "Por favor Aguarde ...",
                    "Baixando Informações ...");
        }

        protected RespostaServidor doInBackground(String... urls) {
            try {
                return get(url_request);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(RespostaServidor resposta) {
            if(resposta == null){
                System.out.println("debugg");
            }else {

                ArrayAdapter<CityInformation> adapter =
                        new ArrayAdapter<CityInformation>(getBaseContext(),
                                android.R.layout.simple_list_item_1, resposta.cities);

                lista = (ListView) findViewById(R.id.lista);
                lista.setAdapter(adapter);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        CityInformation city = (CityInformation) lista.getItemAtPosition(position);
                        DecimalFormat df = new DecimalFormat("0.##");
                        telaCidade.putExtra( "com.example.openweathermap.NOME" , city.getName());
                        telaCidade.putExtra("com.example.openweathermap.MAX_TEMPERATURA", ""+
                                df.format(city.getTemperature().getTemp_max()-273.0));
                        telaCidade.putExtra("com.example.openweathermap.MIN_TEMPERATURA", ""+
                                df.format(city.getTemperature().getTemp_min()-273.0));
                        telaCidade.putExtra("com.example.openweathermap.DESCRICAO", city.getWeather().get(0).getDescription());
                        startActivity(telaCidade);
                    }

                });
            }

            load.dismiss();
        }

        public RespostaServidor get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return gson.fromJson(response.body().charStream(), RespostaServidor.class);
        }


    }
}
