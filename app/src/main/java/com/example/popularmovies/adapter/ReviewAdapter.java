package com.example.popularmovies.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.databinding.ReviewItemBinding;
import com.example.popularmovies.model.Review;

public class ReviewAdapter extends PagedListAdapter<Review, ReviewAdapter.ReviewViewHolder> {

    public ReviewAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ReviewItemBinding binding = ReviewItemBinding.inflate(inflater, parent, false);
        return new ReviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = getItem(position);
        holder.bind(review);
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        private final ReviewItemBinding binding;

        ReviewViewHolder(ReviewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.reviewLayout.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    Review clickedReviewItem = getItem(clickedPosition);
                    if (clickedReviewItem != null) {
                        clickedReviewItem.setExpandable(!clickedReviewItem.isExpandable());
                    }
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

        void bind(Review review) {
            binding.setReview(review);
            binding.executePendingBindings();
            if (review.isExpandable()) {
                binding.tvReviewContentExpandable.setVisibility(View.VISIBLE);
                binding.tvReviewContent.setVisibility(View.GONE);
            } else {
                binding.tvReviewContentExpandable.setVisibility(View.GONE);
                binding.tvReviewContent.setVisibility(View.VISIBLE);
            }
        }
    }

    private static DiffUtil.ItemCallback<Review> DIFF_CALLBACK = new DiffUtil.ItemCallback<Review>() {
        @Override
        public boolean areItemsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem.equals(newItem);
        }
    };

}
