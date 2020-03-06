package com.omidrayaneh.kharidchimovieapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;


import com.omidrayaneh.kharidchimovieapp.R;
import com.omidrayaneh.kharidchimovieapp.adapter.MovieAdapter;
import com.omidrayaneh.kharidchimovieapp.databinding.ActivityMainBinding;
import com.omidrayaneh.kharidchimovieapp.model.Movie;

import com.omidrayaneh.kharidchimovieapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private PagedList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);




        getSupportActionBar().setTitle("TMDB Popular Movies Today");

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        getPopularMovies();

        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();

            }
        });
    }

    public void getPopularMovies() {

       /* mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {

                movies = (PagedList<Movie>) moviesFromLiveData;
                showOnRecyclerView();

            }
        });*/

       mainActivityViewModel.getMoviesPageList().observe(this, new Observer<PagedList<Movie>>() {
           @Override
           public void onChanged(PagedList<Movie> moviesFromLiveData) {

               movies=moviesFromLiveData;
               showOnRecyclerView();

           }
       });


    }




    private void showOnRecyclerView() {
        recyclerView = activityMainBinding.rvMovies;
        adapter = new MovieAdapter(this);
        adapter.submitList(movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
