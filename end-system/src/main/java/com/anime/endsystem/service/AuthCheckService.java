package com.anime.endsystem.service;

import com.anime.endsystem.enums.AuthEnum;
import com.anime.endsystem.mapper.AdminMapper;
import com.anime.endsystem.mapper.AuthMapper;
import com.anime.endsystem.message.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthCheckService {

    @Autowired
    public AdminMapper adminMapper;
    @Autowired
    public AuthMapper authMapper;

    public ResultBean createAccountLogsAJAX(String id){
        String auth = adminMapper.findAuthById(id);
        List<AuthEnum> result = authMapper.findAuth(auth);
        if(result.indexOf(AuthEnum.ChaKanRiZhi) == -1){
            return new ResultBean().error("你没有查看日志的权限");
        }
        return new ResultBean().success();
    }
}
