package player;

import android.Manifest;
import android.app.Activity;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicplayer.R;


import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dbconnect.SongInfo;

public class PlayerActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ImageView play;
    private TextView lengthText, progressText;
    private SeekBar progress;
    //音乐长度
    private int songLength;
    //播放是否暂停
    private boolean isPause;
    //播放模式 0：列表循环 1：单曲循环
    private int playmode = 0;
    //当前播放列表所在位置
    private int location = -1;
    //音乐播放列表信息
    private List<SongInfo> songList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //初始化界面信息
        //设置标题歌曲信息
        initUI();

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
        backTo();
        errorListen();

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

    public static void actionStart(Context context, List<SongInfo> list, int location){
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra("SongList", (Serializable)list);
        intent.putExtra("Location", location);
        context.startActivity(intent);
    }

    /**
     *控制音乐播放开关
     */
    public void playMusicControl(View view){
        if(mediaPlayer.isPlaying()) {
            //暂停
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
//            File file = new File(Environment.getExternalStorageDirectory(),
//                                    songList.get(location).getFileName());
            File file = new File(songList.get(location).getUrl());
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();

            //获取时间长度
            songLength = mediaPlayer.getDuration();
            lengthText.setText(stringForTime(songLength));

            //设置title
            PlayerTitle playerTitle;
            playerTitle = (PlayerTitle) findViewById(R.id.playerTitle);
            playerTitle.setSingerName(songList.get(location).getArtist());
            playerTitle.setSongName(songList.get(location).getTitle());

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
            mediaPlayer.reset();
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
            public void onStartTrackingTouch(SeekBar seekBar) {}

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
                if(playmode == 0){
                    location = (location+1)%songList.size();
                    mediaPlayer.reset();

                    initMediaPlayer();
                }
                else {
                    mediaPlayer.reset();

                    initMediaPlayer();
                }
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

    /**
     *  返回按钮
     */
    private void backTo(){
        ImageView backto = (ImageView) findViewById(R.id.backto);
        backto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化MusicPlayerUI界面
     */
    private void initUI(){
        //初始化对象
        lengthText = (TextView)findViewById(R.id.lengthText);
        progress = (SeekBar) findViewById(R.id.progress);
        play = (ImageView) findViewById(R.id.play);
        progressText = (TextView) findViewById(R.id.progressText);

        //接收intent设置界面信息
        Intent intent = getIntent();
        songList = (List<SongInfo>) intent.getSerializableExtra("SongList");
        location = intent.getIntExtra("Location", -1);

        Log.d("ListSize", songList.size()+"");
        Log.d("PlayLocation", location+"");

    }

    /**
     * 切换播放模式
     * @param view
     */
    public void changePlayMode(View view){
        ImageView pm = (ImageView) findViewById(R.id.playmode);
        if(playmode == 0){
            //设置单曲循环
            pm.setImageResource(R.drawable.singleloop);
            playmode = 1;
        }
        else{
            //设置列表循环
            pm.setImageResource(R.drawable.listloop);
            playmode = 0;
        }
    }

    /**
     * 下一首
     * @param view
     */
    public void nextSong(View view){
        location = (location+1)%songList.size();
        mediaPlayer.reset();

        initMediaPlayer();
        mediaPlayer.start();
        play.setImageResource(R.drawable.pause);
    }

    /**
     * 上一首
     * @param view
     */
    public void lastSong(View view){
        location = (location+songList.size()-1)%songList.size();
        mediaPlayer.reset();

        initMediaPlayer();
        mediaPlayer.start();
        play.setImageResource(R.drawable.pause);

    }

    /**
     * 进行除初始化外的音乐加载
     */
    private void switchSong(){
        try {
            File file = new File(songList.get(location).getUrl());
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();

            //获取时间长度
            songLength = mediaPlayer.getDuration();
            lengthText.setText(stringForTime(songLength));

            //设置title
            PlayerTitle playerTitle;
            playerTitle = (PlayerTitle) findViewById(R.id.playerTitle);
            playerTitle.setSingerName(songList.get(location).getArtist());
            playerTitle.setSongName(songList.get(location).getTitle());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void errorListen(){
        final String TAG = "ERROR";
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d(TAG, "OnError - Error code: " + what + " Extra code: " + extra);
                switch (what) {
                    case -1004:
                        Log.d(TAG, "MEDIA_ERROR_IO");
                        break;
                    case -1007:
                        Log.d(TAG, "MEDIA_ERROR_MALFORMED");
                        break;
                    case 200:
                        Log.d(TAG, "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK");
                        break;
                    case 100:
                        Log.d(TAG, "MEDIA_ERROR_SERVER_DIED");
                        break;
                    case -110:
                        Log.d(TAG, "MEDIA_ERROR_TIMED_OUT");
                        break;
                    case 1:
                        Log.d(TAG, "MEDIA_ERROR_UNKNOWN");
                        break;
                    case -1010:
                        Log.d(TAG, "MEDIA_ERROR_UNSUPPORTED");
                        break;
                }
                switch (extra) {
                    case 800:
                        Log.d(TAG, "MEDIA_INFO_BAD_INTERLEAVING");
                        break;
                    case 702:
                        Log.d(TAG, "MEDIA_INFO_BUFFERING_END");
                        break;
                    case 701:
                        Log.d(TAG, "MEDIA_INFO_METADATA_UPDATE");
                        break;
                    case 802:
                        Log.d(TAG, "MEDIA_INFO_METADATA_UPDATE");
                        break;
                    case 801:
                        Log.d(TAG, "MEDIA_INFO_NOT_SEEKABLE");
                        break;
                    case 1:
                        Log.d(TAG, "MEDIA_INFO_UNKNOWN");
                        break;
                    case 3:
                        Log.d(TAG, "MEDIA_INFO_VIDEO_RENDERING_START");
                        break;
                    case 700:
                        Log.d(TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING");
                        break;
                }
                return false;
            }
        });
    }
}
