package com.anime.endsystem.message;

import com.anime.endsystem.enums.UserState;

public class UserInfo {
    private String uid;
    private String nickname;
    private String tel;
    private int following;
    private int funs;
    private int jubaoed;
    private UserState state;

    public UserInfo(String uid, String nickname, String tel, int following, int funs, int jubaoed, UserState state) {
        this.uid = uid;
        this.nickname = nickname;
        this.tel = tel;
        this.following = following;
        this.funs = funs;
        this.jubaoed = jubaoed;
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFuns() {
        return funs;
    }

    public void setFuns(int funs) {
        this.funs = funs;
    }

    public int getJubaoed() {
        return jubaoed;
    }

    public void setJubaoed(int jubaoed) {
        this.jubaoed = jubaoed;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }
}
