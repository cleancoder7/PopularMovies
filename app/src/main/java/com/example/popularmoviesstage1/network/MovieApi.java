package com.example.popularmoviesstage1.network;

import com.example.popularmoviesstage1.models.MovieApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/{sort}")
    Call<MovieApiResponse> getMovies(
            @Path("sort") String sortOrder,
            @Query("page") int page,
            @Query("api_key") String apiKey);
}
