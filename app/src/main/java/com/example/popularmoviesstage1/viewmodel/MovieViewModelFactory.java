package com.example.popularmoviesstage1.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.popularmoviesstage1.viewmodel.MovieViewModel;

public class MovieViewModelFactory implements ViewModelProvider.Factory{
    private String mSortCriteria;
    public MovieViewModelFactory (String sortCriteria) {
        mSortCriteria = sortCriteria;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieViewModel(mSortCriteria);
    }
}
