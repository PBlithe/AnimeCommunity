package com.anime.endsystem;

import com.anime.endsystem.enums.AdminState;
import com.anime.endsystem.enums.AuthEnum;
import com.anime.endsystem.mapper.AdminMapper;
import com.anime.endsystem.mapper.AuthMapper;
import com.anime.endsystem.model.Admin;
import com.anime.endsystem.model.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EndSystemApplicationTests {

    @Autowired
    AuthMapper authMapper;
    @Autowired
    AdminMapper adminMapper;

    @Test
    void contextLoads() {
    }
    @Test
    void testAuth(){
        List<Auth> authList = new ArrayList<Auth>();
        authList = authMapper.findAuthById("123456789");
        for(int i=0;i<authList.size();i++){
            System.out.println(authList);
        }
    }
    @Test
    void insertAuth(){
        authMapper.insertAuthById("123", AuthEnum.ChaKanRiZhi);
    }
    @Test
    void testAdmin(){
        Admin admin = new Admin();
        admin.setId("123456789");
        admin.setAuth("123");
        admin.setPassword("123456");
        admin.setOptor("123456");
        admin.setState(AdminState.NORMAL);
        adminMapper.addAdmin(admin);
        adminMapper.updateAuthById("123456","123456");
        adminMapper.addAdmin(admin);
    }
}
