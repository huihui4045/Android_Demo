package com.alizhezi.demo;

/**
 * Created by gavin
 * Time 2017/12/18  18:02
 * Email:molu_clown@163.com
 */

public class UserBean {

    String name;

    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
