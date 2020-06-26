package com.example.popularmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MovieViewModelFactory implements ViewModelProvider.Factory{
    private final String sortCriteria;
    private final Application application;

    public MovieViewModelFactory (Application application, String sortCriteria) {
        this.application = application;
        this.sortCriteria = sortCriteria;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MovieViewModel(application, sortCriteria);
    }
}
