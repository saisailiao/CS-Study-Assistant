package com.example.gahui.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticeAnlzActivity extends AppCompatActivity {

    public int i=0;
    public int sum_anlz=0;
    public int [] id_anlz = new int[50];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_anlz);
        TextView ctnt=(TextView)findViewById(R.id.ctnt);
        TextView usr=(TextView)findViewById(R.id.usr);
        TextView ans=(TextView)findViewById(R.id.ans);
        Button next = (Button) findViewById(R.id.next);
        Button prev = (Button) findViewById(R.id.prev);
        //初始化页面控件
        for (i=0;i<50;i++)
            if(Practice2Activity.isAnlz[i] == 1) {
                id_anlz[sum_anlz]=i;
                sum_anlz++;
            }
        prev.setText("退出");
        i = 0;
        ctnt.setText(Practice2Activity.all_ctnt[id_anlz[0]].substring(0,200));
        usr.setText("你的答案："+Practice2Activity.usr_ans[id_anlz[0]]);
        ans.setText("正确答案："+Practice2Activity.all_ans[id_anlz[0]].substring(0,50));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                System.out.println("next:"+i);
                prev.setText("上一题");
                if(i == sum_anlz)
                {
                    //跳到index界面
                    Intent intent = new Intent(PracticeAnlzActivity.this,IndexActivity.class);
                    startActivity(intent);
                }
                else
                {
                    if(i+1 == sum_anlz)
                        next.setText("退出");
                    else
                        next.setText("下一题");
                    ctnt.setText(Practice2Activity.all_ctnt[id_anlz[i]].substring(0,200));
                    usr.setText("你的答案："+Practice2Activity.usr_ans[id_anlz[i]]);
                    ans.setText("正确答案："+Practice2Activity.all_ans[id_anlz[i]].substring(0,50));
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i--;
                System.out.println("prev:"+i);
                next.setText("下一题");
                if(i < 0)
                {
                    //跳到index界面
                    Intent intent = new Intent(PracticeAnlzActivity.this,IndexActivity.class);
                    startActivity(intent);
                }
                else
                {
                    if(i == 0)
                        prev.setText("退出");
                    else
                        prev.setText("上一题");
                    ctnt.setText(Practice2Activity.all_ctnt[id_anlz[i]].substring(0,200));
                    usr.setText("你的答案："+Practice2Activity.usr_ans[id_anlz[i]]);
                    ans.setText("正确答案："+Practice2Activity.all_ans[id_anlz[i]].substring(0,50));
                }
            }
        });
    }
}
