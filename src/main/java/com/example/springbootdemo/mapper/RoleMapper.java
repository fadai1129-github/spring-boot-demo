package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    Role getById(String id);

    List<Role> getAll();

    void add (Role role);
}
