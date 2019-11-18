package com.cursoandroid.oliveiragabriel.finddog.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cursoandroid.oliveiragabriel.finddog.R;
import com.cursoandroid.oliveiragabriel.finddog.adapter.AdapterPhotos;
import com.cursoandroid.oliveiragabriel.finddog.call.BreedsService;
import com.cursoandroid.oliveiragabriel.finddog.model.PhotosModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.squareup.picasso.Picasso.with;

public class RecyclerViewPhotos extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_photos);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recyclerViewPhotos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Bundle bundle = getIntent().getExtras();
        String breed = bundle.getString("breed");
        ResponseApi(breed);


        breed = breed.substring(0,1).toUpperCase().concat(breed.substring(1));
        toolbar.setTitle(breed);




    }


    public void ResponseApi(final String breed){

        BreedsService breedsService = retrofit.create(BreedsService.class);
        Call<PhotosModel> call = breedsService.call_api(breed);

        call.enqueue(new Callback<PhotosModel>() {

            @Override
            public void onResponse(Call<PhotosModel> call, Response<PhotosModel> response) {
                if (response.isSuccessful()){


                    PhotosModel photosModel = response.body();
                    stringList = photosModel.getList();
                    AdapterPhotos adapterPhotos = new AdapterPhotos(stringList, getApplicationContext());
                    recyclerView.setAdapter(adapterPhotos);



                }
            }

            @Override
            public void onFailure(Call<PhotosModel> call, Throwable t) {

            }
        });






    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.back :

                this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
