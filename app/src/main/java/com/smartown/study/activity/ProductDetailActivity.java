package com.smartown.study.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.smartown.study.R;
import com.smartown.study.touch.BottomView;
import com.smartown.study.touch.ProductDetailView;
import com.smartown.study.touch.TopView;

/**
 * Created by Tiger on 2016-05-03.
 */
public class ProductDetailActivity extends AppCompatActivity {

    private ProductDetailView productDetailView;
    private TopView topView;
    private BottomView bottomView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        setContentView(R.layout.activity_product_detail);
        productDetailView = (ProductDetailView) findViewById(R.id.product_detail);
        topView = (TopView) LayoutInflater.from(this).inflate(R.layout.view_top, null);
        bottomView = (BottomView) LayoutInflater.from(this).inflate(R.layout.view_bottom, null);
        productDetailView.setTopView(topView);
        productDetailView.setBottomView(bottomView);
    }
}
