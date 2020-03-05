package com.omidrayaneh.kharidchimovieapp.view;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.omidrayaneh.kharidchimovieapp.R;
import com.omidrayaneh.kharidchimovieapp.databinding.ActivityMovieBinding;
import com.omidrayaneh.kharidchimovieapp.model.Movie;

public class MovieActivity extends AppCompatActivity {
    private ActivityMovieBinding activityMovieBinding;
    private  Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityMovieBinding= DataBindingUtil.setContentView(this,R.layout.activity_movie);

        Intent intent=getIntent();

        if (intent.hasExtra("movies")){
            movie=getIntent().getParcelableExtra("movies");

            activityMovieBinding.setMovie(movie);
            getSupportActionBar().setTitle(movie.getTitle());
        }
    }

}
