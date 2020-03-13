package com.anime.endsystem.message;

import com.anime.endsystem.model.UserLogs;

import java.util.List;

public class UserLogsResult {
    private Integer code;
    private String msg;
    private Integer count;
    private List<UserLogs> data;

    public UserLogsResult() {
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

    public List<UserLogs> getData() {
        return data;
    }

    public void setData(List<UserLogs> data) {
        this.data = data;
    }
}
