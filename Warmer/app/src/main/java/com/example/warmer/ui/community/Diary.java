package com.example.warmer.ui.community;

import android.widget.TextView;

public class Diary {
    private String date;
    private String contents;

    public Diary(String date, String contents){
        this.date = date;
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }
    public String getDate() {
        return date;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
