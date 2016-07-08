package com.smartown.study.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smartown.study.R;
import com.smartown.study.commonAdapter.City;
import com.smartown.study.commonAdapter.CommonAdapter;
import com.smartown.study.commonAdapter.Person;
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
//        initUser();
        initCity();
    }

    /**
     * 数据为人物
     */
    private void initUser() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("12", "张三", "男"));
        persons.add(new Person("18", "翠花", "女"));
        persons.add(new Person("22", "刘德华", "男"));
        persons.add(new Person("16", "赵四", "男"));
        persons.add(new Person("24", "Angelababy", "女"));
        CommonAdapter<Person> commonAdapter = new CommonAdapter<>(CommonAdapterActivity.this, persons);
        commonAdapter.setValueGetter(new ValueGetter<Person>() {
            @Override
            public String getValue(Person person) {
                //重写取值方法，返回人物描述
                return person.getName() + "/" + person.getSex() + "/今年" + person.getAge() + "岁";
            }
        });
        recyclerView.setAdapter(commonAdapter);
    }

    /**
     * 数据为城市
     */
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
            public String getValue(City city) {
                return city.getProvince() + " " + city.getName();
            }
        });
        recyclerView.setAdapter(commonAdapter);
    }

}
