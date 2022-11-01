package com.example.gahui.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gahui.httptest.R;
import com.example.gahui.httptest.views.FavouriteActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PracticeMstActivity extends AppCompatActivity {
    private String[] data = {"Sunny","Cloudy","Few Clouds","Partly Cloudy","Overcast","Windy","Calm","Light Breeze",
            "Moderate","Fresh Breeze","Strong Breeze","High Wind","Gale","Strong Gale","Storm","Violent Storm","Hurricane",
            "Tornado","Tropical Storm","Shower Rain","Heavy Shower Rain","Thundershower","Heavy Thunderstorm","Thundershower with hail",
            "Light Rain"};
    private String[] data2= {"Moderate Rain","Heavy Rain","Extreme Rain","Drizzle Rain","Storm","Heavy Storm","Severe Storm","Freezing Rain",
            "Light to moderate rain","Moderate to heavy rain","Heavy rain to storm","Storm to heavy storm","Heavy to severe storm",
            "Rain","Light Snow","Moderate Snow","Heavy Snow","Snowstorm","Sleet","Rain And Snow","Shower Snow","Snow Flurry",
            "Light to moderate snow","Moderate to heavy snow","Heavy snow to snowstorm","Snow","Mist","Foggy","Haze","Sand","Dust",
            "Duststorm","Sandstorm","Dense fog","Strong fog","Moderate haze","Heavy haze","Severe haze","Heavy fog","Extra heavy fog",
            "Hot","Cold","Unknown"};
    public static int flag1 = 0;
    public String result="111";
    public int question = 1;
    public String strArr[][] = new String[20][6];
    private ArrayList str1 =  new ArrayList<String>();
    private ArrayList str =  new ArrayList<String>();
    private ArrayList str2 =  new ArrayList<String>();
    public String[] qid = new String [5];
    public String ques = "";
    public String ans = "";
    public int flag = 0;
    private TextView textView1=null;
    private TextView textView2=null;
    private TextView textView3=null;
    private String type ;
    public int [] mistakes_int = new int[50];
    public int sum_anlz=0;
    public int [] id_anlz = new int[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_mst);
        TextView button4 = (TextView) findViewById(R.id.index1);
        //通过泛型来指定要适配的数据类型，然后在构造函数中把适配的数据传入。
        //android.R.layout.simple_list_item_1是ListView内置的一个子项布局，里面只有一个TextView，可显示一段文本
        //data表示要适配的数据
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PracticeMstActivity.this,R.layout.list_item , str1);
        ListView listView = (ListView)findViewById(R.id.list_view);
        //将构建好的适配器对象传进去
        listView.setAdapter(adapter);
        //设置监听
        //为ListView添加点击事件
        int i;
        for (i=0;i<Practice3Activity.mst;i++) {
            mistakes_int[i] = Integer.valueOf(Practice3Activity.mistakes[i]) - 1;
            str1.add(Practice2Activity.all_ctnt[mistakes_int[i]]);
            str.add(Practice2Activity.all_ans[mistakes_int[i]]);
            str2.add(Practice2Activity.usr_ans[mistakes_int[i]]);
        }
        for (i=0;i<50;i++)
            if(Practice2Activity.isAnlz[i] == 1) {
                id_anlz[sum_anlz]=i;
                sum_anlz++;
                str1.add(Practice2Activity.all_ctnt[id_anlz[i]]);
                str.add(Practice2Activity.all_ans[id_anlz[i]]);
                str2.add(Practice2Activity.usr_ans[id_anlz[i]]);
            }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String showText = "点击第" + position + "项" + "，ID为：" + id;
                System.out.println(showText);
                Toast.makeText(PracticeMstActivity.this,showText, Toast.LENGTH_SHORT).show();
                flag1 = 0;
                Intent intent = new Intent(PracticeMstActivity.this, PracticeMst_eachActivity.class);
                intent.putExtra("str",(String)str.get(position));
                intent.putExtra("str1",(String) str1.get(position));//显示题目详情
                intent.putExtra("str2", (String)str2.get(position));//显示题目详情
                startActivity(intent);
                finish();
            }
        });

        Button button3 = (Button) findViewById(R.id.next);        //下一页，刷新数据
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView)findViewById(R.id.page);
                text.setText("2");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PracticeMstActivity.this,R.layout.list_item , str1);
                ListView listView = (ListView)findViewById(R.id.list_view);
                listView.setAdapter(adapter);
            }
        });

        Button button1 = (Button) findViewById(R.id.back);        //上一页，刷新数据
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView)findViewById(R.id.page);
                text.setText("1");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PracticeMstActivity.this,R.layout.list_item , str1);
                ListView listView = (ListView)findViewById(R.id.list_view);
                listView.setAdapter(adapter);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticeMstActivity.this, IndexActivity.class);//回到上一页活动
                Practice3Activity.mst = 0;
                startActivity(intent);
            }
        });




    }

}
