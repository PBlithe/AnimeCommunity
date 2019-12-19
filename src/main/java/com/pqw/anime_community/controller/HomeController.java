package com.pqw.anime_community.controller;

import com.pqw.anime_community.entity.User;
import com.pqw.anime_community.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping({"/", "/index", "/home"})
    public String root(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(User user){
        // 此处省略校验逻辑
        userService.insert(user);
        return "user";
    }
    @GetMapping("/user")
    public String User(){
        return "user";
    }

}
