package com.cursoandroid.oliveiragabriel.finddog.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cursoandroid.oliveiragabriel.finddog.Activity.MainActivity;
import com.cursoandroid.oliveiragabriel.finddog.Activity.RecyclerViewPhotos;
import com.cursoandroid.oliveiragabriel.finddog.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class AdapterPhotos extends RecyclerView.Adapter<AdapterPhotos.ViewHolderPhotos>{


   private Context context;
   private List<String> stringList;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public AdapterPhotos(List<String> list, Context context){
        this.stringList = list;
        this.context = context;

    }

    public class ViewHolderPhotos extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public ViewHolderPhotos(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_breed);


        }
    }


    @NonNull
    @Override
    public ViewHolderPhotos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.breeds_photos, viewGroup, false);

        return new ViewHolderPhotos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPhotos viewHolderPhotos, int i) {

        Picasso.with(context).load(stringList.get(i)).resize(1500,1500).centerCrop().into(viewHolderPhotos.imageView);

    }

    @Override
    public int getItemCount() {
        return stringList.size();

    }




}
