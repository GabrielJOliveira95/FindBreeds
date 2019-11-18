package com.cursoandroid.oliveiragabriel.finddog.call;

import com.cursoandroid.oliveiragabriel.finddog.model.BreedsModel;
import com.cursoandroid.oliveiragabriel.finddog.model.PhotosModel;
import com.cursoandroid.oliveiragabriel.finddog.model.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BreedsService {

    @GET("api/breed/{breed}/images")
    Call<PhotosModel> call_api(@Path("breed") String breed);

    @GET("api/breeds/list")
    Call<BreedsModel> call_api2();

    @GET("/users")
    Call<List<Test>> call_test();

}

