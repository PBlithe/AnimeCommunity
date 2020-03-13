package com.anime.endsystem.model;

import com.anime.endsystem.enums.UserOpt;

import java.util.Date;

public class UserLogs {
    private int no;
    private String uid;
    private Date time;
    private UserOpt opt;
    private String optor;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public UserOpt getOpt() {
        return opt;
    }

    public void setOpt(UserOpt opt) {
        this.opt = opt;
    }

    public String getOptor() {
        return optor;
    }

    public void setOptor(String optor) {
        this.optor = optor;
    }
}
