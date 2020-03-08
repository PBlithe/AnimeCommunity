package com.anime.endsystem.message;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于返回修改账号表的结果
 */
public class MAResult {

    private Integer code;
    private String msg;
    private Integer count;
    List<MAData> data;

    public MAResult() {
        this.code = 0;
        this.msg = "";
        this.data = new ArrayList<MAData>();
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

    public List<MAData> getData() {
        return data;
    }

    public void setData(List<MAData> data) {
        this.data = data;
    }
}
