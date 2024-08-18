package com.example.deber3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;



public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Album> albumList;
    private OnAlbumClickListener listener;

    public interface OnAlbumClickListener {
        void onAlbumClick(int position);
    }

    public AlbumAdapter(List<Album> albumList, OnAlbumClickListener listener) {
        this.albumList = albumList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.textViewAlbumTitle.setText(album.getTitle());
        Glide.with(holder.imageViewAlbum.getContext()).load(album.getImageUrl()).into(holder.imageViewAlbum);
        holder.itemView.setOnClickListener(v -> listener.onAlbumClick(position));
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAlbum;
        TextView textViewAlbumTitle;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAlbum = itemView.findViewById(R.id.imageViewAlbum);
            textViewAlbumTitle = itemView.findViewById(R.id.textViewAlbumTitle);
        }
    }
}
