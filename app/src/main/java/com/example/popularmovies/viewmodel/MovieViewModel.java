package com.example.popularmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.popularmovies.model.Movie;
import com.example.popularmovies.repository.MovieRepository;


public class MovieViewModel extends AndroidViewModel {

    private LiveData<PagedList<Movie>> moviePagedList;
    private LiveData<PagedList<Movie>> favoriteMoviePagedList;

    public MovieViewModel(@NonNull Application application, String sortCriteria) {
        super(application);
        MovieRepository repository = MovieRepository.getInstance(application);
        moviePagedList = repository.getMoviePagedList(sortCriteria);
        favoriteMoviePagedList = repository.getFavoriteMoviePagedList();
    }

    public LiveData<PagedList<Movie>> getMoviePagedList() {
        return moviePagedList;
    }

    public LiveData<PagedList<Movie>> getFavoriteMoviePagedList() {
        return favoriteMoviePagedList;
    }
}
