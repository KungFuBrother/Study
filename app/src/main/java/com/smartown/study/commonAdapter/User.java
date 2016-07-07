package com.smartown.study.commonAdapter;

/**
 * Created by Tiger on 2016-07-07.
 */
public class User {

    private String name = "";
    private String sex = "";
    private String age = "";

    public User(String age, String name, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
