package com.example.warmer.ui.calender;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.chip.Chip;

public class Data {
    private String title;
    private int resId;
    private FlexboxLayout flexboxLayout;

    public void setFlexboxLayout(FlexboxLayout flexboxLayout) {
        this.flexboxLayout = flexboxLayout;
    }

    public FlexboxLayout getFlexboxLayout() {
        return flexboxLayout;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getResId() { return resId; }
    public void setResId(int resId) { this.resId = resId; }
}