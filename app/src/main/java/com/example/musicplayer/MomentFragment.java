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
    private void initSquareView(View view){
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.moment_recyclerview);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        MomentAdapter adapter = new MomentAdapter(momentList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }


}
