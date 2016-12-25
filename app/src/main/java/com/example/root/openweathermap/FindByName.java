package com.example.root.openweathermap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.DecimalFormat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FindByName extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private String url_request;
    private Intent telaCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_name);
        telaCidade = new Intent(this, CityScreen.class);
    }

    public void capturaNomes(View view) {
        EditText name = (EditText) findViewById(R.id.editText5);
        String nome = name.getText().toString();
        EditText code = (EditText) findViewById(R.id.editText6);
        String codigo = code.getText().toString();

        if(!nome.equals("")){
            if(!codigo.equals("")){
                url_request = "http://api.openweathermap.org/data/2.5/weather?q="+nome+","+codigo+"&APPID=fb83342443d6727211b36da403438b02";
            }
            else{
                url_request = "http://api.openweathermap.org/data/2.5/weather?q="+nome+"&APPID=fb83342443d6727211b36da403438b02";
            }
            GetHttp task = new GetHttp();
            task.execute();
        }
        else{
            Toast.makeText(FindByName.this, "O nome não pode ficar em branco",
                    Toast.LENGTH_LONG).show();
        }
    }

    private class GetHttp extends AsyncTask<String, Void, CityInformation> {
        private ProgressDialog load;

        @Override
        protected void onPreExecute(){
            this.load = ProgressDialog.show(FindByName.this, "Por favor Aguarde ...",
                    "Baixando Informações ...");
        }

        protected CityInformation doInBackground(String... urls) {
            try {
                return get(url_request);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(CityInformation resposta) {
            if(resposta == null){
                Toast.makeText(FindByName.this, "Erro inesperado, por favor verificar as informações inseridas" +
                        " e tentar novamente em alguns segundos.",
                        Toast.LENGTH_LONG).show();
            }else {

                DecimalFormat df = new DecimalFormat("0.##");
                telaCidade.putExtra( "com.example.openweathermap.NOME" , resposta.getName());
                telaCidade.putExtra("com.example.openweathermap.MAX_TEMPERATURA", ""+
                        df.format(resposta.getTemperature().getTemp_max()-273.0));
                telaCidade.putExtra("com.example.openweathermap.MIN_TEMPERATURA", ""+
                        df.format(resposta.getTemperature().getTemp_min()-273.0));
                telaCidade.putExtra("com.example.openweathermap.DESCRICAO", resposta.getWeather().get(0).getDescription());
                startActivity(telaCidade);

            }

            load.dismiss();
        }

        public CityInformation get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            if(isSuccess(response.code())) {
                return gson.fromJson(response.body().charStream(), CityInformation.class);
            }
            else{
                return null;
            }
        }

        private boolean isSuccess(int status){
            return status >= 200 && status < 300;
        }


    }
}
