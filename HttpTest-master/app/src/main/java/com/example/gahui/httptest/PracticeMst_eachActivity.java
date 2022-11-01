package com.example.gahui.httptest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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
import java.util.Calendar;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


public class PracticeMst_eachActivity extends Activity {
    public String context;
    private TextView show1=null;
    private Handler handler=null;
    private TextView text1;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_mst_each);
        handler=new Handler();
        show1=(TextView)findViewById(R.id.show1);
        Runnable  runnableUi1=new  Runnable(){
            @Override
            public void run() {
                //更新界面
                // handler.postDelayed(this, 1000);
                show1.setText(context);

            }
        };
        Intent intent = getIntent();
        String str = intent.getStringExtra("str");
        String str1 = intent.getStringExtra("str1");
        String str2 = intent.getStringExtra("str2");
        Button button3 = (Button) findViewById(R.id.back);        //按下返回前一页
        context = str;
        new Thread(new Runnable() {
            @Override
            public void run() {
                //System.out.println("进入了函数          "+str1);
                final TextView show1 = (TextView) findViewById(R.id.show1);
                show1.setMovementMethod(ScrollingMovementMethod.getInstance());
                show1.setText(str1);
                final TextView answer4 = (TextView) findViewById(R.id.answer4);
                answer4.setMovementMethod(ScrollingMovementMethod.getInstance());
                answer4.setText("你的答案是："+str2+"\n\n-----------------------------------------------------\n正确答案是："+str);
                // String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
            }
        }).start();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //选中状态 可以做一些操作
                Intent intent = new Intent(PracticeMst_eachActivity.this, PracticeMstActivity.class);           //回到上一页活动
                startActivity(intent);
                finish();
            }
        });

    }
}
