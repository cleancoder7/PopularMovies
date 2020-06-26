package com.example.popularmovies.repository.network;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.popularmovies.util.Constants;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.MovieApiResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {

    //The sort order of the movies.
    private String mSortOrder;

    MovieDataSource(String sortOrder) {
        mSortOrder = sortOrder;
    }

    private Call<MovieApiResponse> request(int page) {
        return RetrofitClient.getInstance()
                .getMovieApi()
                .getMovies(mSortOrder, page, Constants.API_KEY);
    }

    // This method is called first to initialize a PageList with data.
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        try {
            Response<MovieApiResponse> response = request(Constants.FIRST_PAGE).execute();
            MovieApiResponse movieApiResponse = response.body();
            callback.onResult(movieApiResponse.getMovies(), null, Constants.FIRST_PAGE + 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        int currentPage = params.key;
        request(currentPage)
                .enqueue(new Callback<MovieApiResponse>() {
                    @Override
                    public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                        MovieApiResponse movieApiResponse = response.body();
                        if (response.isSuccessful() && movieApiResponse != null) {
                            // If the response has next page, increment the next page number
                            Integer key = (movieApiResponse.getMovies().size() == Constants.PAGE_SIZE) ? (currentPage + 1) : null;
                            callback.onResult(movieApiResponse.getMovies(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                    }
                });
    }
}
