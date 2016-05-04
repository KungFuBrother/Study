package com.smartown.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.smartown.study.R;
import com.smartown.study.ScreenUtil;
import com.smartown.study.label.LabelView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LabelView labelView;
    private List<ModelLabel> labels = new ArrayList<>();
//    private String[] labels = {"滚动初探", "触摸控制滚动及滚动翻页", "标签控件LabelView"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtil.init(this);
        init();
        setContentView(R.layout.activity_main);
        labelView = (LabelView) findViewById(R.id.main_label);
        labelView.setOnItemClickListener(new LabelView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, labels.get(position).getaClass());
                intent.putExtra("title", labels.get(position).getLabel());
                startActivity(intent);
            }
        });
        for (int i = 0; i < labels.size(); i++) {
            labelView.addLabel(labels.get(i).getLabel());
        }
    }

    private void init() {
        labels.add(new ModelLabel(ScrollActivity.class, "Scroller滚动初探"));
        labels.add(new ModelLabel(ProductDetailActivity.class, "onTouch触摸控制"));
        labels.add(new ModelLabel(LabelActivity.class, "LabelView标签控件"));
    }

    private class ModelLabel {

        String label = "";
        Class aClass;

        public ModelLabel(Class aClass, String label) {
            this.aClass = aClass;
            this.label = label;
        }

        public Class getaClass() {
            return aClass;
        }

        public String getLabel() {
            return label;
        }

    }

}
