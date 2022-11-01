package com.example.gahui.httptest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.Handler;
import android.os.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class Countdown extends Activity {
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            final TextView date1 = (TextView) findViewById(R.id.date1);
            int s = (int) msg.obj;
            String s1 = Integer.toString(s);
            System.out.println(String.format("%s天填", s1));
            date1.setText(s1+'天');
        }
    };
    public static TimeZone DEFAULT_SERVER_TIME_ZONE = TimeZone.getTimeZone("GMT+08:00");

    public static long getTimeInMillis(int year, int month, int day, int hours, int minutes, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(DEFAULT_SERVER_TIME_ZONE);
        calendar.set(year, month - 1, day, hours, minutes, seconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format.setTimeZone(DEFAULT_SERVER_TIME_ZONE);
        try {
            return format.parse(format.format(calendar.getTime())).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        long timeMillis = getTimeInMillis(2020, 12, 21, 0, 0, 0);
        Calendar current = Calendar.getInstance(TimeZone.getDefault());
        current.setTimeInMillis(System.currentTimeMillis());
        Calendar date = Calendar.getInstance(TimeZone.getDefault());
        date.setTimeInMillis(timeMillis);
        long time = date.getTimeInMillis() - current.getTimeInMillis();
        int day = Math.round(time / 1000 / 60 / 60 / 24);
        final TextView date1 = (TextView) findViewById(R.id.date1);
        if(day > 0) {
            String s1 = Integer.toString(day);
            date1.setText(s1 + '天');
        }
        else{
            date1.setText("时间已过");
        }

        timeMillis = getTimeInMillis(2020, 10, 10, 0, 0, 0);
        current = Calendar.getInstance(TimeZone.getDefault());
        current.setTimeInMillis(System.currentTimeMillis());
        date = Calendar.getInstance(TimeZone.getDefault());
        date.setTimeInMillis(timeMillis);
        time = date.getTimeInMillis() - current.getTimeInMillis();
        day = Math.round(time / 1000 / 60 / 60 / 24);
        final TextView date2 = (TextView) findViewById(R.id.date2);
        String s2 = Integer.toString(day);
        date2.setText(s2+'天');

        timeMillis = getTimeInMillis(2020, 11, 11, 0, 0, 0);
        current = Calendar.getInstance(TimeZone.getDefault());
        current.setTimeInMillis(System.currentTimeMillis());
        date = Calendar.getInstance(TimeZone.getDefault());
        date.setTimeInMillis(timeMillis);
        time = date.getTimeInMillis() - current.getTimeInMillis();
        day = Math.round(time / 1000 / 60 / 60 / 24);
        final TextView date3 = (TextView) findViewById(R.id.date3);
        String s3 = Integer.toString(day);
        date3.setText(s3+'天');

        timeMillis = getTimeInMillis(2020, 12, 4, 0, 0, 0);
        current = Calendar.getInstance(TimeZone.getDefault());
        current.setTimeInMillis(System.currentTimeMillis());
        date = Calendar.getInstance(TimeZone.getDefault());
        date.setTimeInMillis(timeMillis);
        time = date.getTimeInMillis() - current.getTimeInMillis();
        day = Math.round(time / 1000 / 60 / 60 / 24);
        final TextView date4 = (TextView) findViewById(R.id.date4);
        String s4 = Integer.toString(day);
        date4.setText(s4+'天');
    }
}
