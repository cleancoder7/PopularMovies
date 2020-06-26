package com.example.popularmovies.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.popularmovies.R;
import com.example.popularmovies.util.Constants;
import com.squareup.picasso.Picasso;

public class RetrofitBindingAdapter {
    @BindingAdapter("posterImageUrl")
    public static void setImageResource(ImageView view, String imageUrl) {
        Picasso.get()
                .load(Constants.IMAGE_BASE_URL + Constants.IMAGE_FILE_SIZE + imageUrl)
                .fit()
                .centerInside()
                .into(view);
    }

    @BindingAdapter("backdropImageUrl")
    public static void setBackdropImageResource(ImageView view, String imageUrl) {
        Picasso.get()
                .load(Constants.IMAGE_BASE_URL + Constants.BACKDROP_FILE_SIZE + imageUrl)
                .fit()
                .centerInside()
                .into(view);
    }

    @BindingAdapter("castProfilePath")
    public static void setCastImageResource(ImageView view, String profilePath) {
        Picasso.get()
                .load(Constants.IMAGE_BASE_URL + Constants.IMAGE_FILE_SIZE + profilePath)
                .fit()
                .placeholder(R.drawable.ic_person)
                .centerInside()
                .into(view);
    }

    @BindingAdapter("trailerThumbnailUrl")
    public static void setTrailerThumbnailResource(ImageView view, String trailerKey) {
        Picasso.get()
                .load(Constants.YOUTUBE_THUMBNAIL_BASE_URL + trailerKey + Constants.YOUTUBE_THUMBNAIL_URL_JPG)
                .fit()
                .centerInside()
                .into(view);
    }
}
