package com.example.gahui.httptest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.Handler;
import android.os.Message;
import android.widget.ViewFlipper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gahui.httptest.bean.Pickers;
import com.example.gahui.httptest.views.PickerScrollView;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.entity.StringEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.net.URLEncoder;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import android.view.GestureDetector.OnGestureListener;



public class SearchActivity extends AppCompatActivity {

    GestureDetector _GestureDetector;
    private ViewFlipper flipper;
    private Button bt_scrollchoose; // 滚动选择器按钮
    private PickerScrollView pickerscrlllview; // 滚动选择器
    private List<Pickers> list; // 滚动选择器数据
    private String[] id;
    private String[] name;
    private Button bt_yes; // 确定按钮
    private RelativeLayout picker_rel; // 选择器布局

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private Button button2;       //按下返回前一页
    private TextView text4;
    private TextView text5;

    public String result="111";
    public int question = 1;
    public String strArr[][] = new String[3][6];
    public String[] str1 = new String[3];
    public String[] str = new String[3];
    public String[] qid = new String [3];
    public String ques = "";
    public String ans = "";
    public int flag = 0;
    public int flag1 = 0;
    private Handler handler=null;
    private TextView textView1=null;
    private TextView textView2=null;
    private TextView textView3=null;
    private String type ;


    //private TextView textView4=null;
    // 构建Runnable对象，在runnable中更新界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        handler=new Handler();
        textView1=(TextView)findViewById(R.id.Q1);
        textView2=(TextView)findViewById(R.id.Q2);
        textView3=(TextView)findViewById(R.id.Q3);
        flipper = (ViewFlipper) this.findViewById(R.id.scrollQ);
        _GestureDetector = new GestureDetector(new GestureCallBack());
        Switch mSwitch = (Switch) this.findViewById(R.id.switch1);
        text1 = (TextView) this.findViewById(R.id.accounttext2);
        text2 = (TextView) this.findViewById(R.id.subject);
        text3 = (TextView) this.findViewById(R.id.accounttext3);
        button2 = (Button) findViewById(R.id.bt_scrollchoose3);        //按下返回前一页
        text4 = (TextView) this.findViewById(R.id.keyword_text);
        text5 = (TextView) this.findViewById(R.id.keyword);

        initView();
        initLinstener();
        initData();

        final EditText subject1 = (EditText) findViewById(R.id.subject);
        final EditText keyword1 = (EditText) findViewById(R.id.keyword);
        Button click = (Button) findViewById(R.id.click);
        Button look = (Button) findViewById(R.id.look);
        ImageButton favorite_button = (ImageButton) findViewById(R.id.favorite_button2);
        Runnable  runnableUi1=new  Runnable(){
            @Override
            public void run() {
                //更新界面
                // handler.postDelayed(this, 1000);
                System.out.println("------------    get here    "+strArr[1][0]+strArr[0][5]);
                String temp = strArr[0][0]+"\n"+strArr[0][1]+"\n"+strArr[0][2]+"\n"+strArr[0][3]+"\n"+strArr[0][4];
                textView1.setText(temp);
               //textView4.setText(strArr[0][5]);

            }
        };

        // 构建Runnable对象，在runnable中更新界面
        Runnable  runnableUi2=new  Runnable(){
            @Override
            public void run() {
                //更新界面
                System.out.println("------------get here    "+strArr[1][1]+strArr[1][5]);
                //textView1.setText(result);
                String temp = strArr[1][0]+"\n"+strArr[1][1]+"\n"+strArr[1][2]+"\n"+strArr[1][3]+"\n"+strArr[1][4];
                textView2.setText(temp);
                //textView5.setText(strArr[1][5]);
            }
        };

