package com.smartown.study.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.smartown.study.R;

public class ImageActivity extends AppCompatActivity {

    private ImageView imageView1;
    private ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        setContentView(R.layout.activity_image);
        imageView1 = (ImageView) findViewById(R.id.image1);
        imageView2 = (ImageView) findViewById(R.id.image2);
//        Glide.with(this).load("http://img.taopic.com/uploads/allimg/121209/234928-12120Z0543764.jpg").into(imageView1);
        ImageLoader.getInstance().displayImage("http://img.taopic.com/uploads/allimg/121209/234928-12120Z0543764.jpg", imageView2);
    }

}
