package com.example.root.openweathermap;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ListCities extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private String url_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cities);

        Intent intent = getIntent();
        url_request = intent.getStringExtra("com.example.openweathermap.BUSCAR");
//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setText(url_request);
//
//        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_list_cities);
//        layout.addView(textView);

        GetHttp task = new GetHttp();
        task.execute();
    }

    public class GetHttp extends AsyncTask<String, Void, RespostaServidor> {
        private Exception exception;
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
                this.exception = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(RespostaServidor resposta) {
            if(resposta == null){
                System.out.println("debugg");
            }else {
                System.out.println(resposta.cities.get(0).name);
                System.out.println(resposta.cities.get(0).weather.get(0).description);
                System.out.println(resposta.cities.get(0).temperature.temp_min);
                System.out.println(resposta.cities.get(0).temperature.temp_max);
                String[] result = new String[15];
                for(int i=0; i<15; i++) result[i] = resposta.cities.get(i).name;
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(getBaseContext(),
                                android.R.layout.simple_list_item_1, result);
                System.out.println("passou");
                lista = (ListView) findViewById(R.id.lista);
                lista.setAdapter(adapter);
                // Coloca depois da linha no seu OnCreate onde você pega a referência do ListView
// seta o listener para pegar o click do usuário no item do seu list View
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // pega o o item selecionado com os dados da pessoa
                        String medico = (String) lista.getItemAtPosition(position);
                        System.out.println(medico);

                        // cria a intent
//                        Intent intent = new Intent(this, DetalhesMedicoActivity.class);
                        // seta o parametro do medico a exibir os dados
//                        intent.putExtra( "MEDICO" , medico );
                        //  chama a Activity que mostra os detalhes
//                        startActivity(intent);

                    }

                });
            }

            load.dismiss();
            System.out.println("acabou");
        }

        public RespostaServidor get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return gson.fromJson(response.body().charStream(), RespostaServidor.class);
//            return resposta;
        }


    }
}
