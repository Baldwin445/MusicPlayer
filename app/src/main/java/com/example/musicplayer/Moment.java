package com.example.musicplayer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Baldwin on 19/11/29.
 */

public class Moment {
    private int thumbnailId;
    private String describe;
    private int profilePhotoId;
    private String nickname;
    private int thumbnailWidth,thumbnailHeight;
    private int profileWidth,profileHeight;

    public Moment(int thumbnail, String describe,
                  int profilePhoto, String nickname){
        this.thumbnailId = thumbnail;
        this.describe = describe;
        this.profilePhotoId = profilePhoto;
        this.nickname = nickname;

    }

    public int getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(int thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getProfilePhotoId() {
        return profilePhotoId;
    }

    public void setProfilePhotoId(int profilePhotoId) {
        this.profilePhotoId = profilePhotoId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
