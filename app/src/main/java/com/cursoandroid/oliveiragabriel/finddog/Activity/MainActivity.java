package com.cursoandroid.oliveiragabriel.finddog.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cursoandroid.oliveiragabriel.finddog.R;
import com.cursoandroid.oliveiragabriel.finddog.adapter.AdapterBreed;
import com.cursoandroid.oliveiragabriel.finddog.call.BreedsService;
import com.cursoandroid.oliveiragabriel.finddog.model.BreedsModel;
import com.cursoandroid.oliveiragabriel.finddog.model.Test;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cursoandroid.oliveiragabriel.finddog.adapter.AdapterBreed.*;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> stringList;
    private Retrofit retrofit;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_breed);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        toolbar.setTitle("Dog's breeds");


        //Retrofit settings
        retrofit = new Retrofit.Builder().baseUrl("https://dog.ceo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Setting RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //Setting adapter
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));




        ResponseApi();



    }

    public void ResponseApi() {

        BreedsService breedsService = retrofit.create(BreedsService.class);
        Call<BreedsModel> call = breedsService.call_api2();

        call.enqueue(new Callback<BreedsModel>() {
            @Override
            public void onResponse(Call<BreedsModel> call, Response<BreedsModel> response) {
                final BreedsModel breeds = response.body();
                stringList = breeds.getMessageList();

                AdapterBreed adapterBreed = new AdapterBreed(stringList);
                recyclerView.setAdapter(adapterBreed);

                adapterBreed.setOnClickAdapter(new OnClickAdapter() {
                    @Override
                    public void clickItemAdapter(int position) {

                        Toast.makeText(MainActivity.this, breeds.getMessageList().get(position), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, RecyclerViewPhotos.class);
                        intent.putExtra("breed", breeds.getMessageList().get(position));
                        startActivity(intent);


                    }
                });


            }

            @Override
            public void onFailure(Call<BreedsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


 /*   public class MyTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {

            String url_string = strings[0];
            InputStream inputStream = null;
            BufferedReader bufferedReader = null;
            InputStreamReader inputStreamReader = null;
            HttpURLConnection httpURLConnection = null;
            StringBuffer stringBuffer = new StringBuffer();
            String line ="";
            try {
                URL url = new URL(url_string);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                while ((line = bufferedReader.readLine()) != null){

                    stringBuffer.append(line);

                }








            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return stringBuffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                JSONObject jsonObject = new JSONObject(s);
                JSONObject jsonArray = jsonObject.getJSONObject("message");


                while (jsonArray.keys().hasNext()){

                    stringList.add(jsonArray.keys().toString());
                    AdapterBreed adapterBreed = new AdapterBreed(stringList);
                    recyclerView.setAdapter(adapterBreed);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

        }
    }*/


}
