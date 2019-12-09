package com.example.login;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.musicplayer.R;


/**
 * Created by Baldwin on 19/12/7.
 */

public class LoginTitle extends RelativeLayout{
    ImageView backto;

    public LoginTitle(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.login_title, this);

        backto = (ImageView) findViewById(R.id.backto);
        backto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity)getContext();
                activity.finish();
            }
        });
    }



}
