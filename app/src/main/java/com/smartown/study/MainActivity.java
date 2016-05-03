package com.smartown.study;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    LauncherView launcherView;
    LauncherView1 launcherView1;
    LauncherView2 launcherView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtil.init(this);
        setContentView(R.layout.activity_main);
        launcherView = (LauncherView) findViewById(R.id.view);
        launcherView1 = (LauncherView1) findViewById(R.id.view1);
        launcherView2 = (LauncherView2) findViewById(R.id.view2);
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
                System.out.println(launcherView1.getHeight() + "  " + launcherView1.getMeasuredHeight());
            }
        });
    }
}
