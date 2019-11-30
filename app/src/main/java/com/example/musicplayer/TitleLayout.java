package com.example.musicplayer;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Baldwin on 19/11/29.
 */

public class TitleLayout extends RelativeLayout {
    private Button info, search;
    private TextView mine, find, communicate;

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);

        info = (Button) findViewById(R.id.info);
        search = (Button) findViewById(R.id.search);
        mine = (TextView)findViewById(R.id.mine);
        find = (TextView)findViewById(R.id.find);
        communicate = (TextView)findViewById(R.id.communicate);

        mine.setTextSize(16);
        mine.setTypeface(Typeface.DEFAULT_BOLD);

        buttonEvent();
        textViewEvent();

    }

    private void buttonEvent(){
        info.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tips", "你点击了个人信息按钮");
            }
        });

        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tips", "你点击了搜索按钮");
            }
        });

    }

    private void textViewEvent(){
        mine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mine.setTextSize(16);
                mine.setTypeface(Typeface.DEFAULT_BOLD);
                find.setTextSize(14);
                find.setTypeface(Typeface.DEFAULT);
                communicate.setTextSize(14);
                communicate.setTypeface(Typeface.DEFAULT);

                new Thread(){
                    @Override
                    public void run() {
                        MainActivity main = (MainActivity) getContext();
                        main.replaceFragment(new SongFragment());
                    }
                }.start();

                Log.d("Tips","你点击了\"我的\"标签页");
            }
        });
        find.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                find.setTextSize(16);
                find.setTypeface(Typeface.DEFAULT_BOLD);
                mine.setTextSize(14);
                mine.setTypeface(Typeface.DEFAULT);
                communicate.setTextSize(14);
                communicate.setTypeface(Typeface.DEFAULT);
                Log.d("Tips","你点击了\"发现\"标签页");

            }
        });
        communicate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                communicate.setTextSize(16);
                communicate.setTypeface(Typeface.DEFAULT_BOLD);
                mine.setTextSize(14);
                mine.setTypeface(Typeface.DEFAULT);
                find.setTextSize(14);
                find.setTypeface(Typeface.DEFAULT);

                new Thread(){
                    @Override
                    public void run() {
                        MainActivity main = (MainActivity) getContext();
                        main.replaceFragment(new MomentFragment());
                    }
                }.start();

                Log.d("Tips","你点击了\"音村\"标签页");

            }
        });
    }

}
