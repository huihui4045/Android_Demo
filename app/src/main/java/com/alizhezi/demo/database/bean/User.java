package com.alizhezi.demo.database.bean;

import com.alizhezi.demo.database.annotation.DbField;
import com.alizhezi.demo.database.annotation.DbTable;

@DbTable("t_user")
public class User {
    @DbField("_id")
    private Integer id;

    private String name;

    private String password;

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
