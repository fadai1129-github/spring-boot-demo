package com.example.springbootdemo.web;

import com.example.springbootdemo.entity.Role;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.mapper.RoleMapper;
import com.example.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@ResponseBody
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    HashOperations<String, String, Role> opsHash;

    @GetMapping("user/users")
    public List<User> list() {
        List<User> all = userMapper.getAll();
        return all;
    }



    @RequestMapping("user/add")
    public User add(User user) {
        userMapper.add(user);
        return user;
    }

    @RequestMapping("user/all")
    public List<User> getAllUsers() {
        return userMapper.getAll();
    }
    @RequestMapping("user/get/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userMapper.getById(id);
    }
//
//    @RequestMapping("user/delete/{id}")
//    public User delete(@PathVariable("id") String id) {
//        return userMapper.getAll().get(2);
//    }



    @RequestMapping("role/add")
    public String addAll(Role role) {
        roleMapper.add(role);
        return role.getId();
    }

    @RequestMapping("role/all")
    public List<Role> getAllRoles() {
        return roleMapper.getAll();
    }

    @RequestMapping("role/get/{id}")
    public Role getRole(@PathVariable("id") String id) {
        Role role = null;
        if (redisTemplate.opsForHash().hasKey("user", id)){
            role = opsHash.get("user",id);
            System.out.println("!!!get from redis");
        } else {
            role = roleMapper.getById(id);
            redisTemplate.opsForHash().put("user", id, role);
            System.out.println("!!!get from db");
        }
        return role;
    }
}
