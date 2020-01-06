package com.example.warmer.ui.home.detailView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.warmer.R;
import com.example.warmer.ui.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageDetailView extends AppCompatActivity {

    final String serverURL = "http://192.249.19.252:1380";
    final String image = "/images/:";
    private ArrayList<String> image_list;

    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int mid = getIntent().getIntExtra("mid", 0);
        mContext = ImageDetailView.this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_view);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.image_pager);

        image_list = new ArrayList<>();
        JsonArrayRequest imageRequest = new JsonArrayRequest(
                Request.Method.GET,
                serverURL + image + Integer.toString(mid),
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JsonAddToImageList(image_list, response);

                        pagerAdapter = new ImagePagerAdapter(mContext, image_list);
                        mPager.setAdapter(pagerAdapter);
                        mPager.setPageTransformer(true, new DepthPageTransformer());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("FAIL", "imageGET");
                    }
                }
        );

        VolleySingleton.getInstance(this).addToRequestQueue(imageRequest);

    }

//    @Override
//    public void onBackPressed() {
//        if (mPager.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//        }
//    }


    public static void JsonAddToImageList(ArrayList<String> image_list,
                                          JSONArray jsonArray)
    {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                image_list.add(jsonObject.getString("image"));
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
