package com.anime.endsystem.controller;

import com.anime.endsystem.message.ResultBean;
import com.anime.endsystem.message.UserInfo;
import com.anime.endsystem.message.UserInfoResult;
import com.anime.endsystem.message.UserLogsResult;
import com.anime.endsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public UserInfoResult userInfo(String nickname,String uid,String tel,int page,int limit){
        page = (page-1)*10;
        if(nickname == null||(nickname.isEmpty()&&uid.isEmpty()&&tel.isEmpty())){
            return userService.userInfo(page,limit);
        }

        if(!uid.isEmpty()){
            //按照昵称查询
            return userService.userInfoUID(uid,page,limit);
        }else if(!nickname.isEmpty()){
            //按照uid查询
            return userService.userInfoNickname(nickname,page,limit);
        }else if(!tel.isEmpty()){
            //按照手机号查询
            return userService.userInfoTel(tel,page,limit);
        }else{
        }
        return new UserInfoResult();
    }

    @GetMapping("/tryBlockUser")
    public ResultBean tryBlock(UserInfo data, HttpSession session){
        //首先判断是否有处理违规的权限,如果没有则返回没有处理违规的权限
        //如果有则把账号的状态封锁，并把词条操作加入到管理员创建记录表中
        String id = (String) session.getAttribute("optor");
        return userService.tryBlock(data,id);
    }
    @GetMapping("/trydisBlockUser")
    public ResultBean trydisBlock(UserInfo data, HttpSession session){
        //首先判断用户是否有权限修改账号状态
        //如果有才能修改状态
        //最后还要在记录表中添加记录
        String id = (String) session.getAttribute("optor");
        return userService.trydisBlock(data,id);
    }
    @GetMapping("/userLogs")
    public UserLogsResult userLogs(String uid,String time,int page,int limit){
        page = (page - 1)*limit;
        if((uid == null&&time == null)||uid.isEmpty()&&time.isEmpty()){
            return userService.userLogs(page,limit);
        }
        if(!uid.isEmpty()){
            return userService.userLogsUid(uid,page,limit);
        }else if(!time.isEmpty()){
            return userService.userLogsTime(time,page,limit);
        }else{}
        return new UserLogsResult();
    }
}
