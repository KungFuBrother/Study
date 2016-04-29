package com.smartown.study;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    LauncherView launcherView;
    LauncherView1 launcherView1;
    JellyTextView jellyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtil.init(this);
        setContentView(R.layout.activity_main);
        launcherView = (LauncherView) findViewById(R.id.view);
        launcherView1 = (LauncherView1) findViewById(R.id.view1);
        jellyTextView = (JellyTextView) findViewById(R.id.view3);
        launcherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcherView.clickToScroll();
            }
        });
        launcherView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcherView1.clickToScroll();
            }
        });
        jellyTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return jellyTextView.onTouchEvent(event);
            }
        });

    }
}
