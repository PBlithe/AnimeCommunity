package com.pqw.anime_community.service;

import com.pqw.anime_community.entity.User;

public interface UserService {

    void insert(User user);

    User getByUsername(String username);
}
