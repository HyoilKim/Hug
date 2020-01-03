package com.example.warmer.ui.dashboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.example.warmer.R;

import java.util.ArrayList;

public class MyEmotion extends Fragment {
    private Bitmap[][] emotionArr = new Bitmap[12][5];
    public ArrayList<String> seletedEmotions = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myemotion, container, false);

        emotionArr[0][0] = getBitmapFromVectorDrawable(getContext(), R.drawable.ic_home_black_24dp);
        emotionArr[0][1] = getBitmapFromVectorDrawable(getContext(), R.drawable.ic_home_black_24dp);
        emotionArr[0][2] = getBitmapFromVectorDrawable(getContext(), R.drawable.ic_home_black_24dp);
        emotionArr[0][3] = getBitmapFromVectorDrawable(getContext(), R.drawable.ic_home_black_24dp);

        GridView gridView = (GridView) view.findViewById(R.id.emotionGridView);
        EmotionAdapter adapter = new EmotionAdapter(getContext(), R.layout.emotion_item, emotionArr);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //seletedEmotions.add(adapter.getItem(position)); String or bitMap
                Log.d("position ~~~~~~~~~~~", String.valueOf(position));
            }
        });

//        view = inflater.inflate(R.layout.emotion_item, container, false);
//        ImageButton button = (ImageButton) view.findViewById(R.id.emotionItem);
//        button.setOnClickListener(new AdapterView.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("buttnon","clicked~~~~~~~~~~");
//            }
//        });
        return view;
    }


    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}