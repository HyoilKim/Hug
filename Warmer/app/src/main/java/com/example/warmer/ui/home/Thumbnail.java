package com.example.warmer.ui.home;

import androidx.appcompat.app.AppCompatActivity;

public class Thumbnail extends AppCompatActivity {
    private int mid;
    private String thumbURL;
    private String mainTitle;
    private String subTitle;
    private String videoURL;

    public Thumbnail(int mid, String thumbURL, String mainTitle, String subTitle, String videoURL) {
        this.mid = mid;
        this.thumbURL = thumbURL;
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.videoURL = videoURL;
    }

    public int getMid() { return mid; }
    public String getThumbURL() { return thumbURL; }
    public String getMainTitle() { return mainTitle; }
    public String getSubTitle() { return subTitle; }
    public String getVideoURL() { return videoURL; }

    public void setMid(int mid) { this.mid = mid; }
    public void setThumbURL(String thumbURL) { this.thumbURL = thumbURL; }
    public void setTitle(String mainTitle) { this.mainTitle = mainTitle; }
    public void setSubTitle(String subTitle) { this.subTitle = subTitle; }
    public void setVideoURL(String videoURL) { this.videoURL = videoURL; }
}
