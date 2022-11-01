package com.example.gahui.httptest;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class IndexActivity extends AppCompatActivity {
    public static int a,b,c,d,e,f,g,j,h,k,l;
    public static String[] strArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ImageButton info = (ImageButton) findViewById(R.id.info2);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://10.250.106.45:8080/AndroidTest/mustStudio?uid=" + MainActivity.uid;
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
                        System.out.println("abcdefghijk!!!!!!!!!!"+ MainActivity.name1);
                        int i =0;
                        while ((result = reader.readLine()) != null) {//循环读取响应
                            System.out.println("--------  " + result);
                            strArr = result.split("\\|");
                            // i = i + 1;
                        }
                        a = Integer.valueOf(strArr[0]);
                        b = Integer.valueOf(strArr[1]);
                        c = Integer.valueOf(strArr[2]);
                        d = Integer.valueOf(strArr[3]);
                        e = Integer.valueOf(strArr[4]);
                        f = Integer.valueOf(strArr[5]);
                        g = Integer.valueOf(strArr[6]);
                        h = Integer.valueOf(strArr[7]);
                        j = Integer.valueOf(strArr[9]);
                        k = (int)(j*((float)h/100));
                        l = j - k;
                        //System.out.println(Integer.valueOf(strArr[1]));
                        System.out.println("dash"+a);
                        System.out.println("dash"+b);
                        // String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
                    } catch (MalformedURLException e) {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this,User_info.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
