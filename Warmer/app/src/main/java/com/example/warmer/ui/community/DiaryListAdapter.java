package com.example.warmer.ui.community;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmer.R;

import java.util.ArrayList;

public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.ViewHolder> {
    private ArrayList<Diary> diaryList;
    private Context context;

    public DiaryListAdapter() {
        this.diaryList = new ArrayList<>();
    }

    @NonNull
    @Override
    public DiaryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.diary_item, parent, false) ;

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryListAdapter.ViewHolder holder, int position) {
        String str = diaryList.get(position).getContents();
        holder.textView.setText(str);
    }

    @Override
    public int getItemCount() {
        return diaryList.size();
    }

    public void addItem (Diary diary) {
        this.diaryList.add(diary);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.diaryItem);
        }
    }

}
