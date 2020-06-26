package com.example.popularmovies.repository.network;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.popularmovies.model.Review;
import com.example.popularmovies.model.ReviewApiResponse;
import com.example.popularmovies.util.Constants;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewDataSource extends PageKeyedDataSource<Integer, Review> {

    private int mMovieId;

    ReviewDataSource(int movieId) {
        mMovieId = movieId;
    }

    private Call<ReviewApiResponse> request(int page) {
        return RetrofitClient.getInstance()
                .getMovieApi()
                .getReviews(mMovieId, page, Constants.API_KEY);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Review> callback) {
        try {
            Response<ReviewApiResponse> response = request(Constants.FIRST_PAGE).execute();
            ReviewApiResponse reviewApiResponse = response.body();
            callback.onResult(reviewApiResponse.getReviews(), null, Constants.FIRST_PAGE + 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Review> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Review> callback) {
        int currentPage = params.key;
        request(currentPage)
                .enqueue(new Callback<ReviewApiResponse>() {
                    @Override
                    public void onResponse(Call<ReviewApiResponse> call, Response<ReviewApiResponse> response) {
                        ReviewApiResponse reviewApiResponse = response.body();
                        if (response.isSuccessful() && reviewApiResponse != null) {
                            // If the response has next page, increment the next page number
                            Integer key = (reviewApiResponse.getReviews().size() == Constants.PAGE_SIZE) ? (currentPage + 1) : null;
                            callback.onResult(reviewApiResponse.getReviews(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<ReviewApiResponse> call, Throwable t) {
                    }
                });
    }
}
