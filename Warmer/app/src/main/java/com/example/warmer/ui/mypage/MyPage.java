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
import com.example.warmer.R;
import com.example.warmer.ui.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyPage extends Fragment {
    private PopupWindow mPopupWindow;
    private static Boolean isLoggedIn = false;

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
                        JsonArrayRequest accountRequest = new JsonArrayRequest(
                                Request.Method.GET,
                                "http://192.249.19.252:1380/accounts/:"+"\""+id+"\"",
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        if(checkPassword(response, password)) {
                                            mPopupWindow.dismiss();
                                        }
                                        else {
                                            idText.setText("");
                                            passwordText.setText("");
                                            Toast.makeText(getContext(), "Incorrect ID or password" , Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d("FAIL", "accountGET");
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

    public Boolean checkPassword(JSONArray jsonArray, String password) {
        try{
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            if(jsonObject.getString("password").equals(password)){
                Log.d("account", "correct");
                return true;
            }
            else
                Log.d("account", "wrong password");
                return false;
        }
        catch (JSONException e) {
            Log.d("account", "wrong id");
            return false;
        }
    }
}