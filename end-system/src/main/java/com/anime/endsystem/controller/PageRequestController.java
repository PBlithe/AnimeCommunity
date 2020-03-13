package com.anime.endsystem.controller;

import com.anime.endsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * 这个Controller用来映射所有页面请求
 */
@Controller
public class PageRequestController {
    @Autowired
    private UserService userService;

    @GetMapping({"/createAdminPage","/"})
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
    @GetMapping("/selectUserInfoPage")
    public String selectUserInfoPage(HttpSession httpSession){
        httpSession.setAttribute("optor","admin");
        return "selectUserInfo";
    }
    @GetMapping("/manageUserPage")
    public String manageUserPage(HttpSession httpSession) {
        httpSession.setAttribute("optor","admin");
        return "manageUser";
    }
    @GetMapping("/followingPage")
    public String followingPage(String uid,HttpSession httpSession,Model model){
        httpSession.setAttribute("optor","admin");
        userService.following(uid,model);
        return "followingPage";
    }
    @GetMapping("/funsPage")
    public String funsPage(String uid, HttpSession httpSession, Model model){
        httpSession.setAttribute("optor","admin");
        userService.funs(uid,model);
        return "funsPage";
    }
    @GetMapping("/userLogsPage")
    public String funsPage(){
        return "userLogs";
    }
}
