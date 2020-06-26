package com.example.popularmovies.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.popularmovies.model.CreditApiResponse;
import com.example.popularmovies.repository.MovieRepository;

public class CreditViewModel extends AndroidViewModel {

    private final LiveData<CreditApiResponse> mCreditResponse;

    public CreditViewModel(Application application, int movieId) {
        super(application);
        mCreditResponse = MovieRepository.getInstance(application).getCreditResponse(movieId);
    }

    public LiveData<CreditApiResponse> getCreditResponse() {
        return mCreditResponse;
    }
}
