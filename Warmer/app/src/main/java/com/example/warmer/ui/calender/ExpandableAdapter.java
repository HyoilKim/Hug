package com.example.warmer.ui.calender;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.warmer.R;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;

import java.util.ArrayList;

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.ItemViewHolder> {

    private ArrayList<Data> listData = new ArrayList<>();                  // adapter에 들어갈 list 입니다.
    private Context context;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();   // Item의 클릭 상태를 저장할 array 객체
    private ArrayList<Chip> selectedChipList = new ArrayList<>();
    private int prePosition = -1;                                          // 직전에 클릭됐던 Item의 position
    private ArrayList<Chip[]> chipList;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        context = parent.getContext();
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.item, parent, false) ;
        Log.d("itetviewholder", "@@@@@@@@@@@");
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position), position);
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView1;
        private ImageView imageView1;
        private FlexboxLayout flexboxLayout;
        private ArrayList<Chip> chipList;
        private Data data;
        private Chip chip1, chip2, chip3, chip4, chip5;
        private int position;
        // item을 처음 생성 할 때만 실행돼야 하는데 왜 2번 더 실행되냐고 =====================================================
        ItemViewHolder(View itemView) {
            super(itemView);
            Log.d("constructr","~~~~~~~~~~");
            // item.xml 참조
            textView1 = itemView.findViewById(R.id.textView1);
            imageView1 = itemView.findViewById(R.id.imageView1);
            chip1 = itemView.findViewById(R.id.chip1);
            chip2 = itemView.findViewById(R.id.chip2);
            chip3 = itemView.findViewById(R.id.chip3);
            chip4 = itemView.findViewById(R.id.chip4);
            chip5 = itemView.findViewById(R.id.chip5);

            chipList = new ArrayList<>();
        }

        // item.xml 값 세팅
        void onBind(Data data, int position) {
            this.data = data;
            this.position = position;
            // *************** DB **************** //
            // textView1(감정), imageView(대표 이미지) 세팅
            textView1.setText(data.getTitle());
            imageView1.setImageResource(data.getResId());
            chip1.setText("chip1");
            flexboxLayout = data.getFlexboxLayout();

            changeVisibility(selectedItems.get(position));

            itemView.setOnClickListener(this);
//            textView1.setOnClickListener(this);
//            imageView1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Log.d("len", String.valueOf(selectedItems.size()));
//            this.chip2.setText("chip2");
//            Log.d("pos", String.valueOf(position));
//            if (selectedItems.get(position)) {
//                selectedItems.delete(position);
//            }
//            else {
//                selectedItems.delete(prePosition);
//                selectedItems.put(position, true);
//            }
//            if (prePosition != -1) notifyItemChanged(prePosition);
//            notifyItemChanged(position);
//            prePosition = position;
        }
        // animation & visible/gone
        private void changeVisibility(final boolean isExpanded) {
            // height 값을 dp로 지정해서 넣고싶으면 아래 소스를 이용
            int dpValue = 150;
            float d = context.getResources().getDisplayMetrics().density;
            int height = (int) (dpValue * d);

            // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
            // Animation이 실행되는 시간, n/1000초
            va.setDuration(600);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    for (Chip chip : chipList) {
                        chip.requestLayout();
                        chip.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                    }
                }
            });
            // Animation start
            va.start();
        }

    }
}