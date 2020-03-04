package com.anime.endsystem;

import com.anime.endsystem.mapper.AdminMapper;
import com.anime.endsystem.mapper.AuthMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminTest {
    @Autowired
    public AdminMapper adminMapper;
    @Autowired
    public AuthMapper authMapper;
    @Test
    void findAutoByIdTest(){
        System.out.println(adminMapper.findAuthById("123"));
    }

    @Test
    void findAuthTest(){
        System.out.println(authMapper.findAuth("123"));
    }
}
