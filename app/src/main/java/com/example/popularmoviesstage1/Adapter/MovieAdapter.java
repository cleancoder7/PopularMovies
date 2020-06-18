package com.example.popularmoviesstage1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage1.databinding.MovieItemBinding;
import com.example.popularmoviesstage1.models.Movie;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {
    private Context mContext;

    final private MovieAdapterOnClickListener mOnClickListener;

    public interface MovieAdapterOnClickListener {
        void onListItemClick(Movie clickedMovieItem, ImageView moviePoster);
    }

    public MovieAdapter(MovieAdapterOnClickListener listener) {
        super(DIFF_CALLBACK);
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        MovieItemBinding binding = MovieItemBinding.inflate(inflater, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        if (movie != null) {
            holder.bind(movie);
        } else {
            Toast.makeText(mContext, "Movie is null", Toast.LENGTH_LONG).show();
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final MovieItemBinding binding;

        public MovieViewHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.ivMoviePoster.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Movie clickedMovieItem = getItem(clickedPosition);
            mOnClickListener.onListItemClick(clickedMovieItem, binding.ivMoviePoster);
        }
    }

    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getMovieId().equals(newItem.getMovieId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };
}
