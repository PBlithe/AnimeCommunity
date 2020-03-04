package com.anime.endsystem.message;

import com.anime.endsystem.model.AdminLogs;

import java.util.List;

public class AdminResult {
    private Integer code;
    private String msg;
    private Integer count;
    private List<AdminLogs> data;

    public AdminResult() {
        this.code = 0;
        this.msg = "";
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<AdminLogs> getData() {
        return data;
    }

    public void setData(List<AdminLogs> data) {
        this.data = data;
    }
}
