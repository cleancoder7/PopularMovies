package com.example.popularmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.popularmovies.model.Review;
import com.example.popularmovies.repository.MovieRepository;

public class ReviewViewModel extends AndroidViewModel {

    private LiveData<PagedList<Review>> reviewPagedList;

    public ReviewViewModel(@NonNull Application application, int movieId) {
        super(application);
        MovieRepository movieRepository = MovieRepository.getInstance(application);
        reviewPagedList = movieRepository.getReviewPagedList(movieId);
    }

    public LiveData<PagedList<Review>>  getReviewPagedList() {
        return reviewPagedList;
    }

}
