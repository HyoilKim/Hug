package com.example.warmer.ui.home;

import android.graphics.Bitmap;

import androidx.appcompat.app.AppCompatActivity;

public class Thumbnail extends AppCompatActivity {
    private String name;
    private String desc;
    private Bitmap img;
    private String videoURL;
    private String type;

    public Thumbnail(String type, String name, String desc, Bitmap img, String videoURL) {
        this.name = name;
        this.desc = desc;
        this.img = img;
        this.videoURL = videoURL;
        this.type = type;
    }

    public String getName() { return name; }
    public String getDesc() { return desc; }
    public Bitmap getImg() { return img; }
    public String getVideoURL() { return videoURL; }
    public String getType() { return type; }

    public void setName(String name) { this.name = name; }
    public void setDesc(String desc) { this.desc = desc; }
    public void setImg(Bitmap img) { this.img = img; }
    public void setVideoURL(String videoURL) { this.videoURL = videoURL; }
    public void setType(String type) { this.type = type; }
}
