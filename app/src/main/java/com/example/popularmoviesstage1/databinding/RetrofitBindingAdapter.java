package com.example.popularmoviesstage1.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.popularmoviesstage1.utils.Constants;
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
}
