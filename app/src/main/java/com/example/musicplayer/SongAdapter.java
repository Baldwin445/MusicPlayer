package com.example.musicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Baldwin on 19/11/27.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{
    private List<Song> mSongList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,singer;

        public ViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.song_name);
            singer = (TextView) view.findViewById(R.id.singer);
        }
    }

    public SongAdapter(List<Song> songList){
        mSongList = songList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mSongList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = mSongList.get(position);
        holder.name.setText(song.getName());
        holder.singer.setText(song.getSinger());

    }
}
