package com.example.popularmovies.repository.network;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.popularmovies.model.Movie;

// The MovieDataSourceFactory is responsible for creating a DataSource.
public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie>{

    private MutableLiveData<MovieDataSource> movieLiveDataSource = new MutableLiveData<>();
    private MovieDataSource movieDataSource;
    private String mSortOrder;

    public MovieDataSourceFactory(String sortOrder) {
        mSortOrder = sortOrder;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        // Getting our Data source object
        movieDataSource = new MovieDataSource(mSortOrder);
        // Posting the Data source to get the values
        movieLiveDataSource.postValue(movieDataSource);
        // Returning the Data source
        return movieDataSource;
    }
    public MutableLiveData<MovieDataSource> getMovieLiveDataSource() {
        return movieLiveDataSource;
    }
}
