package com.alizhezi.demo.dagger;

import android.util.Log;

import javax.inject.Inject;

public class Student {

    private String name;

    @Inject
    public Student() {

        name="cao cao";
    }

    public String getName() {
        return name;
    }

    public void action(){
        Log.e("SIVEN","demo action");
    }
}
