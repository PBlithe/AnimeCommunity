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
        return panduanAuth(id,AuthEnum.ChaKanRiZhi,"查看日志");
    }
    public ResultBean selectUserInfoAJAX(String id){
        return panduanAuth(id,AuthEnum.ChaXunYongHuXinXi,"查询用户信息");
    }
    public ResultBean manageUserAJAX(String id){
        return panduanAuth(id,AuthEnum.ChuLiWeiGui,"处理违规账号");
    }
    private ResultBean panduanAuth(String id,AuthEnum authEnum,String explain){

        String auth = adminMapper.findAuthById(id);
        List<AuthEnum> result = authMapper.findAuth(auth);
        if(result.indexOf(authEnum) == -1){
            return new ResultBean().error("你没有"+explain+"的权限");
        }
        return new ResultBean().success();
    }
}
