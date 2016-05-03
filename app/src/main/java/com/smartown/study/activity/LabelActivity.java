package com.smartown.study.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.smartown.study.R;
import com.smartown.study.ScreenUtil;
import com.smartown.study.label.LabelView;

public class LabelActivity extends AppCompatActivity {

    private LabelView labelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtil.init(this);
        setContentView(R.layout.activity_label);
        labelView = (LabelView) findViewById(R.id.label_label);
        labelView.addLabel("TEST");
        labelView.addLabel("TEST");
        labelView.addLabel("TEST");
        labelView.addLabel("TEST");
        labelView.addLabel("TEST");
        labelView.addLabel("TEST");
        labelView.addLabel("TEST");
        labelView.addLabel("TEST");
        labelView.addLabel("TEST");
        labelView.addLabel("TEST");
    }
}
