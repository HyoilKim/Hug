package com.example.warmer.ui.home;

import androidx.appcompat.app.AppCompatActivity;

public class Thumbnail extends AppCompatActivity {
    private int mid;
    private String thumbURL;
    private String mainTitle;
    private String subtitle;
    private String videoURL;

    public Thumbnail(int mid, String thumbURL, String mainTitle, String subtitle, String videoURL) {
        this.mid = mid;
        this.thumbURL = thumbURL;
        this.mainTitle = mainTitle;
        this.subtitle = subtitle;
        this.videoURL = videoURL;
    }

    public int getMid() { return mid; }
    public String getThumbURL() { return thumbURL; }
    public String getMainTitle() { return mainTitle; }
    public String getSubtitle() { return subtitle; }
    public String getVideoURL() { return videoURL; }

    public void setMid(int mid) { this.mid = mid; }
    public void setThumbURL(String thumbURL) { this.thumbURL = thumbURL; }
    public void setMainTitle(String mainTitle) { this.mainTitle = mainTitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
    public void setVideoURL(String videoURL) { this.videoURL = videoURL; }
}
