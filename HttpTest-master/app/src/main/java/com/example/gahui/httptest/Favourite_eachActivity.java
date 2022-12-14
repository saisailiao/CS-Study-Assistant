package com.example.gahui.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class Favourite_eachActivity extends AppCompatActivity {
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
    public static int flag2= 0;
    public static  String showText1 = "";
    public String result="111";
    public int question = 1;
    public String strArr[][] = new String[20][6];
    private ArrayList str1 =  new ArrayList<String>();
    private ArrayList str =  new ArrayList<String>();
    public String[] qid = new String [100];
    public String ques = "";
    public String ans = "";
    public int flag = 0;
    private TextView textView1=null;
    private TextView textView2=null;
    private TextView textView3=null;
    private String type ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_each);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = null;
                try {
                    try {
                        if(FavouriteActivity.flag == 1)
                             path = "http://10.250.106.45:8080/AndroidTest/mustShowquestion?uid=" + MainActivity.uid +"&btype=1";
                        else if(FavouriteActivity.flag == 2)
                            path = "http://10.250.106.45:8080/AndroidTest/mustFavourtite?uid=" + MainActivity.uid +"&key="+java.net.URLEncoder.encode(FavouriteActivity.key);
                        else if(FavouriteActivity.flag == 3)
                            path = "http://10.250.106.45:8080/AndroidTest/mustShowquestion?uid=" + MainActivity.uid +"&btype=0";
                        System.out.println(path);
                        //System.out.println(java.net.URLEncoder.encode(FavouriteActivity.key));
                        URL url = new URL(path); //??????url????????????
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");//?????????????????????
                        connection.setReadTimeout(8000);//??????????????????????????????
                        connection.setConnectTimeout(8000);//??????????????????????????????
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        //String result = "";
                        int i = 0;
                        // StringBuilder result = new StringBuilder();  //StringBuilder??????????????????null
                        flag = 0;
                        i = 0;
                        ans = "";
                        ques = "";
                        //System.out.println("get herrrrrrrr");
                        while ((result = reader.readLine()) != null) {//??????????????????
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
                                str.add(ans);
                                str1.add(ques);
                                i = i + 1;
                                ans = "";
                                ques = "";
                                System.out.println("get resssssssssssssss   "+i);
                                //result = reader.readLine();
                            }
                            else if(flag == 1){
                                ans = ans  + result+'\n';
                                //System.out.println("get herrrrrrrrrrrr  "+ans);
                            }
                            else{
                                if(!result.equals('\n'))
                                     ques = ques  + result+'\n';
                            }
                            System.out.println(result);
                            if(result.equals("not get content!")){
                                 showText1 = "???????????????";
                                  flag2 = 1;
                                System.out.println("!!!!!!!!!!!"+result);
                                Intent intent = new Intent(Favourite_eachActivity.this, FavouriteActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            // System.out.println("get herrrrrrrrrrrr   "+ques);
                        }
                        if(flag2 == 0){
                            ans = (String)str.get(0);
                            ques = (String)str1.get(0);
                        }
                        //System.out.println("aaaaaa"+ques);
                        // String result = reader.readLine();//?????????????????????????????????????????????????????????
                    } catch (MalformedURLException e) {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //???????????????????????????????????????????????????????????????????????????????????????????????????
        //android.R.layout.simple_list_item_1???ListView????????????????????????????????????????????????TextView????????????????????????
        //data????????????????????????
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Favourite_eachActivity.this,R.layout.list_item , str1);
        ListView listView = (ListView)findViewById(R.id.list_view);
        //???????????????????????????????????????
        listView.setAdapter(adapter);
        //????????????
        //???ListView??????????????????
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String showText = "?????????" + position + "???" + "???ID??????" + id;
                System.out.println(showText);
                Toast.makeText(Favourite_eachActivity.this,showText, Toast.LENGTH_SHORT).show();
                flag1 = 0;
                Intent intent = new Intent(Favourite_eachActivity.this, DetailsActivity.class);
                intent.putExtra("str",(String) str.get(position));
                intent.putExtra("str1", (String) str1.get(position));//??????????????????
                intent.putExtra("qid", qid[position]);//??????????????????
                intent.putExtra("flag", "true");
                startActivity(intent);
                finish();
            }
        });

        Button button3 = (Button) findViewById(R.id.next);        //????????????????????????
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView)findViewById(R.id.page);
                text.setText("2");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Favourite_eachActivity.this,R.layout.list_item , str1);
                ListView listView = (ListView)findViewById(R.id.list_view);
                listView.setAdapter(adapter);
            }
        });

        Button button1 = (Button) findViewById(R.id.back);        //????????????????????????
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView)findViewById(R.id.page);
                text.setText("1");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Favourite_eachActivity.this,R.layout.list_item , str1);
                ListView listView = (ListView)findViewById(R.id.list_view);
                listView.setAdapter(adapter);
            }
        });

    }

}
