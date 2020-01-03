package com.example.warmer.ui.dashboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.warmer.R;

import java.util.ArrayList;

public class EmotionAdapter extends BaseAdapter {
    private Context mContext;
    private int layout;
    LayoutInflater inflater;
    private Bitmap[][] gridViewImages = new Bitmap[5][12];
    // or String(

    public EmotionAdapter(Context mContext, int layout, Bitmap[][] gridViewImages) {
        this.mContext = mContext;
        this.layout = layout;
        this.gridViewImages = gridViewImages;
        inflater = (LayoutInflater) mContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return gridViewImages[0].length; }
    public Object getItem(int position) { return gridViewImages[0][position]; }
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton imageButton;
        if (convertView == null) {
            convertView = inflater.inflate(layout, null);
        }

        Bitmap bitmap = gridViewImages[0][position];
        imageButton = convertView.findViewById(R.id.emotionItem);
        imageButton.setImageBitmap(bitmap);
        imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
        // imageView.setLayoutParams(new GridView.LayoutParams(340,250));
        return convertView;
    }
}
