package com.anime.endsystem.model;

import com.anime.endsystem.enums.AdminState;

import java.io.Serializable;
import java.util.Date;

// 数据持久化 对应数据表格 t_admin
public class Admin implements Serializable {
    private int no;//序号
    private String id;//账号id
    private String password;//密码
    private Date time;//创建时间
    private String auth;//权限
    private String optor;//操作人id
    private AdminState state;//状态：NORMAL正常,BLOCK封锁

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getOptor() {
        return optor;
    }

    public void setOptor(String optor) {
        this.optor = optor;
    }

    public AdminState getState() {
        return state;
    }

    public void setState(AdminState state) {
        this.state = state;
    }
}
