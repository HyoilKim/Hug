package com.example.warmer.ui.community;

import android.widget.TextView;

public class Diary {
    private String date;
    private String contents;
    private String uid;
    private int shared;

    public Diary(String date, String contents, String uid, int bool){
        this.date = date;
        this.contents = contents;
        this.uid = uid;
        this.shared = bool;
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
    public int getShared() {
        return shared;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setUid(String uid) { this.uid = uid; }
    public void setShared(int bool) { this.shared = bool; }
}
