package com.pqw.anime_community.service.impl;

import com.pqw.anime_community.entity.User;
import com.pqw.anime_community.repository.UserRepository;
import com.pqw.anime_community.service.UserService;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private  final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final static Map<Integer,String> ENCODER_TYPE = new HashMap<>();
    private final static Map<String, PasswordEncoder> ENCODER_MAP = new HashMap<>();
    private final static String PASSWORD_FORMAT = "{%s}%s";

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    static {
        ENCODER_TYPE.put(0, "bcrypt");
        ENCODER_TYPE.put(1, "pbkdf2");
        ENCODER_TYPE.put(2, "sha256");

        ENCODER_MAP.put("bcrypt", new BCryptPasswordEncoder());
        ENCODER_MAP.put("pbkdf2", new Pbkdf2PasswordEncoder());
        ENCODER_MAP.put("sha256", new StandardPasswordEncoder());
    }

    @Override
    public void insert(User user) {
        String username = user.getUsername();
        if(exist(username)){
            throw new RuntimeException("用户名已存在！");
        }
        String encoderType = ENCODER_TYPE.get(2);
        PasswordEncoder passwordEncoder = ENCODER_MAP.get(encoderType);
        user.setPassword(String.format(PASSWORD_FORMAT, encoderType, passwordEncoder.encode(user.getPassword())));
        //System.out.println("user");
        userRepository.save(user);
       // System.out.println("插入成功");
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private boolean exist(String username) {
        User user = userRepository.findByUsername(username);
        return (user != null);
    }
}
