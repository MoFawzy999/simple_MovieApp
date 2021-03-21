package com.fawzy.parsingjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context ;
    ArrayList<MovieModelClass> movieModelClasses = new ArrayList<>();
    MovieModelClass movieModelClass ;

    public Adapter(Context context, ArrayList<MovieModelClass> movieModelClasses) {
        this.context = context;
        this.movieModelClasses = movieModelClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        movieModelClass = movieModelClasses.get(position);
        holder.vote.setText(movieModelClass.getVote());
        holder.name.setText(movieModelClass.getName());
        // put the general directory of images brfore taken response of image string from JSON
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+movieModelClass.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movieModelClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
         TextView vote , name ;
         ImageView imageView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            vote = itemView.findViewById(R.id.id_txt);
            imageView = itemView.findViewById(R.id.img);
        }
    }



}
