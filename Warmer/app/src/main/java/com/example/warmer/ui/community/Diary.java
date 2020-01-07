package com.example.warmer.ui.community;

import android.widget.TextView;

public class Diary {
    private String contents;

    public Diary(){
        contents = "";
    }
    public Diary(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
