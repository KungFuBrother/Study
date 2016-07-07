package com.smartown.study.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smartown.study.R;
import com.smartown.study.commonAdapter.City;
import com.smartown.study.commonAdapter.CommonAdapter;
import com.smartown.study.commonAdapter.User;
import com.smartown.study.commonAdapter.ValueGetter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiger on 2016-07-07.
 */
public class CommonAdapterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        setContentView(R.layout.activity_common_adapter);
        recyclerView = (RecyclerView) findViewById(R.id.common_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(CommonAdapterActivity.this));
        initUser();
        initCity();
    }

    private void initUser() {
        List<User> users = new ArrayList<>();
        users.add(new User("12", "张三", "男"));
        users.add(new User("18", "翠花", "女"));
        users.add(new User("22", "刘德华", "男"));
        users.add(new User("16", "赵四", "男"));
        users.add(new User("24", "Angelababy", "女"));
        CommonAdapter<User> commonAdapter = new CommonAdapter<>(CommonAdapterActivity.this, users);
        commonAdapter.setValueGetter(new ValueGetter<User>() {
            @Override
            public String getName(User user) {
                return user.getName();
            }
        });
        recyclerView.setAdapter(commonAdapter);
    }

    private void initCity() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("成都", "四川"));
        cities.add(new City("绵阳", "四川"));
        cities.add(new City("达州", "四川"));
        cities.add(new City("南充", "四川"));
        cities.add(new City("资阳", "四川"));
        cities.add(new City("遂宁", "四川"));
        cities.add(new City("眉山", "四川"));
        cities.add(new City("乐山", "四川"));
        CommonAdapter<City> commonAdapter = new CommonAdapter<>(CommonAdapterActivity.this, cities);
        commonAdapter.setValueGetter(new ValueGetter<City>() {
            @Override
            public String getName(City city) {
                return city.getProvince() + " " + city.getName();
            }
        });
        recyclerView.setAdapter(commonAdapter);
    }

}
