package com.example.warmer.ui.community;

import android.widget.TextView;

public class Diary {
    private String date;
    private String contents;
    private String uid;

    public Diary(String date, String contents, String uid){
        this.date = date;
        this.contents = contents;
        this.uid = uid;
    }

    public String getContents() {
        return contents;
    }
    public String getDate() {
        return date;
    }
    public String getUid() {
        return uid;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setUid(String uid) { this.uid = uid; }
}
