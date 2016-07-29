package com.smartown.study.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.smartown.study.R;
import com.smartown.study.refresh.InnerScrollView;
import com.smartown.study.refresh.RefreshView;

public class RefreshActivity extends AppCompatActivity {

    private RefreshView refreshView;
    private InnerScrollView innerScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        setContentView(R.layout.activity_refresh);
        innerScrollView = (InnerScrollView) LayoutInflater.from(this).inflate(R.layout.activity_refresh_scroll, null);
        refreshView = (RefreshView) findViewById(R.id.refresh);
        refreshView.setContentView(innerScrollView);
    }
}
