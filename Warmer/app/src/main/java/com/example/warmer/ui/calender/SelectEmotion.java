package com.example.warmer.ui.calender;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmer.R;
import com.example.warmer.ui.home.ThumbnailAdapter;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.internal.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SelectEmotion extends AppCompatActivity {
    private ExpandableAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emotion);

        RecyclerView recyclerview = findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(new ExpandableAdapter());
        init();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ExpandableAdapter();

        View view = getLayoutInflater().inflate(R.layout.item, null, false);

//        FlexboxLayout flexboxLayout = view.findViewById(R.id.itemFlexBox);

        List<String> listTitle = Arrays.asList("불안", "우울", "힘이 빠지는", "심각한");
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setResId(R.drawable.main_img);
//            data.setFlexboxLayout(flexboxLayout);
            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        recyclerView.setAdapter(adapter);
    }

//    private void getData() {
//        // 임의의 데이터입니다.
//        List<String> listTitle = Arrays.asList("불안", "우울", "힘이 빠지는", "심각한");
//
//        for (int i = 0; i < listTitle.size(); i++) {
//            // 각 List의 값들을 data 객체에 set 해줍니다.
//            Data data = new Data();
//            data.setTitle(listTitle.get(i));
//            data.setResId(R.drawable.main_img);
//
//            // 각 값이 들어간 data를 adapter에 추가합니다.
//            adapter.addItem(data);
//        }
//
//        // adapter의 값이 변경되었다는 것을 알려줍니다.
//        adapter.notifyDataSetChanged();
//    }

}
