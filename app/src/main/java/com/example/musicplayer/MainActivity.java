package com.example.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Song> songList = new ArrayList<>();
    private List<Moment> momentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initSongsView();
//        initSongsList();
        initSquareList();
        initSquareView();
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
    private void initSongsView(){                                  //初始化RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SongAdapter adapter = new SongAdapter(songList);
        recyclerView.setAdapter(adapter);

    }

    //初始化音村列表
    private void initSquareList(){
        for(int i = 0; i<2; i++){
            Moment a = new Moment(
                    R.drawable.test1,"ilydsofaeojfijsdfljseoifjasldkjioejfsdlufei",
                    R.drawable.testtwo,"LiGuanFeng");
            momentList.add(a);
            Moment b = new Moment(
                    R.drawable.test2,"sdfa;dkjioejfsdlufei",
                    R.drawable.testone,"YANGLONG");
            momentList.add(b);
            Moment c = new Moment(
                    R.drawable.test1,"ilydsofaeojfijsdfljseoifjasl;dkjioejfsdlufsdfsfsdfsefsdfafei",
                    R.drawable.testthree,"LiGuanFeng");
            momentList.add(c);
            Moment d = new Moment(
                    R.drawable.test3,"程度不太对就是房间阿恩家了省点经费IE加法兰克是大家练腹肌蓝色空间放is对积分lkjsdf",
                    R.drawable.testthree,"CHENYU");
            momentList.add(d);
            Moment e = new Moment(
                    R.drawable.test3,"恩家了省点经费IE加法兰克是大家练腹肌蓝色空间放is对积分;dkjioejfsdlufei",
                    R.drawable.test2,"BIKAQIU");
            momentList.add(e);
            Moment f = new Moment(
                    R.drawable.test2,"i间放is对积分",
                    R.drawable.testone,"MEI");
            momentList.add(f);
            Moment g = new Moment(
                    R.drawable.test1,"恩家了省点经费IE加法兰克是大;dkjioejfsdlufei",
                    R.drawable.testone,"LiGuanFeng");
            momentList.add(g);

        }

    }

    //初始化音村视图
    private void initSquareView(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MomentAdapter adapter = new MomentAdapter(momentList);
        recyclerView.setAdapter(adapter);
    }


}
