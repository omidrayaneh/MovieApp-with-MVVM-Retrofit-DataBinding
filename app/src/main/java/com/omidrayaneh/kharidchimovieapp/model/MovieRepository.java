package com.omidrayaneh.kharidchimovieapp.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.omidrayaneh.kharidchimovieapp.R;
import com.omidrayaneh.kharidchimovieapp.service.MovieDataService;
import com.omidrayaneh.kharidchimovieapp.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

  private   Application application;

    private ArrayList<Movie> movies=new ArrayList<>();

    private MutableLiveData<List<Movie>> mutableLiveData =new MutableLiveData<>();

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {

        final MovieDataService movieDataService= RetrofitInstance.getService();

        Call<MovieDBResponse> call =movieDataService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {

                MovieDBResponse movieDBResponse=response.body();

                if (movieDBResponse!=null && movieDBResponse.getResults()!=null){

                    movies=(ArrayList<Movie>)movieDBResponse.getResults();

                    mutableLiveData.setValue(movies);

                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }


}
