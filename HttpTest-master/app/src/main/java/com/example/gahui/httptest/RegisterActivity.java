package com.example.gahui.httptest;

import android.content.Intent;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText nickname = (EditText) findViewById(R.id.nickname);
        final EditText Phone = (EditText) findViewById(R.id.Phone);
        final EditText pwd = (EditText) findViewById(R.id.pwd);
        final EditText pwd_confirm = (EditText) findViewById(R.id.pwd_confirm);
        final EditText mail = (EditText) findViewById(R.id.mail);
        Button continuing = (Button) findViewById(R.id.continuing);
        continuing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = nickname.getText().toString().trim();
                final String phone_num = Phone.getText().toString().trim();
                final String password = pwd.getText().toString().trim();
                final String password_confirm = pwd_confirm.getText().toString().trim();
                final String email = mail.getText().toString().trim();
                //传递些简单的参数
                Intent intent = new Intent(RegisterActivity.this,Register2Activity.class);
                intent.putExtra("name", name);
                intent.putExtra("phone_num", phone_num);
                intent.putExtra("password", password);
                intent.putExtra("password_confirm", password_confirm);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }
}
