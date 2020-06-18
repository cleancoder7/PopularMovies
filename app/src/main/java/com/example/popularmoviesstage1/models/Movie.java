package com.example.popularmoviesstage1.models;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable{

    @SerializedName("id")
    private String movieId;
    @SerializedName("title")
    private String movieTitle;
    @SerializedName("vote_average")
    private String movieVote;
    @SerializedName("overview")
    private String movieDescription;
    @SerializedName("release_date")
    private String movieReleaseDate;
    @SerializedName("original_language")
    private String movieLanguage;
    @SerializedName("poster_path")
    private String moviePoster;
    @SerializedName("backdrop_path")
    private String movieBackdrop;


    public Movie(String movieId, @NonNull String movieTitle, String movieVote, String movieDescription, String movieReleaseDate, String movieLanguage, String moviePoster, String movieBackdrop) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieVote = movieVote;
        this.movieDescription = movieDescription;
        this.movieReleaseDate = movieReleaseDate;
        this.movieLanguage = movieLanguage;
        this.moviePoster = moviePoster;
        this.movieBackdrop = movieBackdrop;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getMovieVote() {
        return movieVote;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public String getMovieBackdrop() {
        return movieBackdrop;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setMovieVote(String movieVote) {
        this.movieVote = movieVote;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public void setMovieBackdrop(String movieBackdrop) {
        this.movieBackdrop = movieBackdrop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (getClass() != o.getClass())
            return false;
        Movie other = (Movie) o;
        return movieId.equals(other.movieId) &&
            movieTitle.equals(other.movieTitle) &&
            movieVote.equals(other.movieVote) &&
            movieDescription.equals(other.movieDescription) &&
            movieReleaseDate.equals(other.movieReleaseDate) &&
            movieLanguage.equals(other.movieLanguage) &&
            moviePoster.equals(other.moviePoster) &&
            movieBackdrop.equals(other.movieBackdrop);
    }
}
