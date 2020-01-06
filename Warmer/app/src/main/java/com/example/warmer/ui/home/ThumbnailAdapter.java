package com.example.warmer.ui.home;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warmer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ViewHolder> {
    private ArrayList<Thumbnail> thumbnails;
    private LayoutInflater mInflater;

    public ThumbnailAdapter(ArrayList<Thumbnail> thumbnails, LayoutInflater mInflater) {
        this.thumbnails = thumbnails;
        this.mInflater = mInflater;
    }

    @Override
    public int getItemCount() { return thumbnails.size(); }
    public Thumbnail getItem(int i) { return thumbnails.get(i); }

    private OnItemClickListener mOnClickListener = null;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setClickListener(OnItemClickListener onItemClickListener) {
        this.mOnClickListener = onItemClickListener;
    }

    // 실행이 반복되어 화면에 띄어주는 메소드 = getView(in listview)
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageButton thumbnail_btn;
        private TextView main_title;
        ViewHolder(View itemView) {
            super(itemView);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("adatper","@@@@@@");
//                    int pos = getAdapterPosition();
//                    if (pos != RecyclerView.NO_POSITION) {
//                        if (mClickListener != null) {
//                            mClickListener.onItemClick(v, pos);
//                        } else {
//                            Log.d("listner", "null ");
//                        }
//                    }
//                }
//            });
            // 뷰 객체에 대한 참조
            thumbnail_btn = itemView.findViewById(R.id.thumbnail_item);
            main_title = itemView.findViewById(R.id.main_title);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.thumbnail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        // set item contents
        Thumbnail thumb = thumbnails.get(position);
        Picasso.get().load(thumb.getThumbURL())
                .into(holder.thumbnail_btn);
        holder.main_title.setText(thumb.getMainTitle());


        holder.thumbnail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("THUMBNAIL ","CLICKED");
                mOnClickListener.onItemClick(v, position);
            }
        });
//
//        Bitmap tmp = thumbnails.get(position).getImg();
//        holder.thumbnail_btn.setImageBitmap(tmp);
    }
//
//    public void addItem(Bitmap imgBitmap, String name, String desc, String videoURL, String type) {
//        Thumbnail item = new Thumbnail(type, name, desc, imgBitmap, videoURL);
//        thumbnails.add(item);
//        Log.d("added itme", name);
//    }
}
