package com.example.warmer.ui.mypage;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.warmer.R;
import com.example.warmer.ui.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MyPage extends Fragment {
    private PopupWindow mPopupWindow;
    private static Boolean loginStatus = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        Button popup = (Button) view.findViewById(R.id.login);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = getLayoutInflater().inflate(R.layout.login_popup, null);
                mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //popupView 에서 (LinearLayout 을 사용) 레이아웃이 둘러싸고 있는 컨텐츠의 크기 만큼 팝업 크기를 지정
                mPopupWindow.setFocusable(true); // 외부 영역 선택히 PopUp 종료
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                final EditText idText = (EditText) popupView.findViewById(R.id.id);
                final EditText passwordText = (EditText) popupView.findViewById(R.id.password);

                Button ok = (Button) popupView.findViewById(R.id.Ok);
                Button cancel = (Button) popupView.findViewById(R.id.Cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String id = idText.getText().toString();
                        final String password = passwordText.getText().toString();


                        // set request to get account information
                        JsonObjectRequest accountRequest = new JsonObjectRequest(
                                Request.Method.GET,
                                "http://192.249.19.252:1380/accounts/:"+"\""+id+"\"",
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        if(checkPassword(response, password)) {
                                            setLoginStatus(true);
                                            mPopupWindow.dismiss();
                                        }
                                        else {
                                            setLoginStatus(false);
                                            idText.setText("");
                                            passwordText.setText("");
                                            Toast.makeText(getContext(), "Incorrect ID or password" , Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    // only cover status 404(NOT FOUND)
                                    public void onErrorResponse(VolleyError error) {
                                        setLoginStatus(false);
                                        idText.setText("");
                                        passwordText.setText("");
                                        Toast.makeText(getContext(), "Incorrect ID or password" , Toast.LENGTH_SHORT).show();
//                                        try {
//                                            String response = new String(error.networkResponse.data, "utf-8");
//                                            JSONObject jsonObject = new JSONObject(response);
//                                        } catch (UnsupportedEncodingException ueError) {
//                                            idText.setText("");
//                                            passwordText.setText("");
//                                            Toast.makeText(getContext(), "Incorrect ID or password" , Toast.LENGTH_SHORT).show();
//                                        } catch (JSONException jError) {
//                                            Log.d("JSON", "ERROR");
//                                            jError.printStackTrace();
//                                        }
                                    }
                                }
                        );
                        // add request to the queue
                        VolleySingleton.getInstance(getContext()).addToRequestQueue(accountRequest);
                    }
                });
            }
        });
        return view;
    }

    public Boolean checkPassword(JSONObject jsonObject, String password) {
        try{
            if(jsonObject.getString("password").equals(password)){
                return true;
            }
            else
                return false;
        } catch (JSONException je) {
            je.printStackTrace();
            return false;
        }
    }

    public boolean isLoggedIn() {
        return this.loginStatus;
    }

    private void setLoginStatus(Boolean bool) {
        this.loginStatus = bool;
    }
}