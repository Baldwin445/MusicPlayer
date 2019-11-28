package com.example.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Song> songList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSongsView();
        initSongsList();
    }

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

    private void initSongsView(){                                  //初始化RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SongAdapter adapter = new SongAdapter(songList);
        recyclerView.setAdapter(adapter);

    }


}
