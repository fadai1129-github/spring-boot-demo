package com.example.springbootdemo.entity;


import java.io.Serializable;

//create table role(
//id varchar(32) PRIMARY KEY,
//name varchar(200)
//);
public class Role implements Serializable {

    private static final long serialVersionUID = -5602617088694209565L;
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
