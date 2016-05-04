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
        labelView.addLabel("空调");
        labelView.addLabel("电冰箱");
        labelView.addLabel("手机");
        labelView.addLabel("笔记本电脑");
        labelView.addLabel("超级本");
        labelView.addLabel("游戏笔记本");
        labelView.addLabel("I5处理器");
    }
}
