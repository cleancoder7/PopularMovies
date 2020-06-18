package com.example.popularmoviesstage1.utils;

public class Constants {
    // The base movie URL from TMDb
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    // The base image URL to fetch the image
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String BACKDROP_FILE_SIZE = "w500";
    public static final String IMAGE_FILE_SIZE = "w185";
    public static final int FIRST_PAGE = 1;
    // Size of each page loaded by the PagedList
    public static final int PAGE_SIZE = 20;
    public static final int GRID_SPAN_COUNT_PORTRAIT = 2;
    public static final int GRID_SPAN_COUNT_LANDSCAPE = 3;
    public static final int NUMBER_OF_THREADS = 2;
    // Extra for the movie to be received in the intent
    public static final String EXTRA_MOVIE = "movie";

    // TMDb API Key.
    // TODO: Replace with your own.
    public static final String API_KEY = "<API_KEY>";
}
