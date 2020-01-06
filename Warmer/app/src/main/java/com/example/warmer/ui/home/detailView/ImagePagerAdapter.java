package com.example.warmer.ui.home.detailView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.example.warmer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<String> image_list;
    private LayoutInflater inflater;

    public ImagePagerAdapter(Context context, ArrayList<String> image_list) {
        this.mContext = context;
        this.image_list = image_list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Integer.toString(position);
    }

    // return number of views
    @Override
    public int getCount() {
        return image_list.size();
    }

    // produce item on the screen
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView;
        View viewLayout = inflater.inflate(R.layout.image_item, container, false);

        imageView = (ImageView) viewLayout.findViewById(R.id.indImage);
        /***/
        Log.d("image URL: ", image_list.get(position));
        /***/
        Picasso.get().load(image_list.get(position))
                .into(imageView);
        (container).addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((LinearLayout) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
