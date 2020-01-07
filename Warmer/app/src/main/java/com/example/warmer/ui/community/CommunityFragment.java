package com.example.warmer.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmer.R;
import com.example.warmer.ui.calender.Data;

import java.util.Arrays;
import java.util.List;

public class CommunityFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<Diary> diaryList;
    DiaryListAdapter diaryListAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        diaryListAdapter = new DiaryListAdapter();
        List<String> listTitle = Arrays.asList("일기1", "일기2", "일기3", "일기4");
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Diary contents = new Diary();
            contents.setContents(listTitle.get(i));
            // 각 값이 들어간 data를 adapter에 추가합니다.
            diaryListAdapter.addItem(contents);
        }

        recyclerView.setAdapter(diaryListAdapter);

        return view;

    }
}
