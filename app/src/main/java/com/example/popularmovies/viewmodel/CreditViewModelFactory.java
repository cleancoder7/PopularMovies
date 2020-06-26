package com.example.popularmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CreditViewModelFactory implements ViewModelProvider.Factory {

    private final int mMovieId;
    private final Application application;

    public CreditViewModelFactory(Application application, int movieId) {
        this.mMovieId = movieId;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new CreditViewModel(application, mMovieId);
    }
}
