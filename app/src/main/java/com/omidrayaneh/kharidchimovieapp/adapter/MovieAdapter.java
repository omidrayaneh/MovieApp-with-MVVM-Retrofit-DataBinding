package com.omidrayaneh.kharidchimovieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.omidrayaneh.kharidchimovieapp.R;
import com.omidrayaneh.kharidchimovieapp.databinding.MovieListItemBinding;
import com.omidrayaneh.kharidchimovieapp.model.Movie;
import com.omidrayaneh.kharidchimovieapp.view.MovieActivity;

import java.util.ArrayList;

public class MovieAdapter extends PagedListAdapter<Movie,MovieAdapter.MovieViewHolder> {
    private Context context;


    public MovieAdapter(Context context) {

        super(Movie.CALLBACK);
        this.context = context;

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       MovieListItemBinding movieListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
               ,R.layout.movie_list_item,parent,false);


        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie=getItem(position);
        holder.movieListItemBinding.setMovie(movie);

       }




    public class MovieViewHolder extends RecyclerView.ViewHolder{


        private MovieListItemBinding movieListItemBinding;


        public MovieViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding=movieListItemBinding;


            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   int position=getAdapterPosition();
                  Movie selectedMovie=getItem(position);
                   Intent intent = new Intent(context, MovieActivity.class);

                   intent.putExtra("movies",selectedMovie);
                   context.startActivity(intent);

                }
           });
        }
    }
}
