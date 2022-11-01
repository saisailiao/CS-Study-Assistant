package com.example.gahui.httptest;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {
    public static String name1;
    public static String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final EditText logname = (EditText) findViewById(R.id.logname);
        final EditText password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);
        Button Register = (Button) findViewById(R.id.Register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = logname.getText().toString().trim();
                final String psw = password.getText().toString().trim();
                System.out.println("abcdefghijk------------"+name);
                switch (view.getId()) {
                    //当点击登录按钮时
                    case R.id.login:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String path = "http://10.250.106.45:8080/AndroidTest/mustLogin?logname=" + name + "&password=" + psw;
                                try {
                                    try {
                                        URL url = new URL(path); //新建url并实例化
                                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                        connection.setRequestMethod("GET");//获取服务器数据
                                        connection.setReadTimeout(8000);//设置读取超时的毫秒数
                                        connection.setConnectTimeout(8000);//设置连接超时的毫秒数
                                        InputStream in = connection.getInputStream();
                                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                                        String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
                                        Log.d("MainActivity", "run: " + result);
                                        uid = result;
                                        //System.out.println("abcdefghijk------------"+uid);
                                        result = reader.readLine();
                                        if (result.equals("login successfully!")) {
                                            name1 = name;
                                            System.out.println("abcdefghijk------------"+name1);
                                            Log.d("MainActivity", "run2: " + result);
                                            Looper.prepare();
                                            Log.d("MainActivity", "run3: " + result);
                                            Intent intent1 = new Intent(MainActivity.this,IndexActivity.class);
                                            intent1.putExtra("name", name);
                                            startActivity(intent1);
                                            Toast.makeText(MainActivity.this, "You logined successfully!", Toast.LENGTH_SHORT).show();
                                            Log.d("MainActivity", "run4: " + result);
                                            Looper.loop();

                                        }
                                    } catch (MalformedURLException e) {
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        break;
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
