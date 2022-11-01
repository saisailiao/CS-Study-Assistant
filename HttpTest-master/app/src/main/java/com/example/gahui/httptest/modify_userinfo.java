package com.example.gahui.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gahui.httptest.bean.Pickers;
import com.example.gahui.httptest.views.PickerScrollView;
import com.example.gahui.httptest.views.PickerScrollView.onSelectListener;
import com.example.gahui.httptest.R;
import com.example.gahui.httptest.bean.Pickers;
import com.example.gahui.httptest.views.PickerScrollView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class modify_userinfo extends AppCompatActivity {

    private Button bt_scrollchoose; // 滚动选择器按钮
    private Button bt_scrollchoose2; // 滚动选择器按钮
    private PickerScrollView pickerscrlllview; // 滚动选择器
    private PickerScrollView pickerscrlllview2; // 滚动选择器
    private List<Pickers> list; // 滚动选择器数据
    private List<Pickers> list2; // 滚动选择器数据
    private String[] id;
    private String[] id2;
    private String[] name;
    private String[] name2;
    private String school = User_info.strArr[4] ;
    private String exam_time = User_info.strArr[5];

    private Button bt_yes; // 确定按钮
    private Button bt_yes2; // 确定按钮
    private RelativeLayout picker_rel; // 选择器布局
    private RelativeLayout picker_rel2; // 选择器布局

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_userinfo);
        initView();
        initLinstener();
        initData();
        final TextView nickname1 = (TextView) findViewById(R.id.nickname1);
        final EditText Phone = (EditText) findViewById(R.id.phone);
        final EditText mail = (EditText) findViewById(R.id.mail);
        final EditText qq = (EditText) findViewById(R.id.qq);
        nickname1.setText(MainActivity.name1);
        mail.setText(User_info.strArr[1]);
        Phone.setText(User_info.strArr[3]);
        qq.setText(User_info.strArr[2]);
        Button button3 = (Button) findViewById(R.id.sure);        //按下确定
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mail.getText().toString().trim();
                final String phone_num = Phone.getText().toString().trim();
                final String qq_num = qq.getText().toString().trim();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String path = "http://10.250.106.45:8080/AndroidTest/mustUpdate?goal_school=" + java.net.URLEncoder.encode(school)  + "&Phone=" + phone_num  + "&qq=" + qq_num + "&mail=" + email + "&exam_time=" + exam_time +"&uid=" + MainActivity.uid;
                        System.out.println(path);
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
                                Log.d("modify_userinfo", "run: " + result);
                                if (result.equals("update successfully!")) {
                                    Log.d("modify_userinfo", "run2: " + result);
                                    Looper.prepare();
                                    Log.d("modify_userinfo", "run3: " + result);
                                    Intent intent = new Intent(modify_userinfo.this, User_info.class);           //回到上一页活动
                                    startActivity(intent);
                                    Toast.makeText(modify_userinfo.this, "You update successfully!", Toast.LENGTH_SHORT).show();
                                    Log.d("modify_userinfo", "run4: " + result);
                                    Looper.loop();

                                }
                            } catch (MalformedURLException e) {
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    /**
     * 初始化
     */
    private void initView() {
        bt_scrollchoose = (Button) findViewById(R.id.bt_scrollchoose);
        bt_scrollchoose2 = (Button) findViewById(R.id.bt_scrollchoose2);
        picker_rel = (RelativeLayout) findViewById(R.id.picker_rel);
        picker_rel2 = (RelativeLayout) findViewById(R.id.picker_rel2);
        pickerscrlllview = (PickerScrollView) findViewById(R.id.pickerscrlllview);
        pickerscrlllview2  = (PickerScrollView) findViewById(R.id.pickerscrlllview2);
        bt_yes = (Button) findViewById(R.id.picker_yes);
        bt_yes2 = (Button) findViewById(R.id.picker_yes2);
    }

    /**
     * 设置监听事件
     */
    private void initLinstener() {
        bt_scrollchoose.setOnClickListener(onClickListener);
        bt_scrollchoose2.setOnClickListener(onClickListener);
        pickerscrlllview.setOnSelectListener(pickerListener);
        pickerscrlllview2.setOnSelectListener(pickerListener2);
        bt_yes.setOnClickListener(onClickListener);
        bt_yes2.setOnClickListener(onClickListener);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<Pickers>();
        list2 = new ArrayList<Pickers>();
        id = new String[] { "1", "2", "3" };
        name = new String[] { "2020","2021","2022" };
        id2 = new String[] { "1", "2", "3" };
        name2 = new String[] { "哈尔滨工业大学","哈尔滨工业大学（威海）","哈尔滨工业大学（深圳）" };
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        for (int i = 0; i < name2.length; i++) {
            list2.add(new Pickers(name2[i], id2[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(list);
        pickerscrlllview.setSelected(0);

        pickerscrlllview2.setData(list2);
        pickerscrlllview2.setSelected(0);
    }

    // 滚动选择器选中事件
    onSelectListener pickerListener = new onSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
            System.out.println("选择：" + pickers.getShowId()
                    + pickers.getShowConetnt());
            bt_scrollchoose.setText(pickers.getShowConetnt());
            exam_time = pickers.getShowConetnt();
            System.out.println(exam_time);
        }
    };

    onSelectListener pickerListener2 = new onSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
            System.out.println("选择：" + pickers.getShowId()
                    + pickers.getShowConetnt());
            bt_scrollchoose2.setText(pickers.getShowConetnt());
            school = pickers.getShowConetnt();
            System.out.println(school);
        }
    };

    // 点击监听事件
    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v == bt_scrollchoose) {
                picker_rel.setVisibility(View.VISIBLE);
                bt_scrollchoose.setVisibility(View.INVISIBLE);
            }
            else if(v ==  bt_scrollchoose2){
                picker_rel2.setVisibility(View.VISIBLE);
                bt_scrollchoose2.setVisibility(View.INVISIBLE);
            }
            else if (v == bt_yes) {
                picker_rel.setVisibility(View.GONE);
                bt_scrollchoose.setVisibility(View.VISIBLE);

            }
            else if (v == bt_yes2){
                picker_rel2.setVisibility(View.GONE);
                bt_scrollchoose2.setVisibility(View.VISIBLE);
            }
        }
    };
}
