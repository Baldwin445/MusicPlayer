package com.example.musicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baldwin on 19/11/30.
 */

public class SongFragment extends Fragment {
    private List<Song> songList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.songlist_layout, container, false);
        initSongsList();
        initSongsView(view);
        return view;
    }

    //初始化音乐列表
    private void initSongsList(){                                  //初始化音乐列表
        for(int i = 0; i< 2; i++){
            Song s1 = new Song("鸡哥学潮汕话", "李冠锋");
            songList.add(s1);
            Song s2 = new Song("戏精鸡哥", "李冠锋");
            songList.add(s2);
            Song s3 = new Song("鸡哥与天线宝宝", "李冠锋");
            songList.add(s3);
            Song s4 = new Song("鸡哥尬唱", "李冠锋");
            songList.add(s4);
            Song s5 = new Song("鸡哥KTV", "李冠锋");
            songList.add(s5);
            Song s6 = new Song("鸡哥学潮汕话", "李冠锋");
            songList.add(s6);
            Song s7 = new Song("戏精鸡哥", "李冠锋");
            songList.add(s7);
            Song s8 = new Song("鸡哥与天线宝宝", "李冠锋");
            songList.add(s8);
            Song s9 = new Song("鸡哥尬唱", "李冠锋");
            songList.add(s9);

        }
    }

    //初始化音乐视图
    private void initSongsView(View view){                                  //初始化RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.songlist_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        SongAdapter adapter = new SongAdapter(songList);
        recyclerView.setAdapter(adapter);

    }
}
