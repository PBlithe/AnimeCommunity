package com.anime.endsystem.model;


import com.anime.endsystem.enums.OPT;

import java.io.Serializable;
import java.util.Date;

public class AdminLogs implements Serializable {
    private int no;//序号
    private String id;//账号ID
    private Date time;//时间，创建或者修改的时间
    private OPT opt;//操作类型，CREATE/MODIFY/DELETE/BLOCK
    private String optor;//操作人ID
    private String supple;//补充说明


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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public OPT getOpt() {
        return opt;
    }

    public void setOpt(OPT opt) {
        this.opt = opt;
    }

    public String getOptor() {
        return optor;
    }

    public void setOptor(String optor) {
        this.optor = optor;
    }

    public String getSupple() {
        return supple;
    }

    public void setSupple(String supple) {
        this.supple = supple;
    }
}
