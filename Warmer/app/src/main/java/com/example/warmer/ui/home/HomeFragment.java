package com.example.warmer.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmer.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private View view;
    private ArrayList<Thumbnail> thumbnail_list;
    private ThumbnailAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        thumbnail_list = new ArrayList<>();
        Bitmap bitmap = getBitmapFromVectorDrawable(getActivity(), R.drawable.youtube_img);
        Thumbnail thumbnail = new Thumbnail("img", "따뜻한 세상 이야기", "어느 한...", bitmap, null);
        thumbnail_list.add(thumbnail);
        thumbnail_list.add(thumbnail);
        thumbnail_list.add(thumbnail);

        adapter = new ThumbnailAdapter(thumbnail_list, inflater);
        // category마다 adapter 적용하는 thumbnail list 다름
        RecyclerView recyclerView = view.findViewById(R.id.thumbnail_list1);
        makeHorizontalView(recyclerView, thumbnail_list, inflater);

        recyclerView = view.findViewById(R.id.thumbnail_list2);
        makeHorizontalView(recyclerView, thumbnail_list, inflater);

        return view;
    }

    public void makeHorizontalView(RecyclerView recyclerView, ArrayList<Thumbnail> thumbnail_list, LayoutInflater inflater) {
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        adapter = new ThumbnailAdapter(thumbnail_list, inflater);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // position에 따라서
                        Log.d("position@@@@@@@@@@@", String.valueOf(position));
                        Intent intent = new Intent(getActivity(), VideoDetailView.class);
                        startActivity(intent);
                        // do whatever
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
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