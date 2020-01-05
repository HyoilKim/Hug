package com.example.warmer.ui.home;

import android.graphics.Bitmap;

import androidx.appcompat.app.AppCompatActivity;

public class Thumbnail extends AppCompatActivity {
    private int mid;
    private String name;
    private String desc;
    private Bitmap img;
    private String videoURL;

    public Thumbnail(int mid, String name, String desc, Bitmap img, String videoURL) {
        this.mid = mid;
        this.name = name;
        this.desc = desc;
        this.img = img;
        this.videoURL = videoURL;
    }

    public int getMid() { return mid; }
    public String getName() { return name; }
    public String getDesc() { return desc; }
    public Bitmap getImg() { return img; }
    public String getVideoURL() { return videoURL; }

    public void setMid(int mid) { this.mid = mid; }
    public void setName(String name) { this.name = name; }
    public void setDesc(String desc) { this.desc = desc; }
    public void setImg(Bitmap img) { this.img = img; }
    public void setVideoURL(String videoURL) { this.videoURL = videoURL; }
}
