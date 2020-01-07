package com.example.warmer.ui.calender;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.warmer.R;
import com.example.warmer.ui.calender.Decorator.EventDecorator;
import com.example.warmer.ui.calender.Decorator.OneDayDecorator;
import com.example.warmer.ui.calender.Decorator.SaturdayDecorator;
import com.example.warmer.ui.calender.Decorator.SundayDecorator;
import com.example.warmer.ui.home.ThumbnailAdapter;
import com.example.warmer.ui.mypage.MyPage;
import com.example.warmer.ui.network.VolleySingleton;
import com.google.android.flexbox.FlexboxLayout;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

// calender emotion
public class Calender extends Fragment {

    String time, kcal, menu;
    Cursor cursor;
    MaterialCalendarView materialCalendarView;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    private final int ADD_EMOTION = 1;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        materialCalendarView = (MaterialCalendarView)view.findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2010, 0, 1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2020, 11, 31)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        // **************** DB에 작성된 나의 일기 목록 ***************** //
        String[] result = {"2020,01,18","2020,01,15","2020,01,13","2020,01,10"};

        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

        // 날짜 클릭 리스너
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();
                final TextView textview = view.findViewById(R.id.calendarMemo);

                String strDate = Integer.toString(Year*10000+Month*100+Day);
                final String shot_Day = strDate.substring(0,4)+"."+strDate.substring(4,6)+"."+strDate.substring(6,8);

                materialCalendarView.clearSelection();

                Toast.makeText(getContext(), shot_Day , Toast.LENGTH_SHORT).show();

                JsonArrayRequest privateDiaryRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        "http://192.249.19.252:1380/diaries/:"+MyPage.getUserId(),
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                textview.setText(getDairy(response, shot_Day));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("ERROR: ", error.toString());
                                textview.setText("No diary this day");
                            }
                        }
                );

                // add the request to singleton requestQueue
                VolleySingleton.getInstance(getContext()).addToRequestQueue(privateDiaryRequest);
            }
        });

        TextView textView = view.findViewById(R.id.calendarMemo);
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectEmotion.class);
                // 클릭(일기 내용도 같이 보내기)
                startActivityForResult(intent, ADD_EMOTION);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case ADD_EMOTION:
                // ************** ok 누르면 점찍기 *********** //
                default: break;
        }
    }

    public class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
        String[] Time_Result;
        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for(int i = 0 ; i < Time_Result.length ; i ++){
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);
                Log.d("day", year + " " + month +" "+dayy);
                dates.add(day);
                calendar.set(year,month-1,dayy);
            }

            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {

            Log.d("size", String.valueOf(calendarDays.size()));
            super.onPostExecute(calendarDays);
            if (getActivity().isFinishing()) {
                return;
            }
            materialCalendarView.addDecorator(new EventDecorator(Color.LTGRAY, calendarDays, getActivity()));
        }

    }

    public String getDairy(JSONArray jsonArray, String date) {
        try {
            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getString("date").equals(date))
                    return jsonObject.getString("text");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No diary this day";
    }
}