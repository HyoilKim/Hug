package com.example.warmer.ui.calender;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.internal.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SelectEmotion extends AppCompatActivity {
    private ExpandableAdapter adapter;
    Calender calender = new Calender();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)this).getSupportActionBar().hide();
        setContentView(R.layout.activity_add_emotion);

        RecyclerView recyclerview = findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(new ExpandableAdapter());
        init();

        final EditText doneDiary = findViewById(R.id.emotionMemo);
        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        final CheckBox checkBox = findViewById(R.id.checkBox1);

        String selectedEmotion = "";
        for (int i = 0; i < ExpandableAdapter.selectedChipList.size(); i++){
            selectedEmotion += "#"+ExpandableAdapter.selectedChipList.get(i);
        }
        selectedEmotion += "\n";

        final String finalSelectedEmotion = selectedEmotion;
        doneDiary.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        // ********** DB 저장 ********* //
//                        Intent intent = new Intent(SelectEmotion.this, Calender.class);
//                        intent.putExtra("isPublic", checkBox.isChecked());
//                        intent.putExtra("memo", doneDiary.getText().toString());
                        finish();
                        break;
                }
                return true;
            }
        });
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ExpandableAdapter();
        View view = getLayoutInflater().inflate(R.layout.item, null, false);

        // ********** DB의 감정들 불러오기 **************** //
        List<String> listTitle = Arrays.asList("불안", "우울", "힘이 빠지는", "심각한");
        for (int i = 0; i < listTitle.size(); i++) {
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setResId(R.drawable.main_img);
            adapter.addItem(data);
        }

        recyclerView.setAdapter(adapter);
    }

}
