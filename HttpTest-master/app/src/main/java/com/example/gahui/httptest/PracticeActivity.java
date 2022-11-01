package com.example.gahui.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PracticeActivity extends AppCompatActivity {

    private static final String[] mode={"一般模式","随机模式","错题模式"};
    private static final String[] year={"2014","2015","2016","2017","2018","我全都要"};
    private static final String[] subject={"政治","数学","英语","我全都要"};
    private static final String[] school={"哈工大","我全都要"};
    private static final String[] type={"选择题","多选题","分析题","我全都要"};
    private static final String[] num={"5","10","15","20"};

    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private ArrayAdapter<String> adapter3;
    private ArrayAdapter<String> adapter4;
    private ArrayAdapter<String> adapter5;
    private ArrayAdapter<String> adapter6;

    public String mode_;
    public String year_;
    public String subject_;
    public String school_;
    public String type_;
    public String num_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        //final EditText qtime = (EditText) findViewById(R.id.qtime);
        //final EditText subject = (EditText) findViewById(R.id.subject);
        //final EditText qschool = (EditText) findViewById(R.id.qschool);
        //final EditText qtype = (EditText) findViewById(R.id.qtype);
        //final EditText mode = (EditText) findViewById(R.id.mode);
        //final EditText qnum = (EditText) findViewById(R.id.qnum);
        final EditText time_lmt = (EditText) findViewById(R.id.time_lmt);

        Button start = (Button) findViewById(R.id.start);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_mode);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner_year);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner_subject);
        Spinner spinner4 = (Spinner) findViewById(R.id.spinner_school);
        Spinner spinner5 = (Spinner) findViewById(R.id.spinner_type);
        Spinner spinner6 = (Spinner) findViewById(R.id.spinner_num);
        //将可选内容与ArrayAdapter连接起来
        adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mode);
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,year);
        adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subject);
        adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,school);
        adapter5 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,type);
        adapter6 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,num);



        //设置下拉列表的风格
        adapter1.setDropDownViewResource(R.layout.my_spinner_dropdown);
        adapter2.setDropDownViewResource(R.layout.my_spinner_dropdown);
        adapter3.setDropDownViewResource(R.layout.my_spinner_dropdown);
        adapter4.setDropDownViewResource(R.layout.my_spinner_dropdown);
        adapter5.setDropDownViewResource(R.layout.my_spinner_dropdown);
        adapter6.setDropDownViewResource(R.layout.my_spinner_dropdown);

        //将adapter 添加到spinner中
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        spinner4.setAdapter(adapter4);
        spinner5.setAdapter(adapter5);
        spinner6.setAdapter(adapter6);

        //添加事件Spinner事件监听
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //parent就是父控件spinner
            //view就是spinner内填充的textview,id=@android:id/text1
            //position是值所在数组的位置
            //id是值所在行的位置，一般来说与positin一致
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                System.out.println("onItemSelected : parent.id="+parent.getId()+
                        ",isSpinnerId="+(parent.getId() == R.id.spinner_mode)+
                        ",viewid="+view.getId()+ ",pos="+pos+",id="+id);
                //设置spinner内的填充文字居中
                ((TextView)view).setGravity(Gravity.CENTER);
                ((TextView)view).setTextSize(20);
                if(mode[pos].equals("我全都要"))
                    mode_ = "";
                else
                    mode_ = String.valueOf(pos+1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                System.out.println("onItemSelected:pos="+pos+",id="+id);
                //设置spinner内的填充文字居中
                ((TextView)view).setGravity(Gravity.CENTER);
                ((TextView)view).setTextSize(20);
                if(mode[pos].equals("我全都要"))
                    mode_ = "";
                else
                    year_ = year[pos];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                System.out.println("onItemSelected:pos="+pos+",id="+id);
                //设置spinner内的填充文字居中
                ((TextView)view).setGravity(Gravity.CENTER);
                ((TextView)view).setTextSize(20);
                if(subject[pos].equals("我全都要"))
                    subject_ = "";
                else
                    subject_ = subject[pos];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                System.out.println("onItemSelected:pos="+pos+",id="+id);
                //设置spinner内的填充文字居中
                ((TextView)view).setGravity(Gravity.CENTER);
                ((TextView)view).setTextSize(20);
                if(school[pos].equals("我全都要"))
                    school_ = "";
                else
                    school_ = school[pos];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                System.out.println("onItemSelected:pos="+pos+",id="+id);
                //设置spinner内的填充文字居中
                ((TextView)view).setGravity(Gravity.CENTER);
                ((TextView)view).setTextSize(20);
                if(type[pos].equals("我全都要"))
                    type_ = "";
                else
                    type_ = type[pos];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                System.out.println("onItemSelected:pos="+pos+",id="+id);
                //设置spinner内的填充文字居中
                ((TextView)view).setGravity(Gravity.CENTER);
                ((TextView)view).setTextSize(20);
                num_ = num[pos];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final String qtime_ = qtime.getText().toString().trim();
                //final String subject_ = subject.getText().toString().trim();
                //final String qschool_ = qschool.getText().toString().trim();
                //final String qtype_ = qtype.getText().toString().trim();
                //final String mode_ = mode.getText().toString().trim();
                //System.out.println(mode_);
                //final String qnum_ = qnum.getText().toString().trim();
                final String time_lmt_ = time_lmt.getText().toString().trim();

                Intent intent = new Intent(PracticeActivity.this,Practice2Activity.class);
                intent.putExtra("mode", mode_);
                intent.putExtra("qtime", year_);
                intent.putExtra("subject", subject_);
                intent.putExtra("qschool", school_);
                intent.putExtra("qtype", type_);
                intent.putExtra("qnum", num_);
                intent.putExtra("time_lmt", time_lmt_);
                startActivity(intent);
            }
        });
    }
}
