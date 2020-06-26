package com.example.popularmovies.repository.network;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.popularmovies.model.Review;

public class ReviewDataSourceFactory extends DataSource.Factory<Integer, Review> {

    private MutableLiveData<ReviewDataSource> reviewLiveDataSource = new MutableLiveData<>();
    private ReviewDataSource reviewDataSource;
    private int mMovieId;

    public ReviewDataSourceFactory(int movieId) {
        mMovieId = movieId;
    }

    @NonNull
    @Override
    public DataSource<Integer, Review> create() {
        // Getting our Data source object
        reviewDataSource = new ReviewDataSource(mMovieId);
        // Posting the Data source to get the values
        reviewLiveDataSource.postValue(reviewDataSource);
        // Returning the Data source
        return reviewDataSource;
    }

    public MutableLiveData<ReviewDataSource> getReviewLiveDataSource() {
        return reviewLiveDataSource;
    }
}
