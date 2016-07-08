package com.smartown.study.commonAdapter;

/**
 * Created by Tiger on 2016-07-07.
 */
public class City {

    private String province = "";
    private String name = "";

    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }
}
