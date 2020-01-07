package com.example.warmer.ui.mypage;

import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.warmer.R;
import com.example.warmer.ui.network.VolleySingleton;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MyPage extends Fragment {
    private PopupWindow mPopupWindow;
    private static Boolean loginStatus = false;
    private static String userId = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        Button signInButton = (Button) view.findViewById(R.id.sign_in);
        Button signUpButton = (Button) view.findViewById(R.id.sign_up);
        final LinearLayout background = (LinearLayout) view.findViewById(R.id.mypage_background);

        // set popup window for sign-in
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View popupView = getLayoutInflater().inflate(R.layout.login_popup, null);

                final TextView header = (TextView) popupView.findViewById(R.id.popup_header);
                header.setText("[ Login ]");
                background.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //popupView 에서 (LinearLayout 을 사용) 레이아웃이 둘러싸고 있는 컨텐츠의 크기 만큼 팝업 크기를 지정
                mPopupWindow.setFocusable(true); // 외부 영역 선택히 PopUp 종료
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                final EditText idText = (EditText) popupView.findViewById(R.id.id);
                final EditText passwordText = (EditText) popupView.findViewById(R.id.password);

                Button ok = (Button) popupView.findViewById(R.id.Ok);
                ok.setText("SIGN IN");
                Button cancel = (Button) popupView.findViewById(R.id.Cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        background.setBackgroundColor(Color.parseColor("#FFFFFF"));
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
                                "http://192.249.19.252:1380/accounts/:"+id,
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        if(checkPassword(response, password)) {
                                            setLoginStatus(true);
                                            setUserId(id);
                                            mPopupWindow.dismiss();
                                            background.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                            Toast.makeText(getContext(), "Successfully logged in", Toast.LENGTH_SHORT).show();
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
                                    }
                                }
                        );
                        // add request to the queue
                        VolleySingleton.getInstance(getContext()).addToRequestQueue(accountRequest);
                    }
                });
            }
        });

        // set popup window for sign-up
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = getLayoutInflater().inflate(R.layout.login_popup, null);
                final TextView header = (TextView) popupView.findViewById(R.id.popup_header);
                header.setText("[ Creating account ]");
                background.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //popupView 에서 (LinearLayout 을 사용) 레이아웃이 둘러싸고 있는 컨텐츠의 크기 만큼 팝업 크기를 지정
                mPopupWindow.setFocusable(true); // 외부 영역 선택히 PopUp 종료
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                final EditText idText = (EditText) popupView.findViewById(R.id.id);
                final EditText passwordText = (EditText) popupView.findViewById(R.id.password);

                Button ok = (Button) popupView.findViewById(R.id.Ok);
                ok.setText("SIGN UP");
                Button cancel = (Button) popupView.findViewById(R.id.Cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        background.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String id = idText.getText().toString();
                        final String password = passwordText.getText().toString();
                        final ArrayList<String> id_list = new ArrayList<>();

                        // build POST request JSONObject parameter
                        final JSONObject jsonBody = new JSONObject();
                        try {
                            jsonBody.put("uid", id);
                            jsonBody.put("password", password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // set request to add account
                        JsonObjectRequest addAccountRequest = new JsonObjectRequest(
                                Request.Method.POST,
                                "http://192.249.19.252:1380/accounts/:",
                                jsonBody,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.d("JSON", response.toString());
                                        if(isDuplicateId(response)){
                                            idText.setText("");
                                            passwordText.setText("");
                                            Toast.makeText(getContext(), "ID is unavailable" , Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            mPopupWindow.dismiss();
                                            Toast.makeText(getContext(), "Successfully signed up", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                    }
                                }
                        );

                        VolleySingleton.getInstance(getContext()).addToRequestQueue(addAccountRequest);

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

    public Boolean isDuplicateId(JSONObject jsonObject) {
        try{
            if (jsonObject.getInt("errno")==1062) {
                return true;
            }
            else
                return false;
        } catch (JSONException e) {
            return false;
        }
    }

    public static void addJsonToIdList(ArrayList<String> id_list,
                                          JSONArray jsonArray)
    {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                id_list.add(jsonObject.getString("uid"));
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isLoggedIn() {
        return this.loginStatus;
    }

    private void setLoginStatus(Boolean bool) {
        this.loginStatus = bool;
    }

    private void setUserId(String uid) {
        this.userId = uid;
    }

    public static String getUserId() {
        return userId;
    }
}