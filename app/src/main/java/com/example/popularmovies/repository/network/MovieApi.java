package com.example.popularmovies.repository.network;

import com.example.popularmovies.model.MovieApiResponse;
import com.example.popularmovies.model.CreditApiResponse;
import com.example.popularmovies.model.ReviewApiResponse;
import com.example.popularmovies.model.TrailerApiResponse;

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

    @GET("movie/{id}/credits")
    Call<CreditApiResponse> getCredits(
            @Path("id") int id,
            @Query("api_key") String apiKey
    );

    @GET("movie/{id}/reviews")
    Call<ReviewApiResponse> getReviews(
            @Path("id") int id,
            @Query("page") int page,
            @Query("api_key") String apiKey
    );

    @GET("movie/{id}/videos")
    Call<TrailerApiResponse> getTrailers(
            @Path("id") int id,
            @Query("api_key") String apiKey
    );
}
