package player;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicplayer.R;


import java.io.File;

public class PlayerActivity extends AppCompatActivity {
    private PlayerTitle playerTitle;
    private Intent intent;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private int songLength;
    private ImageView play;
    private TextView lengthText, progressText;
    private SeekBar progress;
    private boolean isPause;
    private int playmode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //初始化对象
        playerTitle = (PlayerTitle) findViewById(R.id.playerTitle);
        lengthText = (TextView)findViewById(R.id.lengthText);
        intent = getIntent();
        progress = (SeekBar) findViewById(R.id.progress);
        play = (ImageView) findViewById(R.id.play);
        progressText = (TextView) findViewById(R.id.progressText);

        //设置标题歌曲信息
        playerTitle.setSingerName(intent.getStringExtra("singerName"));
        playerTitle.setSongName(intent.getStringExtra("songName"));

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        else {
            initMediaPlayer();
        }

        //调用方法
        progressControl();
        playListener();

    }

    /**
     * 启动该Activity时应调用的方法
     * @param context
     * @param songName 音乐名
     * @param singerName 歌手名
     */
    public static void actionStart(Context context, String songName, String singerName){
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra("songName", songName);
        intent.putExtra("singerName", singerName);
        context.startActivity(intent);

    }

    /**
     *控制音乐播放开关
     */
    public void playMusicControl(View view){
        if(mediaPlayer.isPlaying()) {
            //暂停
            Log.d("isPause", isPause+"");
            play.setImageResource(R.drawable.start);
            mediaPlayer.pause();
            isPause = true;
        }
        else {
            //继续播放
            play.setImageResource(R.drawable.pause);
            mediaPlayer.start();
            isPause = false;
        }
        refreshUI();
    }

    /**
     * 初始化音乐播放器实例对象
     */
    private void initMediaPlayer(){
        try{
            File file = new File(Environment.getExternalStorageDirectory(),"rather_be.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();

            //获取时间长度
            songLength = mediaPlayer.getDuration();
            lengthText.setText(stringForTime(songLength));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 申请权限操作
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }
                else {
                    Toast.makeText(this, "拒绝权限将无法使用程序",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    /**
     * 当Activity关闭时 音乐关闭播放
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    /**
     * 将毫秒转换为 --:-- 格式
     * @param millisecond   传入需要转换的毫秒时间
     * @return  返回时间的格式字符串
     */
    private String stringForTime(int millisecond){
        //获取时间长度
        int min, sec;
        String format, minStr, secStr;
        min = millisecond/60000;
        sec = millisecond%60000/1000;

        if(min/10 == 0) minStr = "0"+min;
        else minStr = min+"";
        if(sec/10 == 0) secStr = "0"+sec;
        else secStr = sec+"";

        format = minStr + ":" + secStr;

        return format;
    }

    /**
     * SeekBar进度条拖动监听
     */
    private void progressControl(){
        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int secChanged;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                secChanged = songLength/100*progress;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PlayerActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressText.setText(stringForTime(secChanged));
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(secChanged);
                isPause = false;
            }
        });
    }

    /**
     * 播放完成时监听响应
     */
    private void playListener(){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.stop();
                play.setImageResource(R.drawable.start);
            }
        });
    }

    /**
     * 刷新ui界面
     */
    private void refreshUI(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!isPause){
                    PlayerActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progress.setProgress(
                                    mediaPlayer.getCurrentPosition()*100/songLength );
                            progressText.setText(stringForTime(mediaPlayer.getCurrentPosition()));
                        }
                    });
                    if(mediaPlayer.getCurrentPosition()==songLength){
                        Log.d("songLength", songLength+"");
                        Log.d("songLength", mediaPlayer.getCurrentPosition()+"");
                        play.setImageResource(R.drawable.start);

                    }
                    try {
                        Thread.sleep(200);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
