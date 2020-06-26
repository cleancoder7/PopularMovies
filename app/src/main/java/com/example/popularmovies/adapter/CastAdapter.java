package com.example.popularmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.databinding.CastItemBinding;
import com.example.popularmovies.model.Cast;


import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder>{

    private List<Cast> mCasts;

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        CastItemBinding binding = CastItemBinding.inflate(inflater, parent, false);
        return new CastViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast cast = mCasts.get(position);
        holder.bind(cast);
    }

    @Override
    public int getItemCount() {
        return mCasts != null ? mCasts.size() : 0;
    }

    class CastViewHolder extends RecyclerView.ViewHolder {
        private final CastItemBinding binding;

        CastViewHolder(CastItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(Cast cast) {
            binding.setCast(cast);
            binding.executePendingBindings();
        }
    }
    public void submitCastList(List<Cast> casts) {
        mCasts = casts;
        notifyDataSetChanged();
    }
}
