package player;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.musicplayer.R;

/**
 * Created by Baldwin on 19/12/2.
 */

public class PlayerTitle extends RelativeLayout{
    private ImageView share, backto;
    private TextView songName, singerName;

    public PlayerTitle(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.player_title,this);

        share = (ImageView) findViewById(R.id.share);
        backto = (ImageView) findViewById(R.id.backto);
        songName = (TextView) findViewById(R.id.song_name);
        singerName = (TextView) findViewById(R.id.singer_name);

    }

    public PlayerTitle(Context context){
        super(context);
        LayoutInflater.from(context).inflate(R.layout.player_title,this);
    }

    public void setSongName(String songName){
        this.songName.setText(songName);
    }
    public void setSingerName(String singerName){
        this.singerName.setText(singerName);
    }

    public TextView getSongName() {
        return songName;
    }

    public TextView getSingerName() {
        return singerName;
    }

}
