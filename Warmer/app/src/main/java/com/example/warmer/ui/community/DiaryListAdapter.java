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

    public DiaryListAdapter(ArrayList<Diary> diary_list) {
        this.diaryList = diary_list;
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
        String contents = diaryList.get(position).getContents();
        String date = diaryList.get(position).getDate();
        holder.contentsView.setText(contents);
        holder.dateView.setText(date);
    }

    @Override
    public int getItemCount() {
        return diaryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView contentsView;
        public TextView dateView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contentsView = (TextView) itemView.findViewById(R.id.diaryItem);
            dateView = (TextView) itemView.findViewById(R.id.line);
        }
    }

}
