package com.smartown.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.smartown.study.R;
import com.smartown.study.ScreenUtil;
import com.smartown.study.label.LabelView;
import com.smartown.study.label.ModelLabel;
import com.smartown.study.newFeature.BlurActivity;
import com.smartown.study.newFeature.FullScreenActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiger on 2016-07-15.
 */
public class NewFeatureActivity extends AppCompatActivity {

    private LabelView labelView;
    private List<ModelLabel> labels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        ScreenUtil.init(this);
        init();
        setContentView(R.layout.activity_main);
        labelView = (LabelView) findViewById(R.id.main_label);
        labelView.setOnItemClickListener(new LabelView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(NewFeatureActivity.this, labels.get(position).getaClass());
                intent.putExtra("title", labels.get(position).getLabel());
                startActivity(intent);
            }
        });
        for (int i = 0; i < labels.size(); i++) {
            labelView.addLabel(labels.get(i).getLabel());
        }
    }

    private void init() {
        labels.add(new ModelLabel(FullScreenActivity.class, "全屏activity"));
        labels.add(new ModelLabel(BlurActivity.class, "模糊效果"));
    }

}
