package com.example.warmer.ui.network;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class VolleyUtil {

    // request to server and add to queue
    public static void callAPI(RequestData requestData, ResponseListener responseListener) {
        RequestQueue queue = requestData.queue;
        JsonObjectRequest request = new JsonObjectRequest(
                requestData.requestType,
                requestData.requestURL,
                requestData.requestParams,
                getNetworkSuccessListener(responseListener),
                getNetworkErrorListener(responseListener));
        queue.add(request);
    }

    private static Response.Listener<JSONObject> getNetworkSuccessListener(final ResponseListener responseListener) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseListener.onSuccess(response);
            }
        };
    }

    private static Response.ErrorListener getNetworkErrorListener(final ResponseListener responseListener) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.onFail(error);
            }
        };
    }
}
