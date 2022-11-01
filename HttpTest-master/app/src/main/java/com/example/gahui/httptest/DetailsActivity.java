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


public class DetailsActivity extends Activity {
    public String context;
    private TextView show1=null;
    private Handler handler=null;
    private TextView text1;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
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
        String num = intent.getStringExtra("flag");
        String qid = intent.getStringExtra("qid");
        System.out.println("delete!!"+num);
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
                answer4.setText(str);
                // String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
            }
        }).start();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num.equals("true")) {
                    //选中状态 可以做一些操作
                    Intent intent = new Intent(DetailsActivity.this, Favourite_eachActivity.class);           //回到上一页活动
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(DetailsActivity.this, SearchActivity.class);           //回到上一页活动
                    startActivity(intent);
                    finish();
                }
            }
        });
        text1 = (TextView) this.findViewById(R.id.delete_text);
        if (num.equals("true")) {
            //选中状态 可以做一些操作
            text1.setVisibility(View.VISIBLE);          //若在，则显示删除文本
        }
        else{
            text1.setVisibility(View.GONE);
        }
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // subject = URLEncoder.encode(subject,"utf-8")
                        System.out.println("GET HERE!!"+MainActivity.uid);
                        String path = null;
                        try {
                            try {
                                Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   对时间进行加减操作等
                                int year = c.get(Calendar.YEAR);
                                int month = c.get(Calendar.MONTH);
                                int date = c.get(Calendar.DATE);
                                String dtime = year + "-" + month + "-" + date;
                                System.out.println(year + "-" + month + "-" + date);
                                path = "http://10.250.106.45:8080/AndroidTest/mustDeletegood?uid=" + MainActivity.uid + "&qid=" + qid + "&btype=1" ;
                                URL url = new URL(path); //新建url并实例化
                                //url.setEntity(new StringEntity(url.toString(),"UTF-8"));
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("GET");//获取服务器数据
                                connection.setReadTimeout(8000);//设置读取超时的毫秒数
                                connection.setConnectTimeout(8000);//设置连接超时的毫秒数
                                InputStream in = connection.getInputStream();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                                //String result = "";
                                Message message = handler.obtainMessage();
                                String result = reader.readLine();
                                if (result.equals("delete successfully!")) {
                                    Log.d("DetailsActivity", "run2: " + result);
                                    Looper.prepare();
                                    Log.d("DetailsActivity", "run3: " + result);
                                    //Toast.makeText(DetailsActivity.this, "You delete question successfully!", Toast.LENGTH_SHORT).show();
                                    Log.d("DetailsActivity", "run4: " + result);
                                    Looper.loop();
                                }
                                else if (result.equals("can not delete!")) {
                                    Log.d("DetailsActivity", "run2: " + result);
                                    Looper.prepare();
                                    Log.d("DetailsActivity", "run3: " + result);
                                   // Toast.makeText(DetailsActivity.this, "You cannot delete this question!", Toast.LENGTH_SHORT).show();
                                    Log.d("DetailsActivity", "run4: " + result);
                                    Looper.loop();
                                }
                                // String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
                            } catch (MalformedURLException e) {
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Toast.makeText(DetailsActivity.this,"delete",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
