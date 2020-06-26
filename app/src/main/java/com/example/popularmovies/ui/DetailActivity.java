package com.example.popularmovies.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.popularmovies.adapter.CastAdapter;
import com.example.popularmovies.adapter.ReviewAdapter;
import com.example.popularmovies.adapter.TrailerAdapter;
import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ActivityDetailBinding;
import com.example.popularmovies.model.CreditApiResponse;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.Review;
import com.example.popularmovies.model.Trailer;
import com.example.popularmovies.model.TrailerApiResponse;
import com.example.popularmovies.util.Constants;
import com.example.popularmovies.viewmodel.CreditViewModel;
import com.example.popularmovies.viewmodel.CreditViewModelFactory;
import com.example.popularmovies.viewmodel.FavoriteViewModel;
import com.example.popularmovies.viewmodel.FavoriteViewModelFactory;
import com.example.popularmovies.viewmodel.ReviewViewModel;
import com.example.popularmovies.viewmodel.ReviewViewModelFactory;
import com.example.popularmovies.viewmodel.TrailerViewModel;
import com.example.popularmovies.viewmodel.TrailerViewModelFactory;

import java.util.List;

import static com.google.android.material.snackbar.Snackbar.*;


public class DetailActivity extends AppCompatActivity implements TrailerAdapter.TrailerAdapterOnClickListener {

    private ActivityDetailBinding binding;
    private Movie movie;
    private int mMovieId;
    private boolean mIsFavorite;
    private Trailer mFirstTrailer;
    private TrailerAdapter mTrailerAdapter;
    private CastAdapter mCastAdapter;
    private ReviewAdapter mReviewAdapter;
    private FavoriteViewModel mFavoriteViewModel;
    private RecyclerView recyclerViewReview;
    private boolean hasNetwork;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.EXTRA_MOVIE)) {
            movie = intent.getParcelableExtra(Constants.EXTRA_MOVIE);
        }

        if (intent.hasExtra(Constants.NETWORK_STATE)) {
            hasNetwork = intent.getExtras().getBoolean(Constants.NETWORK_STATE);
        }

        if (movie != null) {
            binding.collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
            binding.setMovie(movie);
            binding.executePendingBindings();
            mMovieId = movie.getMovieId();
            getSupportActionBar().setTitle(movie.getMovieTitle());
        }

        if (hasNetwork) {
            setUpCastAdapter();
            setCastList();

            setUpTrailerAdapter();
            setTrailerList();

            setUpReviewAdapter();
            setReviewList();
        } else {
            binding.contentDetail.cvCast.setVisibility(View.GONE);
            binding.contentDetail.cvTrailer.setVisibility(View.GONE);
            binding.contentDetail.cvReview.setVisibility(View.GONE);
        }

        setFavoriteViewModel();
        setIsFavorite();
    }

    private void setUpCastAdapter() {
        mCastAdapter = new CastAdapter();
        RecyclerView recyclerViewCast = binding.contentDetail.rvCast;
        recyclerViewCast.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewCast.setAdapter(mCastAdapter);
    }

    private void setCastList() {
        CreditViewModel creditViewModel = new ViewModelProvider(this, new CreditViewModelFactory(this.getApplication(), mMovieId)).get(CreditViewModel.class);
        creditViewModel.getCreditResponse().observe(this, new Observer<CreditApiResponse>() {

            @Override
            public void onChanged(CreditApiResponse creditApiResponse) {
                if (creditApiResponse != null) {
                    mCastAdapter.submitCastList(creditApiResponse.getCast());
                }
            }
        });
    }

    private void setTrailerList() {
        TrailerViewModel trailerViewModel = new ViewModelProvider(this, new TrailerViewModelFactory(this.getApplication(), mMovieId)).get(TrailerViewModel.class);
        trailerViewModel.getTrailerResponse().observe(this, new Observer<TrailerApiResponse>() {

            @Override
            public void onChanged(TrailerApiResponse trailerApiResponse) {
                if (trailerApiResponse != null) {
                    List<Trailer> trailers = trailerApiResponse.getTrailers();
                    mTrailerAdapter.submitTrailerList(trailers);

                    if (!trailers.isEmpty()) {
                        mFirstTrailer = trailers.get(0);
                    }
                }
            }
        });
    }

    private void setUpTrailerAdapter() {
        mTrailerAdapter = new TrailerAdapter(this);
        RecyclerView recyclerViewTrailer = binding.contentDetail.rvTrailer;
        recyclerViewTrailer.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewTrailer.setAdapter(mTrailerAdapter);
    }

    private void setUpReviewAdapter() {
        mReviewAdapter = new ReviewAdapter();
        recyclerViewReview = binding.contentDetail.rvReview;
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewReview.setLayoutManager(
                mLayoutManager);
        recyclerViewReview.setAdapter(mReviewAdapter);

        DividerItemDecoration itemDecor = new DividerItemDecoration(recyclerViewReview.getContext(), mLayoutManager.getOrientation());
        recyclerViewReview.addItemDecoration(itemDecor);
    }

    private void setReviewList() {
        ReviewViewModel reviewViewModel = new ViewModelProvider(this, new ReviewViewModelFactory(this.getApplication(), mMovieId)).get(ReviewViewModel.class);
        reviewViewModel.getReviewPagedList().observe(this, new Observer<PagedList<Review>>() {
            @Override
            public void onChanged(PagedList<Review> reviews) {
                if (reviews.isEmpty()) {
                    recyclerViewReview.setVisibility(View.GONE);
                    binding.contentDetail.tvNoReview.setVisibility(View.VISIBLE);
                } else {
                    recyclerViewReview.setVisibility(View.VISIBLE);
                    binding.contentDetail.tvNoReview.setVisibility(View.GONE);
                    mReviewAdapter.submitList(reviews);
                }
            }
        });
    }

    private void setFavoriteViewModel() {
        mFavoriteViewModel = new ViewModelProvider(this, new FavoriteViewModelFactory(getApplication())).get(FavoriteViewModel.class);
    }

    private void setIsFavorite() {
        mFavoriteViewModel.getFavoriteMovieById(mMovieId).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                if (movie != null) mIsFavorite = true;
                else mIsFavorite = false;
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public void onListItemClick(String trailerUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        menu.findItem(R.id.action_favorite).setIcon(mIsFavorite ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                if (hasNetwork) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    if (mFirstTrailer != null) {
                        String shareText = getString(R.string.check_out) + movie.getMovieTitle() + getString(R.string.new_line) +Uri.parse((Constants.YOUTUBE_BASE_URL) + mFirstTrailer.getTrailerKey());
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                    }
                    String shareSubject = getString(R.string.check_out) + movie.getMovieTitle() + getString(R.string.trailer) ;
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

                    startActivity(Intent.createChooser(shareIntent, null));
                } else {
                    make(binding.coordinatorLayout, R.string.no_internet_connection, LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_favorite:
                onFavoriteClick(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onFavoriteClick(MenuItem item) {
        if (mIsFavorite) {
            mFavoriteViewModel.delete(movie);
            mIsFavorite = false;
            item.setIcon(R.drawable.ic_favorite_border);
            make(binding.coordinatorLayout, R.string.removed_from_favorites, LENGTH_SHORT).show();

        } else {
            mFavoriteViewModel.insert(movie);
            mIsFavorite = true;
            item.setIcon(R.drawable.ic_favorite);
            make(binding.coordinatorLayout, R.string.added_to_favorites, LENGTH_SHORT).show();
        }
    }
}
