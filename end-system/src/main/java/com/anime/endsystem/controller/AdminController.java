package com.anime.endsystem.controller;

import com.anime.endsystem.exception.NoAuthException;
import com.anime.endsystem.message.AdminResult;
import com.anime.endsystem.message.MAData;
import com.anime.endsystem.message.MAResult;
import com.anime.endsystem.message.ResultBean;
import com.anime.endsystem.model.Admin;
import com.anime.endsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

@RestController
public class AdminController {
    private final String ON = "on";

    @Autowired
    public  AdminService adminService;

    //接收前端输入的数据
    @PostMapping(value="/createAdmin")
    public ResultBean createAdmin(@RequestBody Admin admin, HttpSession session){
        //添加管理员表的信息
        String optor = (String)session.getAttribute("optor");
        admin.setOptor(optor);
        try{
            adminService.createAdmin(admin);
        }catch (DataAccessException e){
            e.printStackTrace();
            return new ResultBean().error(e,"创建失败，可能是账号重复");
        }catch(NoAuthException e){
            return new ResultBean().error(e,e.getMessage());
        }
        return new ResultBean().success();
    }
    @GetMapping(value = "/createAccountLogsPage/accountLogs")
    public AdminResult searchAccountLogs(int page,int limit){
        AdminResult logs = null;
        try {
            logs = adminService.searchAccountLogs(page,limit);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return logs;
    }
    @GetMapping("/account")
    public MAResult account(int page,int limit,HttpSession session){
        MAResult result = new MAResult();
        String id = (String) session.getAttribute("optor");
        result = adminService.returnMList(page,limit,id);//返回修改列表
        return result;
    }
    @PostMapping("tryDeleteAccount")
    public ResultBean tryDeleteAccount(MAData data,HttpSession session){
        //首先判断是否有权限删除，如果没有则返回没有权限删除
        //删除了管理员以后要在记录表中添加数据。
        String id = (String)session.getAttribute("optor");
        System.out.println(data.getState());
        return adminService.tryDeleteAccount(data,id);
    }
    @GetMapping("/tryBlock")
    public ResultBean tryBlock(MAData data,HttpSession session){
        //首先判断是否有权限修改（封锁也在修改的范围内）,如果没有则返回没有权限修改账号的状态
        //如果有则把账号的状态封锁，并把词条操作加入到管理员创建记录表中
        String id = (String) session.getAttribute("optor");
        return adminService.tryBlock(data,id);
    }
    @PostMapping("/tryEdit")
    public ResultBean tryEdit(MAData data,String newAuth, HttpSession session){
        String id = (String) session.getAttribute("optor");
        return adminService.tryEdit(data,newAuth,id);
    }
}
