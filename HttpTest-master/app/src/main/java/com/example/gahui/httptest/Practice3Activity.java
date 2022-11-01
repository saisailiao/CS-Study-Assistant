package com.example.gahui.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Practice3Activity extends AppCompatActivity {
    private TextView score_view;
    private TextView time_view;
    String [] all_ans;
    String [] usr_ans;
    String [] all_qid;
    public static String [] mistakes = new String[50];
    public String [] mistakes_qid = new String[50];
    public String mistakes_qid_str;
    public int correct = 0;
    public int i = 0;
    public static int mst = 0;
    public String result="111";
    String score_text;
    String mistakes_text;
    public int [] mistakes_int = new int[50];
    public int sum_anlz=0;
    public int [] id_anlz = new int[50];
    private ArrayList str1 =  new ArrayList<String>();
    private ArrayList str =  new ArrayList<String>();
    private ArrayList str2 =  new ArrayList<String>();
    /*****************计时器******************
     * 根据毫秒返回时分秒
     * @param time
     * @return
     */
    public static String getFormatHMS(long time){
        time=time/1000;//总秒数
        int s= (int) (time%60);//秒
        int m= (int) (time/60);//分
        int h=(int) (time/3600);//时
        System.out.println(String.valueOf(s));
        return String.format("%02d:%02d:%02d",h,m,s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice3);
        Intent intent = getIntent();
        mst = 0;
        String qid = intent.getStringExtra("qid");
        String all = intent.getStringExtra("all");
        String usr = intent.getStringExtra("usr");
        String study_time = intent.getStringExtra("study_time");
        String sum_str = intent.getStringExtra("sum");
        long time_msec = Long.valueOf(study_time);
        long time_sec = time_msec/1000;
        all_qid= qid.split(",");
        all_ans= all.split(",");
        usr_ans = usr.split(",");
        int sum = Integer.valueOf(sum_str);
        int sum_obj = 0;
        score_view =(TextView)findViewById(R.id.time1);
        time_view =(TextView)findViewById(R.id.time);
        time_view.setText("总时间"+getFormatHMS(time_msec));
        System.out.println(all);
        System.out.println(usr);
        for(i=0;i<sum;i++)
        {
            if(Practice2Activity.isAnlz[i] == 0) //客观题
            {
                if (all_ans[i].equals(usr_ans[i])) {
                    correct++;
                } else {
                    mistakes[mst] = String.valueOf(i + 1);//记录题目在此套卷子中的序号
                    mistakes_qid[mst] = all_qid[i];//记录错题qid
                    mst++;
                }
                sum_obj++;
            }
        }
        score_text =  String.valueOf(correct)+"/"+String.valueOf(sum_obj);
        score_view.setText(score_text);
        mistakes_qid_str = StringUtils.join(mistakes_qid,",",0,mst);

        int i;
        for (i=0;i<mst;i++) {
            mistakes_int[i] = Integer.valueOf(mistakes[i]) - 1;
            str.add("错题:"+ String.valueOf(i+1)+"正确答案："+all_ans[mistakes_int[i]]+"你的答案："+Practice2Activity.usr_ans[mistakes_int[i]]);
        }
        for (i=0;i<50;i++)
            if(Practice2Activity.isAnlz[i] == 1) {
                id_anlz[sum_anlz]=i;
                sum_anlz++;
                str.add("分析题:"+String.valueOf(i+1)+"正确答案：查看解析");
            }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Practice3Activity.this,R.layout.list_item , str);
        System.out.println("!!!!!!"+str.get(0));
        ListView listView = (ListView)findViewById(R.id.list_view3);
        //将构建好的适配器对象传进去
        listView.setAdapter(adapter);


        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://10.250.106.45:8080/AndroidTest/mustPractice_res?mistakes_qid=" + mistakes_qid_str+ "&uid=" + MainActivity.uid + "&num_q=" + sum_str +"&num_incorrect=" + String.valueOf(mst) + "&study_time=" + String.valueOf(time_sec);
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
                        result = reader.readLine();
                        if(result.equals("write data successfully!"))
                        {
                            System.out.println("write data successfully!");
                        }

                    } catch (MalformedURLException e) {
                        System.out.println("Error exists!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Button btn_main = (Button) findViewById(R.id.btn_main);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Practice3Activity.this,IndexActivity.class);
                mst = 0;
                startActivity(intent);
            }
        });

        Button btn_mistakes = (Button) findViewById(R.id.btn_mistakes);
        btn_mistakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Practice3Activity.this,PracticeMstActivity.class);
                startActivity(intent);
            }
        });



    }


}
