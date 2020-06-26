package com.example.popularmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.popularmovies.model.Movie;
import com.example.popularmovies.repository.MovieRepository;


public class FavoriteViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance(application);
    }

    public void insert(Movie movie) {
        repository.insert(movie);
    }

    public void delete(Movie movie) {
        repository.delete(movie);
    }

    public LiveData<Movie> getFavoriteMovieById(int movieId) {
        return repository.getFavoriteMovieByMovieId(movieId);
    }
}
