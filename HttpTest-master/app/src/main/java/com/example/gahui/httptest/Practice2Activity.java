package com.example.gahui.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Practice2Activity extends AppCompatActivity {

    private Handler handler=null;
    public String result="111";
    private TextView progressok_q=null;
    private TextView progressnot_q=null;
    public static String[] all_qid = new String[50];
    public static String[] all_ctnt = new String[50];
    public static String[] all_ans = new String[50];
    public static String[] usr_ans = new String[50];
    public int sum = -1;
    public int cnt = 0;
    public int flag = 0;
    public int i = 0;
    public long starttime = 0;
    public long endtime = 0;
    public long time_msec = 0;
    public long time_lmt_sec = 0;
    public long time_res;
    public String progress_str;
    public String progress_str2;
    public static int [] isAnlz = new int[50];
    public static int [] isMulti = new int[50];

    private RadioGroup multiple;
    private RadioButton radioButton_A,radioButton_B,radioButton_C,radioButton_D;
    private CheckBox CheckA,CheckB,CheckC,CheckD;
    public int type;
    public int number_q;
    private String ans = "";
    private int select[] = new int [4];

    //计时器
    private Handler mhandle = new Handler();
    private boolean isPause = false;//是否暂停
    private long currentSecond = 0;//当前毫秒数

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
        return String.format("%02d:%02d:%02d",h,m,s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice2);
        handler=new Handler();
        final EditText answer = (EditText) findViewById(R.id.answer);
        answer.setMovementMethod(ScrollingMovementMethod.getInstance());
        Runnable  runnableUi0=new  Runnable(){
            @Override
            public void run() {
                multiple.setVisibility(View.VISIBLE);
                radioButton_A.setVisibility(View.VISIBLE);
                radioButton_B.setVisibility(View.VISIBLE);
                radioButton_C.setVisibility(View.VISIBLE);
                radioButton_D.setVisibility(View.VISIBLE);
                CheckA.setVisibility(View.INVISIBLE);
                CheckB.setVisibility(View.INVISIBLE);
                CheckC.setVisibility(View.INVISIBLE);
                CheckD.setVisibility(View.INVISIBLE);
                answer.setVisibility(View.INVISIBLE);

            }
        };
        Runnable  runnableUi1=new  Runnable(){
            @Override
            public void run() {
                multiple.setVisibility(View.INVISIBLE);
                radioButton_A.setVisibility(View.INVISIBLE);
                radioButton_B.setVisibility(View.INVISIBLE);
                radioButton_C.setVisibility(View.INVISIBLE);
                radioButton_D.setVisibility(View.INVISIBLE);
                CheckA.setVisibility(View.VISIBLE);
                CheckB.setVisibility(View.VISIBLE);
                CheckC.setVisibility(View.VISIBLE);
                CheckD.setVisibility(View.VISIBLE);
                answer.setVisibility(View.INVISIBLE);

            }
        };
        Runnable  runnableUi2=new  Runnable(){
            @Override
            public void run() {
                multiple.setVisibility(View.INVISIBLE);
                radioButton_A.setVisibility(View.INVISIBLE);
                radioButton_B.setVisibility(View.INVISIBLE);
                radioButton_C.setVisibility(View.INVISIBLE);
                radioButton_D.setVisibility(View.INVISIBLE);
                CheckA.setVisibility(View.INVISIBLE);
                CheckB.setVisibility(View.INVISIBLE);
                CheckC.setVisibility(View.INVISIBLE);
                CheckD.setVisibility(View.INVISIBLE);
                answer.setVisibility(View.VISIBLE);

            }
        };

        Intent intent = getIntent();
        String mode_str = intent.getStringExtra("mode");
        String qtime = intent.getStringExtra("qtime");
        String subject = intent.getStringExtra("subject");
        String qschool = intent.getStringExtra("qschool");
        String qtype = intent.getStringExtra("qtype");
        String qnum = intent.getStringExtra("qnum");
        String time_lmt_str = intent.getStringExtra("time_lmt");
        time_lmt_sec = Long.valueOf(time_lmt_str) * 60;

        /*textView=(TextView)findViewById(R.id.Q1);
        timerView=(TextView)findViewById(R.id.timerView);*/
        progressok_q=(TextView)findViewById(R.id.progressok_q);
        progressnot_q=(TextView)findViewById(R.id.progressnot_q);
        Button next = (Button) findViewById(R.id.next);
        Button prev = (Button) findViewById(R.id.prev);

        //!!!!!!!!!!!!!!!!!!!!!!!!1
        //type = 1;       //选择题型
        number_q = 10;
        multiple = (RadioGroup) this.findViewById(R.id.select);
        radioButton_A = (RadioButton) this.findViewById(R.id.selectA);
        radioButton_B = (RadioButton) this.findViewById(R.id.selectB);
        radioButton_C = (RadioButton) this.findViewById(R.id.selectC);
        radioButton_D = (RadioButton) this.findViewById(R.id.selectD);
        CheckA = (CheckBox) this.findViewById(R.id.selectA2);
        CheckB = (CheckBox) this.findViewById(R.id.selectB2);
        CheckC = (CheckBox) this.findViewById(R.id.selectC2);
        CheckD = (CheckBox) this.findViewById(R.id.selectD2);

        final TextView question = (TextView) findViewById(R.id.show1);
        question.setMovementMethod(ScrollingMovementMethod.getInstance());

        /*****************计时器*******************/
        new Thread(new Runnable() {
            @Override
            public void run() {

                currentSecond = currentSecond + 1000;
                //String tmp = getFormatHMS(currentSecond);
                time_res = time_lmt_sec - currentSecond/1000;
                String tmp_min = String.valueOf(time_res/60);
                String tmp_sec = String.valueOf(time_res%60);
                if(time_res == 0)
                {
                    isPause = true;
                }
                if (!isPause) {
                    //递归调用本runable对象，实现每隔一秒一次执行任务
                    mhandle.postDelayed(this, 1000);
                }
                else if(time_res == 0 && isPause == true)
                {
                    Intent intent = new Intent(Practice2Activity.this,Practice3Activity.class);
                    time_msec = time_lmt_sec*1000;
                    for (i=0;i<sum;i++)
                        if(usr_ans[i].equals(""))
                            usr_ans[i]="E";
                    //时间到时实现跳转逻辑到计分界面
                    String qid = StringUtils.join(all_qid,",",0,sum);
                    String all = StringUtils.join(all_ans,",",0,sum);
                    String usr = StringUtils.join(usr_ans,",",0,sum);
                    //System.out.println("all_ans:"+all);
                    //System.out.println("usr_ans:"+usr);
                    intent.putExtra("qid", qid);
                    intent.putExtra("all", all);
                    intent.putExtra("usr", usr);
                    intent.putExtra("study_time", String.valueOf(time_msec));
                    intent.putExtra("sum", String.valueOf(sum));
                    startActivity(intent);
                    Toast.makeText(Practice2Activity.this, "Time's up!", Toast.LENGTH_SHORT).show();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://10.250.106.45:8080/AndroidTest/mustPractice?mode="+ mode_str +"&qtime=" + qtime + "&subject=" + java.net.URLEncoder.encode(subject) + "&qschool=" + java.net.URLEncoder.encode(qschool) +"&qtype=" + java.net.URLEncoder.encode(qtype)+"&qnum="+qnum;
                try {
                    try {
                        URL url = new URL(path); //新建url并实例化
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");//获取服务器数据
                        connection.setReadTimeout(8000);//设置读取超时的毫秒数
                        connection.setConnectTimeout(8000);//设置连接超时的毫秒数
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        starttime = currentSecond;
                        final Button prev = (Button) findViewById(R.id.prev);
                        for(i=0;i<50;i++)all_ctnt[i]="";
                        for(i=0;i<50;i++)usr_ans[i]="";
                        for(i=0;i<50;i++)all_ans[i]="";
                        for(i=0;i<50;i++)isAnlz[i]=0;
                        for(i=0;i<50;i++)isMulti[i]=0;
                        while((result = reader.readLine())!=null)
                        {
                            System.out.println("!!!!!!!!"+result);
                            if(result.equals("qid"))
                            {
                                flag = 2;
                                continue;
                            }
                            else if(result.equals("qid_a"))//是分析题
                            {
                                flag = 2;
                                isAnlz[sum+1] = 1;//标记它为分析题
                                continue;
                            }
                            else if(result.equals("qid_m"))
                            {
                                flag = 2;
                                isMulti[sum+1] = 1;//标记它为多选题
                                continue;
                            }
                            else if(result.equals("content")) {
                                flag = 1;
                                continue;
                            }
                            else if(result.equals("ans"))
                            {
                                flag = 0;
                                continue;
                            }
                            else if(result.equals("endall")) {
                                sum++;
                                break;
                            }

                            if(flag == 2)
                            {
                                sum++;
                                all_qid[sum] = result;
                            }
                            else if(flag == 1)
                            {
                                all_ctnt[sum] += result+"\n";
                            }
                            else
                            {
                                if(isAnlz[sum] == 1)
                                    all_ans[sum] += result+"\n";
                                else
                                    all_ans[sum] += result;
                            }
                        }
                        //初始化页面控件
                        question.setText(all_ctnt[0]);
                        //初始化题目
                        //如果是多选题
                        if(isMulti[0] == 1)
                        {
                            handler.post(runnableUi1);
                        }
                        else if(isAnlz[0] == 1)        //如果是分析题
                        {
                            handler.post(runnableUi2);
                        }
                        else     //如果是单选题
                        {
                            handler.post(runnableUi0);
                        }
                        prev.setVisibility(View.INVISIBLE);
                        progress_str = "";
                        progress_str2 = "";
                        for(i=0;i<sum;i++)
                        {
                            progress_str2+=String.valueOf(i+1)+" ";
                        }
                        progressok_q.setText(progress_str);
                        progressnot_q.setText(progress_str2);
                    } catch (MalformedURLException e) {
                        System.out.println("Error exists!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.draw);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                String tmp_min = String.valueOf(time_res/60);
                String tmp_sec = String.valueOf(time_res%60);
                TextView time = (TextView) findViewById(R.id.time);
                String time_str = "  "+tmp_min+"      "+tmp_sec;
                time.setText(time_str);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        //监听单选题
        multiple.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch(checkedId){
                    case R.id.selectA:
                        Toast.makeText(Practice2Activity.this, "A", Toast.LENGTH_SHORT).show();
                        ans = "A";
                        break;
                    case R.id.selectB:
                        Toast.makeText(Practice2Activity.this, "B", Toast.LENGTH_SHORT).show();
                        ans = "B";
                        break;
                    case R.id.selectC:
                        Toast.makeText(Practice2Activity.this, "C", Toast.LENGTH_SHORT).show();
                        ans = "C";
                        break;
                    case R.id.selectD:
                        Toast.makeText(Practice2Activity.this, "D", Toast.LENGTH_SHORT).show();
                        ans = "D";
                        break;
                }
            }
        });

        //监听多选框
        CheckA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked)
                    select[0] = 1;
                else
                    select[0] = 0;

            }
        });
        CheckB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked)
                    select[1] = 1;
                else
                    select[1] = 0;
            }
        });
        CheckC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked)
                    select[2] = 1;
                else
                    select[2] = 0;
            }
        });
        CheckD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked)
                    select[3] = 1;
                else
                    select[3] = 0;
            }
        });






        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Button next = (Button) findViewById(R.id.next);
                final Button prev = (Button) findViewById(R.id.prev);
                progress_str = "";
                progress_str2 = "";
                for(i=0;i<sum;i++)
                {
                    if(usr_ans[i].equals(""))
                        progress_str2+=String.valueOf(i+1)+" ";
                    else
                        progress_str+=String.valueOf(i+1)+" ";
                }
                progressok_q.setText(progress_str);
                progressnot_q.setText(progress_str2);

                if(isMulti[cnt] == 1)
                {
                    handler.post(runnableUi1);
                }
                else if(isAnlz[cnt] == 1)        //如果是分析题
                {
                    handler.post(runnableUi2);
                }
                else     //如果是单选题
                {
                    handler.post(runnableUi0);
                }
                if(isMulti[cnt] == 1)
                {
                    ans = "";
                    if(select[0] == 1)
                        ans += "A";
                    if(select[1] == 1)
                        ans += "B";
                    if(select[2] == 1)
                        ans += "C";
                    if(select[3] == 1)
                        ans += "D";
                }
                else if(isAnlz[cnt] == 1)
                {
                    ans = answer.getText().toString();
                    answer.setText("");
                }
                usr_ans[cnt] = ans;

                cnt++;
                //!!!!!!!!!!!!!!!!!用户无法看到之前选择的答案，但是可以覆盖
                /*if(usr_ans[cnt].equals(""))
                    ans.setText("");
                else
                    ans.setText(usr_ans[cnt]);*/
                prev.setVisibility(View.VISIBLE);
                if(cnt == sum)
                {
                    isPause = true;
                    endtime = currentSecond;
                    time_msec = endtime-starttime;
                    for (i=0;i<sum;i++)
                        if(usr_ans[i].equals(""))//!!!!!!!!!!!!!!如何判断单选和多选是否有内容
                            usr_ans[i]="E";
                    //当读到最后一题时实现跳转逻辑到计分界面
                    String qid = StringUtils.join(all_qid,",",0,sum);
                    String all = StringUtils.join(all_ans,",",0,sum);
                    String usr = StringUtils.join(usr_ans,",",0,sum);
                    System.out.println("all_ans:"+all);
                    System.out.println("usr_ans:"+usr);
                    Intent intent = new Intent(Practice2Activity.this,Practice3Activity.class);
                    intent.putExtra("qid", qid);
                    intent.putExtra("all", all);
                    intent.putExtra("usr", usr);
                    intent.putExtra("study_time", String.valueOf(time_msec));
                    intent.putExtra("sum", String.valueOf(sum));
                    System.out.println("cnt:"+cnt);
                    startActivity(intent);
                }
                else
                {
                    if(cnt+1 == sum)
                        next.setText("提交");
                    question.setText(all_ctnt[cnt]);
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Button prev = (Button) findViewById(R.id.prev);
                progress_str = "";
                progress_str2 = "";
                for(i=0;i<sum;i++)
                {
                    if(usr_ans[i].equals(""))
                        progress_str2+=String.valueOf(i+1)+" ";
                    else
                        progress_str+=String.valueOf(i+1)+" ";
                }
                progressok_q.setText(progress_str);
                progressnot_q.setText(progress_str2);

                if(isMulti[cnt] == 1)
                {
                    handler.post(runnableUi1);
                }
                else if(isAnlz[cnt] == 1)        //如果是分析题
                {
                    handler.post(runnableUi2);
                }
                else     //如果是单选题
                {
                    handler.post(runnableUi0);
                }


                if(isMulti[cnt] == 1)
                {
                    ans = "";
                    if(select[0] == 1)
                        ans += "A";
                    if(select[1] == 1)
                        ans += "B";
                    if(select[2] == 1)
                        ans += "C";
                    if(select[3] == 1)
                        ans += "D";
                }
                else if(isAnlz[cnt] == 1)
                {
                    ans = answer.getText().toString();
                    answer.setText("");
                }
                usr_ans[cnt] = ans;
                cnt--;
                System.out.println("usr_ans:"+usr_ans[cnt]);
                System.out.println("cnt:"+cnt);
                if(cnt == 0)
                {
                    prev.setVisibility(View.INVISIBLE);
                }
                else
                {
                    prev.setVisibility(View.VISIBLE);
                }
                question.setText(all_ctnt[cnt]);
                next.setText("下一题");
            }
        });

    }

}