        // 构建Runnable对象，在runnable中更新界面
        Runnable  runnableUi3=new  Runnable(){
            @Override
            public void run() {
                //更新界面
                System.out.println("------------get here    "+strArr[2][1]+strArr[2][5]);
                //textView1.setText(result);
                String temp = strArr[2][0]+"\n"+strArr[2][1]+"\n"+strArr[2][2]+"\n"+strArr[2][3]+"\n"+strArr[2][4];
                textView3.setText(temp);
            }
        };
        Runnable  runnableUi4=new  Runnable(){
            @Override
            public void run() {
                //更新界面
               // System.out.println("------------分析题：：："+str1);
                //textView1.setText(result);
                textView1.setText(str1[0]);
               // textView4.setText(str[0]);
            }
        };
        Runnable  runnableUi5=new  Runnable(){
            @Override
            public void run() {
                //更新界面
                // System.out.println("------------分析题：：："+str1);
                //textView1.setText(result);
                textView2.setText(str1[1]);
            }
        };
        Runnable  runnableUi6=new  Runnable(){
            @Override
            public void run() {
                //更新界面
                // System.out.println("------------分析题：：："+str1);
                //textView1.setText(result);
                textView3.setText(str1[2]);
            }
        };

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                final String subject = subject1.getText().toString().trim();
                final String keyword = keyword1.getText().toString().trim();
                if(subject.equals("")) {
                    flag1 = 1;
                    System.out.println("no get subject info");
                }
                else if(keyword.equals("")){
                    flag1 = 2;
                    System.out.println("no get keword info");
                }
                System.out.println(subject+keyword);
                switch (view.getId()) {
                    case R.id.click:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                               // subject = URLEncoder.encode(subject,"utf-8")
                                    //System.out.println(subject);}
                                    String path = null;
                                try {
                                    try {
                                        if(flag1 == 1){
                                            path = "http://10.250.106.45:8080/AndroidTest/mustByword?word=" + java.net.URLEncoder.encode(keyword);
                                        }
                                        else if(flag1 ==2){
                                             path = "http://10.250.106.45:8080/AndroidTest/mustSearch?subject=" + java.net.URLEncoder.encode(subject) + "&type=" + java.net.URLEncoder.encode(type);
                                        }
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
                                        int i = 0;
                                       // StringBuilder result = new StringBuilder();  //StringBuilder初始化不可以null
                                            flag = 0;
                                            i = 0;
                                            ans = "";
                                            ques = "";
                                            //System.out.println("get herrrrrrrr");
                                            while ((result = reader.readLine()) != null) {//循环读取响应
                                                //message.obj = result;
                                                //handler.handleMessage(message);
                                                if(result.equals(" qid ")){
                                                    //System.out.println("get herrrrrrrrrrrr   "+flag);
                                                    result = reader.readLine();
                                                    qid[i] = result;
                                                    result = reader.readLine();
                                                    System.out.println("search______  "+qid[i]);
                                                }
                                                if(result.equals(" answer ")){
                                                    //System.out.println("get herrrrrrrrrrrr   "+flag);
                                                    flag = 1;
                                                }
                                                else if(result.equals(" end ")){
                                                  // System.out.println("get herrrrrrrrrrrr "+flag);
                                                    flag = 0;
                                                    str[i] = ans;
                                                    str1[i] = ques;
                                                    i = i + 1;
                                                    ans = "";
                                                    ques = "";
                                                   // System.out.println("get resssssssssssssss   ");
                                                }
                                                else if(flag == 1){
                                                    ans = ans  + result+'\n';
                                                   //System.out.println("get herrrrrrrrrrrr  "+ans);
                                                }
                                                else{
                                                    ques = ques  + result+'\n';
                                                }
                                               // System.out.println("get herrrrrrrrrrrr   "+ques);
                                            }
                                            ans = str[0];
                                            ques = str1[0];
                                            //System.out.println("aaaaaa"+ques);
                                            //System.out.println("aaaaaa"+str1[1]);
                                            handler.post(runnableUi4);
                                            handler.post(runnableUi5);
                                            handler.post(runnableUi6);
                                       // String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
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
        favorite_button.setOnClickListener(new View.OnClickListener() {
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
                                if(question == 1) {
                                    path = "http://10.250.106.45:8080/AndroidTest/mustBygood?uid=" + MainActivity.uid + "&qid=" + qid[0] + "&btype=1" + "&btime=" + dtime;
                                    System.out.println("get the number of the q"+question);
                                }
                                if(question == 2) {
                                    path = "http://10.250.106.45:8080/AndroidTest/mustBygood?uid=" + MainActivity.uid + "&qid=" + qid[1] + "&btype=1" + "&btime=" + dtime;
                                    System.out.println("get the number of the q"+question);
                                }
                                if(question == 3) {
                                    path = "http://10.250.106.45:8080/AndroidTest/mustBygood?uid=" + MainActivity.uid + "&qid=" + qid[2] + "&btype=1" + "&btime=" + dtime;
                                    System.out.println("get the number of the q"+question);
                                }
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
                                result = reader.readLine();
                                if (result.equals("add successfully!")) {
                                    Log.d("SearchActivity", "run2: " + result);
                                    Looper.prepare();
                                    Log.d("SearchActivity", "run3: " + result);
                                    Toast.makeText(SearchActivity.this, "You add question successfully!", Toast.LENGTH_SHORT).show();
                                    Log.d("SearchActivity", "run4: " + result);
                                    Looper.loop();
                                }
                                else if (result.equals("can not add!")) {
                                    Log.d("SearchActivity", "run2: " + result);
                                    Looper.prepare();
                                    Log.d("SearchActivity", "run3: " + result);
                                    Toast.makeText(SearchActivity.this, "You cannot add this question!", Toast.LENGTH_SHORT).show();
                                    Log.d("SearchActivity", "run4: " + result);
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
            }
        });
        look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,DetailsActivity.class);
                intent.putExtra("flag", "false");
                intent.putExtra("str", ans);
                intent.putExtra("str1", ques);
                startActivity(intent);
                finish();
            }
        });
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //选中状态 可以做一些操作
                    text1.setVisibility(View.INVISIBLE);
                    text2.setVisibility(View.INVISIBLE);
                    text3.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    text4.setVisibility(View.VISIBLE);
                    text5.setVisibility(View.VISIBLE);
                }else {
                    //未选中状态 可以做一些操作
                    text1.setVisibility(View.VISIBLE);
                    text2.setVisibility(View.VISIBLE);
                    text3.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    text4.setVisibility(View.INVISIBLE);
                    text5.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        //当触摸 事件触发时，自己不处理，交给这个_GestureDetector来处理
        return _GestureDetector.onTouchEvent(event);
    }

    //触摸 事件的处理
    //事件的回调
    class GestureCallBack implements OnGestureListener
    {

        @Override
        public boolean onDown(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            float x1 = e1.getY();
            float x2 = e2.getY();

            //e1是第一次触碰的位置，e2是结束触碰的位置
            if (x1 - x2 < -100) {

                Animation animation =
                        AnimationUtils.loadAnimation(getApplicationContext(),R.anim.filp_l2r);
                //指定一个动画
                SearchActivity.this.flipper.setAnimation(animation);
                //让flipper 前移
                SearchActivity.this.flipper.showPrevious();

            } else if (x1 - x2 > 100) {

                Animation animation =
                        AnimationUtils.loadAnimation(getApplicationContext(), R.anim.filp_r2l);

                SearchActivity.this.flipper.setAnimation(animation);
                //让flipper 显示下一个控件
                SearchActivity.this.flipper.showNext();

            } else {
                return false;
            }
            int position = flipper.getDisplayedChild();
            System.out.println(position);
            if (position == 0) {
                TextView text = (TextView)findViewById(R.id.text_Q1_out);
                text.setTextColor(android.graphics.Color.parseColor("#F686B3"));
                TextView text2 = (TextView)findViewById(R.id.text_Q2_out);
                text2.setTextColor(android.graphics.Color.parseColor("#938192"));
                TextView text3 = (TextView)findViewById(R.id.text_Q3_out);
                text3.setTextColor(android.graphics.Color.parseColor("#938192"));
                question = 1;

                    ans = str[0];
                    ques = str1[0];
            }
            else if (position == 1) {
                TextView text = (TextView)findViewById(R.id.text_Q1_out);
                text.setTextColor(android.graphics.Color.parseColor("#938192"));
                TextView text2 = (TextView)findViewById(R.id.text_Q2_out);
                text2.setTextColor(android.graphics.Color.parseColor("#F686B3"));
                TextView text3 = (TextView)findViewById(R.id.text_Q3_out);
                text3.setTextColor(android.graphics.Color.parseColor("#938192"));
                question = 2;

                    ans = str[1];
                    ques = str1[1];

            }
            else if (position == 2) {
                TextView text = (TextView)findViewById(R.id.text_Q1_out);
                text.setTextColor(android.graphics.Color.parseColor("#938192"));
                TextView text2 = (TextView)findViewById(R.id.text_Q2_out);
                text2.setTextColor(android.graphics.Color.parseColor("#938192"));
                TextView text3 = (TextView)findViewById(R.id.text_Q3_out);
                text3.setTextColor(android.graphics.Color.parseColor("#F686B3"));
                question = 3;

                    ans = str[2];
                    ques = str1[2];

            }
            return true;

        }

        @Override
        public void onLongPress(MotionEvent e) {
            //默认.
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            //默认.
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            //默认.

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            //默认.
            return false;
        }

    }

    /**
     * 初始化
     */
    private void initView() {
        bt_scrollchoose = (Button) findViewById(R.id.bt_scrollchoose3);
        picker_rel = (RelativeLayout) findViewById(R.id.picker_rel);
        pickerscrlllview = (PickerScrollView) findViewById(R.id.pickerscrlllview);
        bt_yes = (Button) findViewById(R.id.picker_yes);
    }

    /**
     * 设置监听事件
     */
    private void initLinstener() {
        bt_scrollchoose.setOnClickListener(onClickListener);
        pickerscrlllview.setOnSelectListener(pickerListener);
        bt_yes.setOnClickListener(onClickListener);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<Pickers>();
        id = new String[] { "1", "2", "3" };
        name = new String[] { "选择题","多选题","分析题" };
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(list);
        pickerscrlllview.setSelected(0);
    }

    // 滚动选择器选中事件
    PickerScrollView.onSelectListener pickerListener = new PickerScrollView.onSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
            System.out.println("选择：" + pickers.getShowId()
                    + pickers.getShowConetnt());
            bt_scrollchoose.setText(pickers.getShowConetnt());
            type  = pickers.getShowConetnt();
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
            else if (v == bt_yes) {
                picker_rel.setVisibility(View.GONE);
                bt_scrollchoose.setVisibility(View.VISIBLE);
            }
        }
    };


}
