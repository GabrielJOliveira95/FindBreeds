package com.cursoandroid.oliveiragabriel.finddog.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotosModel {

    @SerializedName("message")
    private List<String> list;


    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
