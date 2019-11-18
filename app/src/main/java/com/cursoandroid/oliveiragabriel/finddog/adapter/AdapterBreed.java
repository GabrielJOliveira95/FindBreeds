package com.cursoandroid.oliveiragabriel.finddog.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.cursoandroid.oliveiragabriel.finddog.R;
import java.util.List;

public class AdapterBreed extends RecyclerView.Adapter<AdapterBreed.MyViewHolder> {

    private List<String> list;
    private OnClickAdapter onClickAdapter;


    public OnClickAdapter getOnClickAdapter() {
        return onClickAdapter;
    }

    public void setOnClickAdapter(OnClickAdapter onClickAdapter) {
        this.onClickAdapter = onClickAdapter;
    }

    public interface OnClickAdapter{

        void clickItemAdapter(int position);
    }



    public AdapterBreed(List<String> list){
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView breed;

        public MyViewHolder(@NonNull View itemView, final OnClickAdapter onClickAdapter) {
            super(itemView);
            breed = itemView.findViewById(R.id.breed_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onClickAdapter != null){

                        int postion = getAdapterPosition();

                        if (postion != RecyclerView.NO_POSITION){
                            onClickAdapter.clickItemAdapter(postion);
                        }
                    }

                    
                }
            });

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_breed, viewGroup, false);

        return new MyViewHolder(view, onClickAdapter);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


       myViewHolder.breed.setText(list.get(i));



    }


    @Override
    public int getItemCount() {
        return list.size();
    }




}
