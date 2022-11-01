package com.example.gahui.httptest.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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

import com.example.gahui.httptest.R;


public class NotificationsFragment extends Fragment {

    private com.example.gahui.httptest.ui.notifications.NotificationsViewModel notificationsViewModel;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        notificationsViewModel =
                ViewModelProviders.of(this).get(com.example.gahui.httptest.ui.notifications.NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView date1 = (TextView) root.findViewById(R.id.date1);
        final TextView excatdate1 = (TextView) root.findViewById(R.id.excatdate1);
        long timeMillis = getTimeInMillis(2020, 12, 21, 0, 0, 0);
        Calendar current = Calendar.getInstance(TimeZone.getDefault());
        current.setTimeInMillis(System.currentTimeMillis());
        Calendar date = Calendar.getInstance(TimeZone.getDefault());
        date.setTimeInMillis(timeMillis);
        long time = date.getTimeInMillis() - current.getTimeInMillis();
        int day = Math.round(time / 1000 / 60 / 60 / 24);
        //final TextView date1 = (TextView) findViewById(R.id.date1);
        if(day > 0) {
            String s1 = Integer.toString(day);
            date1.setText(s1);
            excatdate1.setText("2020-12-21");
        }
        else{
            date1.setText("时间已过");
            excatdate1.setText("2020-12-21");
        }

        timeMillis = getTimeInMillis(2020, 10, 10, 0, 0, 0);
        current = Calendar.getInstance(TimeZone.getDefault());
        current.setTimeInMillis(System.currentTimeMillis());
        date = Calendar.getInstance(TimeZone.getDefault());
        date.setTimeInMillis(timeMillis);
        time = date.getTimeInMillis() - current.getTimeInMillis();
        day = Math.round(time / 1000 / 60 / 60 / 24);
        final TextView date2 = (TextView) root.findViewById(R.id.date2);
        final TextView excatdate2 = (TextView) root.findViewById(R.id.excatdate2);
        String s2 = Integer.toString(day);
        date2.setText(s2);
        excatdate2.setText("2020-10-10");

        timeMillis = getTimeInMillis(2020, 11, 11, 0, 0, 0);
        current = Calendar.getInstance(TimeZone.getDefault());
        current.setTimeInMillis(System.currentTimeMillis());
        date = Calendar.getInstance(TimeZone.getDefault());
        date.setTimeInMillis(timeMillis);
        time = date.getTimeInMillis() - current.getTimeInMillis();
        day = Math.round(time / 1000 / 60 / 60 / 24);
        final TextView date3 = (TextView) root.findViewById(R.id.date3);
        final TextView excatdate3 = (TextView) root.findViewById(R.id.excatdate3);
        String s3 = Integer.toString(day);
        date3.setText(s3);
        excatdate3.setText("2020-11-11");

        timeMillis = getTimeInMillis(2020, 12, 4, 0, 0, 0);
        current = Calendar.getInstance(TimeZone.getDefault());
        current.setTimeInMillis(System.currentTimeMillis());
        date = Calendar.getInstance(TimeZone.getDefault());
        date.setTimeInMillis(timeMillis);
        time = date.getTimeInMillis() - current.getTimeInMillis();
        day = Math.round(time / 1000 / 60 / 60 / 24);
        final TextView date4 = (TextView) root.findViewById(R.id.date4);
        final TextView excatdate4 = (TextView) root.findViewById(R.id.excatdate4);
        String s4 = Integer.toString(day);
        date4.setText(s4);
        excatdate4.setText("2020-12-04");
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.rightside_menu_2,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.person:
                Toast.makeText(getActivity(), "person", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}