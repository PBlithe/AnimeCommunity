package com.anime.endsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * 这个Controller用来映射所有页面请求
 */
@Controller
public class PageRequestController {

    @GetMapping("/createAdminPage")
    public String createAccountPage(HttpSession httpSession){
        httpSession.setAttribute("optor","admin");
        return "createAdmin";
    }
    @GetMapping("/createAccountLogsPage")
    public String createAccountLogsPage(HttpSession httpSession){
        httpSession.setAttribute("optor","admin");
        return "createAccountLogs";
    }
    @GetMapping("/modifyAccountPage")
    public String modifyAccountPage(HttpSession httpSession){
        return "modifyAccount";
    }
}
