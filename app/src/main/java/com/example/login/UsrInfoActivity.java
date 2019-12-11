package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicplayer.ActivityCollector;
import com.example.musicplayer.R;

import org.litepal.crud.DataSupport;

import dbconnect.User;

public class UsrInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usr_info);
        ActivityCollector.addActivity(this);


        initUI();
    }

    public static void actionStart(Context context, int location){
        Intent intent = new Intent(context, UsrInfoActivity.class);
        intent.putExtra("Location", location);
        context.startActivity(intent);

    }

    /**
     * 初始化UI
     */
    private void initUI(){
        int location = getIntent().getIntExtra("Location", -1);
        if(location == -1)
            return;

        User usr = DataSupport.findAll(User.class).get(location);
        TextView acct = (TextView) findViewById(R.id.acct);
        acct.setText(usr.getAcct());
        TextView nickname = (TextView) findViewById(R.id.nickname);
        nickname.setText(usr.getNickname());
        ImageView vip = (ImageView) findViewById(R.id.vip);
        if(usr.getVip().equals("Vip"))
            vip.setImageResource(R.drawable.vip);
        else
            vip.setImageResource(R.drawable.qionggui);
        ImageView icon = (ImageView)findViewById(R.id.icon);
        icon.setImageResource(R.drawable.defaulticon);


    }
}
