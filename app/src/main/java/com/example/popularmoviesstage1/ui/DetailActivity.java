package com.example.popularmoviesstage1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.popularmoviesstage1.R;
import com.example.popularmoviesstage1.databinding.ActivityDetailBinding;
import com.example.popularmoviesstage1.models.Movie;
import com.example.popularmoviesstage1.utils.Constants;

public class DetailActivity extends AppCompatActivity {

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.EXTRA_MOVIE)) {
            movie = (Movie) intent.getSerializableExtra(Constants.EXTRA_MOVIE);
        }
        if (movie != null) {
            binding.collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this , android.R.color.transparent));
            binding.setMovie(movie);
            binding.executePendingBindings();
            toolbar.setTitle(movie.getMovieTitle());
        }
        binding.ivMovieDetailBackdrop.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
    }
}
