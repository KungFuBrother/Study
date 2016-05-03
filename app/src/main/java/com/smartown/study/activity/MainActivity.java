package com.smartown.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.smartown.study.R;
import com.smartown.study.ScreenUtil;
import com.smartown.study.scroll.LauncherView;
import com.smartown.study.scroll.LauncherView1;
import com.smartown.study.scroll.LauncherView2;

public class MainActivity extends AppCompatActivity {

    private Button scrollButton;
    private Button touchButton;
    private Button labelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtil.init(this);
        setContentView(R.layout.activity_main);
        scrollButton = (Button) findViewById(R.id.main_scroll);
        touchButton = (Button) findViewById(R.id.main_touch);
        labelButton = (Button) findViewById(R.id.main_label);
        scrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ScrollActivity.class));
            }
        });
        touchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProductDetailActivity.class));
            }
        });
        labelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LabelActivity.class));
            }
        });
    }
}
