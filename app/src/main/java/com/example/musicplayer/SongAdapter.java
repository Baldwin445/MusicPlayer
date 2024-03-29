package com.example.musicplayer;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import dbconnect.SongInfo;
import player.PlayerActivity;

/**
 * Created by Baldwin on 19/11/27.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{
    private List<Song> mSongList;
    private List<SongInfo> list;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View songView;
        TextView name,singer;

        Button delete;

        public ViewHolder(View view){
            super(view);
            songView = view;
            name = (TextView) view.findViewById(R.id.song_name);
            singer = (TextView) view.findViewById(R.id.singer);
            delete = (Button)view.findViewById(R.id.delete);
        }
    }

    public SongAdapter(List<Song> songList){
        mSongList = songList;
        list = DataSupport.select("fileName, title, artist, url")
                .find(SongInfo.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //进入播放页
                int position = holder.getAdapterPosition();
                Song song = mSongList.get(position);
                Log.d("ClickPosition", position+"");

                PlayerActivity.actionStart(v.getContext(), list, position);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSongList.remove(holder.getAdapterPosition());
                list.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyDataSetChanged();

            }
        });
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
