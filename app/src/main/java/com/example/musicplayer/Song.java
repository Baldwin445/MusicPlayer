package com.example.musicplayer;

/**
 * Created by Baldwin on 19/11/27.
 */

public class Song {
    private String name;
    private String singer;

    public Song(String name, String singer){
        this.name = name;
        this.singer = singer;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
