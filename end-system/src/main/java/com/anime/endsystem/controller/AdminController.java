package com.anime.endsystem.controller;

import com.anime.endsystem.exception.NoAuthException;
import com.anime.endsystem.message.AdminResult;
import com.anime.endsystem.message.ResultBean;
import com.anime.endsystem.model.Admin;
import com.anime.endsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
