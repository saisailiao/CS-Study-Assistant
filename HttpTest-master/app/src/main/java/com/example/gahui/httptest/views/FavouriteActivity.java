package com.example.gahui.httptest.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gahui.httptest.Favourite_eachActivity;
import com.example.gahui.httptest.R;

public class FavouriteActivity extends AppCompatActivity {
    public static int flag;
    public static String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        System.out.println("!!!!!!!!!!!");
        final EditText keyword = (EditText) findViewById(R.id.keyword);
        final TextView show = (TextView) findViewById(R.id.text5);
        show.setText(Favourite_eachActivity.showText1);
        Favourite_eachActivity.showText1 = "";
        ImageButton button = (ImageButton) findViewById(R.id.wrong_button);        //按下返回前一页
        Button button2 = (Button) findViewById(R.id.click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavouriteActivity.this, Favourite_eachActivity.class);
                flag = 3;//回到上一页活动
                startActivity(intent);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key = keyword.getText().toString().trim();
                System.out.println("FAVOUTITE!!!!!!!!"+key);
                Intent intent = new Intent(FavouriteActivity.this, Favourite_eachActivity.class);
                flag = 2;//回到上一页活动
                startActivity(intent);
                finish();
            }
        });

        ImageButton button1 = (ImageButton) findViewById(R.id.good_button);        //按下返回前一页
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavouriteActivity.this, Favourite_eachActivity.class);
                flag = 1;//回到上一页活动
                startActivity(intent);
                finish();
            }
        });
    }
}
