package com.example.popularmovies.repository.database;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.popularmovies.model.Movie;


@Dao
public interface MovieDao {

    @Insert
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM movies WHERE id = :movieId")
    LiveData<Movie> getMovieById(int movieId);

    @Transaction
    @Query("SELECT * FROM movies")
    DataSource.Factory<Integer, Movie> getAllMovies();
}

