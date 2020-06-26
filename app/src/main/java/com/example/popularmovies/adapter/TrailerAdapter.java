package com.example.popularmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.databinding.TrailerItemBinding;
import com.example.popularmovies.model.Trailer;
import com.example.popularmovies.util.Constants;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private List<Trailer> mTrailers;

    private final TrailerAdapterOnClickListener mOnClickListener;

    public interface TrailerAdapterOnClickListener {
        void onListItemClick(String trailerUrl);
    }

    public TrailerAdapter(TrailerAdapterOnClickListener listener) {
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public TrailerAdapter.TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        TrailerItemBinding binding = TrailerItemBinding.inflate(inflater, parent, false);
        return new TrailerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.TrailerViewHolder holder, int position) {
        Trailer trailer = mTrailers.get(position);
        holder.bind(trailer);
    }

    @Override
    public int getItemCount() {
        return mTrailers != null ? mTrailers.size() : 0;
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TrailerItemBinding binding;

        TrailerViewHolder(TrailerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.cardTrailer.setOnClickListener(this);
        }
        void bind(Trailer trailer) {
            binding.setTrailer(trailer);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Trailer trailer = mTrailers.get(clickedPosition);
            String trailerUrl = Constants.YOUTUBE_BASE_URL + trailer.getTrailerKey();
            mOnClickListener.onListItemClick(trailerUrl);
        }

    }
    public void submitTrailerList(List<Trailer> trailers) {
        mTrailers = trailers;
        notifyDataSetChanged();
    }
}
