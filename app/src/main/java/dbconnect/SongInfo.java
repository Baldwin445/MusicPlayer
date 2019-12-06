package dbconnect;

import android.os.Bundle;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Baldwin on 19/12/3.
 */

public class SongInfo extends DataSupport implements Serializable{
    //歌曲编号
    private int id;
    //文件名
    private String fileName;
    //歌曲标题
    private String title;
    //歌曲的专辑名：MediaStore.Audio.Media.ALBUM
    private String album;
    //歌曲的歌手名： MediaStore.Audio.Media.ARTIST
    private String artist;
    //歌曲文件的路径 ：MediaStore.Audio.Media.DATA
    private String url;
    //歌曲的总播放时长：MediaStore.Audio.Media.DURATION
    private int duration;
    //歌曲文件的大小 ：MediaStore.Audio.Media.SIZE
    private String size;

    public void Song(){}

    public void Song(String title, String artist){
        this.title = title;
        this.artist = artist;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
