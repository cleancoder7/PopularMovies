package com.example.popularmovies.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.popularmovies.model.TrailerApiResponse;
import com.example.popularmovies.repository.MovieRepository;

public class TrailerViewModel extends AndroidViewModel {

    private final LiveData<TrailerApiResponse> mTrailerResponse;

    public TrailerViewModel(Application application, int movieId) {
        super(application);
        mTrailerResponse = MovieRepository.getInstance(application).getTrailerResponse(movieId);
    }

    public LiveData<TrailerApiResponse> getTrailerResponse() {
        return mTrailerResponse;
    }
}
