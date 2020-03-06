package com.omidrayaneh.kharidchimovieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.omidrayaneh.kharidchimovieapp.model.Movie;
import com.omidrayaneh.kharidchimovieapp.model.MovieDataSource;
import com.omidrayaneh.kharidchimovieapp.model.MovieDataSourceFactory;
import com.omidrayaneh.kharidchimovieapp.model.MovieRepository;
import com.omidrayaneh.kharidchimovieapp.service.MovieDataService;
import com.omidrayaneh.kharidchimovieapp.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository repository;

    LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> moviesPageList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository=new MovieRepository(application);

        MovieDataService movieDataService= RetrofitInstance.getService();

        MovieDataSourceFactory factory=new MovieDataSourceFactory(movieDataService,application);

        movieDataSourceLiveData=factory.getMutableLiveData();

        PagedList.Config config=(new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor= Executors.newFixedThreadPool(5);

        moviesPageList=(new LivePagedListBuilder<Long,Movie>(factory,config))
                .setFetchExecutor(executor)
                .build();



    }


    public LiveData<List<Movie>> getAllMovies(){

        return repository.getMutableLiveData();
    }

    public LiveData<PagedList<Movie>> getMoviesPageList() {
        return moviesPageList;
    }
}
