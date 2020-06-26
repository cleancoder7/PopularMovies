package com.example.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "movies")
public class Movie implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int movieId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String movieTitle;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private String movieVote;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String movieDescription;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String movieReleaseDate;

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    private String movieLanguage;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String moviePoster;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String movieBackdrop;

    public Movie(int movieId, @NonNull String movieTitle, String movieVote, String movieDescription, String movieReleaseDate, String movieLanguage, String moviePoster, String movieBackdrop) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieVote = movieVote;
        this.movieDescription = movieDescription;
        this.movieReleaseDate = movieReleaseDate;
        this.movieLanguage = movieLanguage;
        this.moviePoster = moviePoster;
        this.movieBackdrop = movieBackdrop;
    }

    protected Movie(Parcel in) {
        movieId = in.readInt();
        movieTitle = in.readString();
        movieVote = in.readString();
        movieDescription = in.readString();
        movieReleaseDate = in.readString();
        movieLanguage = in.readString();
        moviePoster = in.readString();
        movieBackdrop = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieId);
        dest.writeString(movieTitle);
        dest.writeString(movieVote);
        dest.writeString(movieDescription);
        dest.writeString(movieReleaseDate);
        dest.writeString(movieLanguage);
        dest.writeString(moviePoster);
        dest.writeString(movieBackdrop);
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieVote() {
        return movieVote;
    }

    public void setMovieVote(String movieVote) {
        this.movieVote = movieVote;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieBackdrop() {
        return movieBackdrop;
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
        return movieId == other.movieId &&
                movieTitle.equals(other.movieTitle) &&
                movieVote.equals(other.movieVote) &&
                movieDescription.equals(other.movieDescription) &&
                movieReleaseDate.equals(other.movieReleaseDate) &&
                movieLanguage.equals(other.movieLanguage) &&
                moviePoster.equals(other.moviePoster) &&
                movieBackdrop.equals(other.movieBackdrop);
    }
}
