package com.example.popularmovies.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.popularmovies.model.CreditApiResponse;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.Review;
import com.example.popularmovies.model.TrailerApiResponse;
import com.example.popularmovies.repository.database.MovieDao;
import com.example.popularmovies.repository.database.MovieDatabase;
import com.example.popularmovies.repository.network.MovieApi;
import com.example.popularmovies.repository.network.MovieDataSource;
import com.example.popularmovies.repository.network.MovieDataSourceFactory;
import com.example.popularmovies.repository.network.RetrofitClient;
import com.example.popularmovies.repository.network.ReviewDataSource;
import com.example.popularmovies.repository.network.ReviewDataSourceFactory;
import com.example.popularmovies.util.Constants;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository mInstance;
    private MovieDao movieDao;
    private MovieApi movieApi;

    private MovieRepository(Context context) {
        MovieDatabase database = MovieDatabase.getInstance(context);
        movieDao = database.movieDao();
        movieApi = RetrofitClient.getInstance().getMovieApi();
    }

    public static MovieRepository getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MovieRepository(context);
        }
        return mInstance;
    }

    public LiveData<PagedList<Movie>> getMoviePagedList(String sortCriteria) {
        MovieDataSourceFactory factory = new MovieDataSourceFactory(sortCriteria);
        DataSource<Integer, Movie> mostRecentDataSource = factory.create();
        LiveData<MovieDataSource> liveDataSource = factory.getMovieLiveDataSource();

        // Get PagedList configuration
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(Constants.PAGE_SIZE).build();

        // Build the paged list
        LiveData<PagedList<Movie>> moviePagedList = new LivePagedListBuilder(factory, pagedListConfig)
                .setFetchExecutor(Executors.newFixedThreadPool(Constants.NUMBER_OF_THREADS))
                .build();

        return moviePagedList;
    }

    public LiveData<PagedList<Movie>> getFavoriteMoviePagedList() {
        DataSource.Factory<Integer, Movie> favoriteMoviesDataSource = movieDao.getAllMovies();
        LiveData<PagedList<Movie>> favoriteMoviePagedList =
                new LivePagedListBuilder(favoriteMoviesDataSource, Constants.PAGE_SIZE)
                        .setFetchExecutor(Executors.newFixedThreadPool(Constants.NUMBER_OF_THREADS))
                        .build();
        return favoriteMoviePagedList;
    }

    public LiveData<PagedList<Review>> getReviewPagedList(int movieId) {
        ReviewDataSourceFactory factory = new ReviewDataSourceFactory(movieId);
        DataSource<Integer, Review> mostRecentDataSource = factory.create();
        LiveData<ReviewDataSource> liveDataSource = factory.getReviewLiveDataSource();

        // Get PagedList configuration
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(Constants.PAGE_SIZE).build();

        // Build the paged list
        LiveData<PagedList<Review>> reviewPagedList = new LivePagedListBuilder(factory, pagedListConfig)
                .setFetchExecutor(Executors.newFixedThreadPool(Constants.NUMBER_OF_THREADS))
                .build();
        return reviewPagedList;
    }

    public LiveData<CreditApiResponse> getCreditResponse(int movieId) {
        final MutableLiveData<CreditApiResponse> creditResponseData = new MutableLiveData<>();

        movieApi.getCredits(movieId, Constants.API_KEY)
                .enqueue(new Callback<CreditApiResponse>() {
                    @Override
                    public void onResponse(Call<CreditApiResponse> call, Response<CreditApiResponse> response) {
                        CreditApiResponse creditApiResponse = response.body();
                        if (response.isSuccessful() && creditApiResponse != null) {
                            creditResponseData.postValue(creditApiResponse);
                        }
                    }

                    @Override
                    public void onFailure(Call<CreditApiResponse> call, Throwable t) {
                    }
                });
        return creditResponseData;
    }

    public LiveData<TrailerApiResponse> getTrailerResponse(int movieId) {
        final MutableLiveData<TrailerApiResponse> trailerResponseData = new MutableLiveData<>();

        movieApi.getTrailers(movieId, Constants.API_KEY)
                .enqueue(new Callback<TrailerApiResponse>() {
                    @Override
                    public void onResponse(Call<TrailerApiResponse> call, Response<TrailerApiResponse> response) {
                        TrailerApiResponse trailerApiResponse = response.body();
                        if (response.isSuccessful() && trailerApiResponse != null) {
                            trailerResponseData.postValue(trailerApiResponse);
                        }
                    }

                    @Override
                    public void onFailure(Call<TrailerApiResponse> call, Throwable t) {
                    }
                });
        return trailerResponseData;
    }

    public void insert(Movie movie) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                movieDao.insert(movie);
            }
        });
    }

    public void delete(Movie movie) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                movieDao.delete(movie);
            }
        });
    }

    public LiveData<Movie> getFavoriteMovieByMovieId(int movieId) {
        return movieDao.getMovieById(movieId);
    }
}
