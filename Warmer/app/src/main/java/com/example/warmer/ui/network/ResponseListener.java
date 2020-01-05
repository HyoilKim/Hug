package com.example.warmer.ui.network;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface ResponseListener {
    void onSuccess(JSONObject object);
    void onFail(VolleyError message);
}
