package com.example.warmer.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.warmer.R;
import com.example.warmer.ui.home.detailView.ImageDetailView;
import com.example.warmer.ui.home.detailView.VideoDetailView;
import com.example.warmer.ui.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private View view;
    private ArrayList<Thumbnail> video_list;
    private ArrayList<Thumbnail> picture_list;
    private RecyclerView videoRecyclerView;
    private RecyclerView pictureRecyclerView;
    private ThumbnailAdapter videoAdapter;
    private ThumbnailAdapter pictureAdapter;

    final String serverURL = "http://192.249.19.252:1380";
    final String media = "/medias";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        video_list = new ArrayList<>();
        picture_list = new ArrayList<>();

        // construct a request
        // params : request method / url / JSON / response listener / error listener
        JsonArrayRequest mediaRequest = new JsonArrayRequest(
                Request.Method.GET,
                serverURL + media,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        addJsonToThumbList(video_list, picture_list, response);

                        // attach adapter to list1
                        videoAdapter = new ThumbnailAdapter(video_list, inflater);
                        videoRecyclerView = view.findViewById(R.id.thumbnail_list2);
                        makeHorizontalView(videoAdapter, videoRecyclerView, video_list, inflater);

                        //attach adapter to list2
                        pictureAdapter = new ThumbnailAdapter(picture_list, inflater);
                        pictureRecyclerView = view.findViewById(R.id.thumbnail_list1);
                        makeHorizontalView(pictureAdapter, pictureRecyclerView, picture_list, inflater);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("FAIL", "CONNECTION");
                    }
                }
        );

        // add the request to singleton requestQueue
        VolleySingleton.getInstance(getContext()).addToRequestQueue(mediaRequest);

        return view;
    }

    public void makeHorizontalView(ThumbnailAdapter adapter, RecyclerView recyclerView, final ArrayList<Thumbnail> thumbnail_list, LayoutInflater inflater) {
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent;
                        // pass information to following activity
                        Thumbnail thumb = thumbnail_list.get(position);
                        if(isVideo(thumb)) {
                            intent = new Intent(getActivity(), VideoDetailView.class);
                            intent.putExtra("videoURL", thumb.getVideoURL());
                        } else {
                            intent = new Intent(getActivity(), ImageDetailView.class);
                            intent.putExtra("mid", thumb.getMid());
                        }

                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    public static void addJsonToThumbList(ArrayList<Thumbnail> video_list,
                                          ArrayList<Thumbnail> picture_list,
                                          JSONArray jsonArray)
    {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Thumbnail thumbnail = new Thumbnail(0, "", "", "", "");
                thumbnail.setMid(jsonObject.getInt("mid"));
                thumbnail.setThumbURL(jsonObject.getString("thumb"));
                thumbnail.setMainTitle(jsonObject.getString("title"));
                thumbnail.setSubtitle(jsonObject.getString("subtitle"));
                thumbnail.setVideoURL(jsonObject.getString("video"));

                if(isVideo(thumbnail)) {
                    picture_list.add(thumbnail);
                } else {
                    video_list.add(thumbnail);
                }
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean isVideo(Thumbnail thumb) {
        if(thumb.getVideoURL().equals("image"))
            return false;
        else
            return true;
    }
}