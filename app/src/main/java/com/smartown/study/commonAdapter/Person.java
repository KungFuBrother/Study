package com.smartown.study.commonAdapter;

/**
 * Created by Tiger on 2016-07-07.
 */
public class Person {

    private String name = "";
    private String sex = "";
    private String age = "";

    public Person(String age, String name, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

}
