package com.example.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class BeginActivity extends AppCompatActivity {
    private int progress=0;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        pb = (ProgressBar) findViewById(R.id.pb);

        progressUp();

    }

    public void progressUp(){
        new Thread(){
            @Override
            public void run() {
                while(progress < 100) {
                    progress += 1;

                    BeginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(progress);
                        }
                    });
                    try{
                        Thread.sleep(50);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    Log.d("Progress", progress+"");

                }
                change();
            }
        }.start();
    }

    public void change(){
        Intent a = new Intent(this, MainActivity.class);
        startActivity(a);

    }
}
