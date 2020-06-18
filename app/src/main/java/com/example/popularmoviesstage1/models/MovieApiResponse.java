package com.example.popularmoviesstage1.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieApiResponse  {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Movie> movies;

    public int getPage() {
        return page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
