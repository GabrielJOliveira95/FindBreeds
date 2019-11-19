package com.cursoandroid.oliveiragabriel.finddog.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.cursoandroid.oliveiragabriel.finddog.R;
import com.cursoandroid.oliveiragabriel.finddog.adapter.AdapterBreed;
import com.cursoandroid.oliveiragabriel.finddog.call.BreedsService;
import com.cursoandroid.oliveiragabriel.finddog.model.BreedsModel;

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
        toolbar.setTitle("Breeds");


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







    }





    @Override
    protected void onStart() {
        super.onStart();
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

                        Toast.makeText(MainActivity.this, breeds.getMessageList().get(position).substring(0,1).toUpperCase().concat(breeds.getMessageList().get(position).substring(1)), Toast.LENGTH_SHORT).show();
                        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle();

                        Intent intent = new Intent(MainActivity.this, RecyclerViewPhotos.class);
                        intent.putExtra("breed", breeds.getMessageList().get(position));
                        startActivity(intent, bundle);


                    }
                });


            }

            @Override
            public void onFailure(Call<BreedsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}
