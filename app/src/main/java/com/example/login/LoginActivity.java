package com.example.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.musicplayer.ActivityCollector;
import com.example.musicplayer.R;

import org.litepal.crud.DataSupport;

import dbconnect.User;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity {
    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCollector.addActivity(this);

        userList = searchUserData();

        loginListener();

    }

    /**
     * 获取用户信息列表
     * @return 返回包含用户信息的list
     */
    private List<User> searchUserData(){
        List<User> list = DataSupport.findAll(User.class);
        for(int i = 0; i < list.size()-1; i++){
            Log.d("ACCT", list.get(i).getAcct());
            Log.d("PWD", list.get(i).getPwd());
//            Log.d("NICKNAME", list.get(i).getNickname());
//            Log.d("VIP", list.get(i).getVip());

        }
        return DataSupport.findAll(User.class);
    }

    private void loginListener(){
        Button button = (Button)findViewById(R.id.sign_in_button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)findViewById(R.id.tips);
                tv.setText("");
                EditText pwd = (EditText)findViewById(R.id.password);
                AutoCompleteTextView acct =
                        (AutoCompleteTextView) findViewById(R.id.acct);

                int position;
                if(acct.getText().toString().equals("") || pwd.getText().toString().equals("")){

                    return;
                }
                position = checkUserInfo(acct.getText().toString(), pwd.getText().toString());
                if(position == -1){
                    tv.setText("密码错误！");
                    return;
                }
                if(position == userList.size()){
                    User user = new User();
                    user.setAcct(acct.getText().toString());
                    user.setPwd(pwd.getText().toString());
                    user.save();
                }
                UsrInfoActivity.actionStart(LoginActivity.this, position);
            }
        });
    }


    /**
     * 检查用户数据表
     * @param acct 传入用户账号
     * @param pwd 传入用户密码
     * @return 返回整型表示用户所在list位置，-1表示密码错误，
     *          若为list.size()表示不存在此用户，需新建
     */
    private int checkUserInfo(String acct, String pwd){
        int i;
        for(i = 0; i < userList.size(); i++){
            if(acct.equals(userList.get(i).getAcct()))
                if(pwd.equals(userList.get(i).getPwd()))
                    return i;
                else
                    return -1;

        }
        return i;
    }


}

