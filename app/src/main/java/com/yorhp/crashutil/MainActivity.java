package com.yorhp.crashutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv_hello, tv_hello2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_hello = (TextView) findViewById(R.id.tv_hello);
        tv_hello2 = (TextView) findViewById(R.id.tv_hello2);


        tv_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "测试错误", Toast.LENGTH_SHORT).show();
                throw new RuntimeException("测试错误");
            }
        });

        tv_hello2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "测试错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
