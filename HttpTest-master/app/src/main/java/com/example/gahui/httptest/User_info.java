package com.example.gahui.httptest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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



public class User_info extends Activity {
    public String name2 = MainActivity.name1;
    public static String[] strArr;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Intent intent = getIntent();
        String nickname = intent.getStringExtra("name");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://10.250.106.45:8080/AndroidTest/mustUser?nickname=" + MainActivity.name1;
                try {
                    try {
                        URL url = new URL(path); //新建url并实例化
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");//获取服务器数据
                        connection.setReadTimeout(8000);//设置读取超时的毫秒数
                        connection.setConnectTimeout(8000);//设置连接超时的毫秒数
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String result = "";
                        final TextView nickname1 = (TextView) findViewById(R.id.nickname1);
                        final TextView mail1 = (TextView) findViewById(R.id.mail1);
                        final TextView qq1 = (TextView) findViewById(R.id.qq1);
                        final TextView phone1 = (TextView) findViewById(R.id.phone1);
                        final TextView tschool1 = (TextView) findViewById(R.id.tschool1);
                        final TextView exam_time1 = (TextView) findViewById(R.id.exam_time1);
                        System.out.println("abcdefghijk!!!!!!!!!!"+MainActivity.name1);
                        while ((result = reader.readLine()) != null) {//循环读取响应
                            //System.out.println("--------  "+result);
                            strArr = result.split("\\|");
                            nickname1.setText(MainActivity.name1);
                            mail1.setText(strArr[1]);
                            qq1.setText(strArr[2]);
                            phone1.setText(strArr[3]);
                            tschool1.setText(strArr[4]);
                            exam_time1.setText(strArr[5]);
                        }
                        // String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
                    } catch (MalformedURLException e) {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        ImageButton button = (ImageButton) findViewById(R.id.modify);        //按下返回前一页
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_info.this, com.example.gahui.httptest.modify_userinfo.class);           //回到上一页活动
                startActivity(intent);
            }
        });
        ImageButton button1 = (ImageButton) findViewById(R.id.index);        //按下返回前一页
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_info.this, IndexActivity.class);           //回到上一页活动
                startActivity(intent);
            }
        });

    }
}
