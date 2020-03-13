package com.anime.endsystem.controller;

import com.anime.endsystem.message.ResultBean;
import com.anime.endsystem.service.AuthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AuthCheckController {

    @Autowired
    public AuthCheckService authCheckService;

    @GetMapping("/createAccountLogsAJAX")
    public ResultBean createAccountLogsAJAX(HttpSession session){
        String id = (String) session.getAttribute("optor");
        return authCheckService.createAccountLogsAJAX(id);
    }
    @GetMapping("/selectUserInfoAJAX")
    public ResultBean selectUserInfoAJAX(HttpSession session){
        String id = (String) session.getAttribute("optor");
        return authCheckService.selectUserInfoAJAX(id);
    }
    @GetMapping("/manageUserAJAX")
    public ResultBean manageUserAJAX(HttpSession session){
        String id = (String) session.getAttribute("optor");
        return authCheckService.manageUserAJAX(id);
    }
}
