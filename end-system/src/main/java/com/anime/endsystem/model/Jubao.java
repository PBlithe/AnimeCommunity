package com.anime.endsystem.model;

import com.anime.endsystem.enums.JubaoReason;
import com.anime.endsystem.enums.JubaoSate;
import com.anime.endsystem.enums.PostType;

import java.util.Date;

public class Jubao {
    private int no;
    private String uid1;
    private String uid2;
    private JubaoReason reason;
    private String supple;
    private Date time;
    private String pid;
    private PostType type;
    private JubaoSate state;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUid1() {
        return uid1;
    }

    public void setUid1(String uid1) {
        this.uid1 = uid1;
    }

    public String getUid2() {
        return uid2;
    }

    public void setUid2(String uid2) {
        this.uid2 = uid2;
    }

    public JubaoReason getReason() {
        return reason;
    }

    public void setReason(JubaoReason reason) {
        this.reason = reason;
    }

    public String getSupple() {
        return supple;
    }

    public void setSupple(String supple) {
        this.supple = supple;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public PostType getType() {
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public JubaoSate getState() {
        return state;
    }

    public void setState(JubaoSate state) {
        this.state = state;
    }
}
