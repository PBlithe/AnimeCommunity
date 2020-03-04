package com.anime.endsystem.model;

import com.anime.endsystem.enums.AuthEnum;

public class Auth {
    private int id;
    private String a_id;
    private AuthEnum auth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public AuthEnum getAuth() {
        return auth;
    }

    public void setAuth(AuthEnum auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return " id= "+id+" a_id= "+a_id+" auth= "+auth;
    }
}
