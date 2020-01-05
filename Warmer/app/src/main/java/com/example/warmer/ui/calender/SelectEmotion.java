package com.example.warmer.ui.calender;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmer.R;

import java.util.ArrayList;
import java.util.List;

public class SelectEmotion extends AppCompatActivity {
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emotion);

        recyclerview = findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "슬픈, 처지는"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "기운없는"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "위축된"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "우울한"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "화난"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "분한"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "불쾌한"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "신경질"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "약오른"));

        ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "놀란");
        places.invisibleChildren = new ArrayList<>();
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "놀란"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "황당한"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "의아한"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "아찔한"));

        data.add(places);

//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        // position에 따라서
//                        Log.d("position@@@@@@@@@@@", String.valueOf(position));
////                        Intent intent = new Intent(SelectEmotion.this, VideoDetailView.class);
////                        startActivity(intent);
//                    }
//                    @Override public void onLongItemClick(View view, int position) {
//                        // do whatever
//                    }
//                })
//        );

        recyclerview.setAdapter(new ExpandableListAdapter(data));
    }
}
