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



public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private List<Song> songList;

    public SongAdapter(List<Song> songList) {
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.textViewSongTitle.setText(song.getTitle());
        Glide.with(holder.imageViewSong.getContext()).load(song.getImageUrl()).into(holder.imageViewSong);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewSong;
        TextView textViewSongTitle;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSong = itemView.findViewById(R.id.imageViewSong);
            textViewSongTitle = itemView.findViewById(R.id.textViewSongTitle);
        }
    }
}
