package com.example.warmer.ui.network;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

public class RequestData {

    public RequestQueue queue;

    // {GET, POST, PUT, DELETE}
    public int requestType;

    public String requestURL;

    public JSONObject requestParams;

    public RequestData(RequestQueue queue, int requestType, String requestURL, JSONObject requestParams) {
        this.queue = queue;
        this.requestType = requestType;
        this.requestURL = requestURL;
        this.requestParams = requestParams;
    }

    public static class RequestBuilder {
        private RequestQueue queue;
        private int requestType;
        private String requestURL;
        private JSONObject requestParams;

        public RequestBuilder() {}

        public RequestBuilder setQueue(RequestQueue queue) {
            this.queue = queue;
            return this;
        }

        public RequestBuilder setRequestType(int requestType) {
            this.requestType = requestType;
            return this;
        }

        public RequestBuilder setRequestURL(String requestURL) {
            this.requestURL = requestURL;
            return this;
        }

        public RequestBuilder setRequestParams(JSONObject requestParams) {
            this.requestParams = requestParams;
            return this;
        }

        public RequestData requestBuild() {
            if (queue==null) {
                throw new IllegalStateException("null RequestQueue");
            }

            if (requestType < 0) {
                throw new IllegalStateException("null RequestType");
            }

            if (requestURL==null) {
                throw new IllegalStateException("null RequestURL");
            }

            return new RequestData(queue, requestType, requestURL, requestParams);
        }
    }
}
