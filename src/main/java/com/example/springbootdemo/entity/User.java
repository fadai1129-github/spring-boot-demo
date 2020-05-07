package com.example.springbootdemo.entity;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


//create table user(
//id varchar(32) PRIMARY KEY,
//name varchar(200),
//password varchar(200),
//age int,
//roleId varchar(32));
public class User implements Serializable {


    private static final long serialVersionUID = 7601342592265762052L;

    private String id;
    private String name;
    private String password;
    private int age;
//    private String role_id;

    private Role role;

    //    每个实体属性添加
//　　@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    配置文件全局配置
//　　spring.mvc.date-format=yyyy-MM-dd HH:mm:ss
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;

    public User() {
    }

    public User(String id, String name, String password, int age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

//    public String getRole_id() {
//        return role_id;
//    }
//
//    public void setRole_id(String role_id) {
//        this.role_id = role_id;
//    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
