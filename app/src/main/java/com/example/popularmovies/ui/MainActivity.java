package com.example.popularmovies.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.popularmovies.adapter.MovieAdapter;
import com.example.popularmovies.viewmodel.MovieViewModel;
import com.example.popularmovies.viewmodel.MovieViewModelFactory;
import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ActivityMainBinding;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.settings.SettingsActivity;
import com.example.popularmovies.util.Constants;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickListener {

    private String mSortCriteria;
    private MovieAdapter mMovieAdapter;
    private MovieViewModel mMoviesViewModel;
    private RecyclerView mRecyclerView;
    private ActivityMainBinding binding;
    private LinearLayout networkConnectionError;
    private Button retryButton;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Toolbar toolbar = binding.movieToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);

        mRecyclerView = binding.rvMovies;
        networkConnectionError = binding.networkErrorLayout;
        retryButton = binding.retryButton;

        // Get the sort criteria currently set in Preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSortCriteria = sharedPreferences.getString(getString(R.string.pref_sort_by_key), getString(R.string.pref_sort_by_default));

        mMoviesViewModel = getViewModel();
        initAdapter();

        if (mSortCriteria.equals(getString(R.string.pref_sort_by_favorites))) {
            updateFavoriteMovieUI();
        } else {
            updateUI();
        }
    }

    private MovieViewModel getViewModel() {
        return new ViewModelProvider(this, new MovieViewModelFactory(getApplication(), mSortCriteria)).get(MovieViewModel.class);
    }

    private void initAdapter() {
        mMovieAdapter = new MovieAdapter(this);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, Constants.GRID_SPAN_COUNT_PORTRAIT));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, Constants.GRID_SPAN_COUNT_LANDSCAPE));
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMovieAdapter);
    }

    private void setMovieList() {
        mMoviesViewModel.getMoviePagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                if (movies != null) {
                    mMovieAdapter.submitList(movies);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    boolean hasNetwork() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
        return capabilities != null
                && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
    }

    private void updateUI() {
        // If there is no network on app startup, show the error page with retry button.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasNetwork()) {
                mRecyclerView.setVisibility(View.GONE);
                networkConnectionError.setVisibility(View.VISIBLE);
                retryButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (hasNetwork()) {
                            networkConnectionError.setVisibility(View.GONE);
                            mRecyclerView.setVisibility(View.VISIBLE);
                            setMovieList();
                        }
                    }
                });
            } else {
                networkConnectionError.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                setMovieList();
            }
        }
    }

    private void updateFavoriteMovieUI() {
        mMoviesViewModel.getFavoriteMoviePagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                if (movies.isEmpty()) {
                    mRecyclerView.setVisibility(View.GONE);
                    binding.emptyFavoriteLayout.setVisibility(View.VISIBLE);
                } else {
                    mRecyclerView.setVisibility(View.VISIBLE);
                    binding.emptyFavoriteLayout.setVisibility(View.GONE);
                    mMovieAdapter.submitList(movies);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onListItemClick(Movie clickedMovieItem, ImageView moviePoster) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.EXTRA_MOVIE, clickedMovieItem);
        intent.putExtra(Constants.NETWORK_STATE, hasNetwork());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        // Return true so that the main_menu is displayed in the Toolbar.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
