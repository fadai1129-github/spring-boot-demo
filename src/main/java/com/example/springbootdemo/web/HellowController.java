package com.example.springbootdemo.web;

import com.example.springbootdemo.utils.I18nUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Controller
@ResponseBody
@RequestMapping("/demo")
@Validated
public class HellowController {

    @RequestMapping("hello")
    public String hello(HttpSession session) {
        session.setAttribute("username",null);
        String test = I18nUtil.getMessage("test");
        return "Hello World!" + "<br/>" + test;
    }

    @RequestMapping("login")
    public String add(@NotBlank(message="username is null")
                    @RequestParam(value = "username")
                      String username,
                      @NotBlank(message="password is null")
//                      @RequestParam(value = "password")
                      String password, HttpSession session) {
        session.setAttribute("username",username);
        return username + "  login!";
    }


}
