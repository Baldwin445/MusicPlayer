package com.example.musicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

public class MomentFragment extends Fragment {
    private List<Moment> momentList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.moment_layout, container, false);
        initSquareList();
        initSquareView(view);

        return view;

    }

    //初始化音村列表
    private void initSquareList(){
        for(int i = 0; i<2; i++){
            Moment a = new Moment(
                    R.drawable.test3,"究竟为什么进入贰刺螈，想想看，到今天为止我进入二次元最初是因为好奇",
                    R.drawable.testtwo,"LiGuanFeng");
            momentList.add(a);
            Moment b = new Moment(
                    R.drawable.test2,"音乐小屋音乐推荐",
                    R.drawable.testone,"YANGLONG");
            momentList.add(b);
            Moment c = new Moment(
                    R.drawable.test1,"关于网易云音乐带来给我的美好回忆：我与他正是因为网易云相识",
                    R.drawable.testthree,"LiGuanFeng");
            momentList.add(c);
            Moment d = new Moment(
                    R.drawable.test3,"但爷Adam Lambert音乐节表演真棒",
                    R.drawable.testthree,"CHENYU");
            momentList.add(d);
            Moment e = new Moment(
                    R.drawable.test3,"【安利一首歌曲】",
                    R.drawable.test2,"BIKAQIU");
            momentList.add(e);
            Moment f = new Moment(
                    R.drawable.test2,"给大家安利我最喜欢的组合：PTX",
                    R.drawable.testone,"MEI");
            momentList.add(f);
            Moment g = new Moment(
                    R.drawable.test1,"PTX作为一个纯人声乐团，演唱歌曲的范围",
                    R.drawable.testone,"LiGuanFeng");
            momentList.add(g);

        }

    }

    //初始化音村视图
    private void initSquareView(View view){
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.moment_recyclerview);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        MomentAdapter adapter = new MomentAdapter(momentList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }


}
