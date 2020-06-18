package com.example.popularmoviesstage1.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.popularmoviesstage1.models.Movie;
import com.example.popularmoviesstage1.network.MovieDataSource;
import com.example.popularmoviesstage1.network.MovieDataSourceFactory;
import com.example.popularmoviesstage1.utils.Constants;

import java.util.concurrent.Executors;


public class MovieViewModel extends ViewModel {

    public LiveData<PagedList<Movie>> moviePagedList;
    private LiveData<MovieDataSource> liveDataSource;
    private DataSource<Integer, Movie> mostRecentDataSource;

    public MovieViewModel(String sortCriteria) {
        MovieDataSourceFactory factory = new MovieDataSourceFactory(sortCriteria);
        mostRecentDataSource = factory.create();
        liveDataSource = factory.getMovieLiveDataSource();

        // Get PagedList configuration
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(Constants.PAGE_SIZE).build();

        // Build the paged list
        moviePagedList = new LivePagedListBuilder(factory, pagedListConfig)
                .setFetchExecutor(Executors.newFixedThreadPool(Constants.NUMBER_OF_THREADS))
                .build();
    }
}
