package com.example.musicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import dbconnect.SongInfo;

/**
 * Created by Baldwin on 19/11/30.
 */

public class SongFragment extends Fragment {
    public List<Song> songList = new ArrayList<>();

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
        List<SongInfo> list = searchInfo();
        int size = list.size();
        Log.d("ListSize", size+"");
        for(int i = 0; i< size; i++){
            Song song = new Song(list.get(i).getTitle(), list.get(i).getArtist());
            songList.add(song);

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

    /**
     * 查询数据库已有书籍信息
     * @return 返回一个存有书籍信息的list
     */
    private List<SongInfo> searchInfo(){
        List<SongInfo> list = DataSupport.select("fileName, title, artist, url")
                .find(SongInfo.class);
        return list;
    }


}
