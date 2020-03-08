package com.anime.endsystem.message;

import com.anime.endsystem.enums.AdminState;

/**
 * 对应了修改账号表的中列
 */
public class MAData {
    private Integer no;
    private String id;
    private String auth;
    private AdminState state;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public AdminState getState() {
        return state;
    }

    public void setState(AdminState state) {
        this.state = state;
    }
}
