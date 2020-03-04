package com.anime.endsystem.service;

import com.anime.endsystem.enums.AuthEnum;
import com.anime.endsystem.enums.OPT;
import com.anime.endsystem.exception.NoAuthException;
import com.anime.endsystem.mapper.AdminLogsMapper;
import com.anime.endsystem.mapper.AdminMapper;
import com.anime.endsystem.mapper.AuthMapper;
import com.anime.endsystem.message.AdminResult;
import com.anime.endsystem.model.Admin;
import com.anime.endsystem.model.AdminLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    public AdminMapper adminMapper;
    @Autowired
    public AuthMapper authMapper;
    @Autowired
    public AdminLogsMapper adminLogsMapper;

    //创建管理员
    public void createAdmin(Admin admin) throws DataAccessException, NoAuthException {
        //查看该用户是否有权限创建管理员
        String auth = adminMapper.findAuthById(admin.getOptor());
        List<AuthEnum> is_exist = authMapper.findAuth(auth);
        if(is_exist.indexOf(AuthEnum.CreateAdmin) == -1){
            throw new NoAuthException("没有权限");
        }
        //添加管理员的权限
        List<AuthEnum> auths = new ArrayList<AuthEnum>();
        if(authMapper.findAuthById(admin.getAuth()).isEmpty()){//如果权限表中没有相关权限记录在增加
            for (int i = 0; i < admin.getAuth().length(); i++) {
                if(admin.getAuth().charAt(i) == '1'){
                    auths.add(AuthEnum.ChuLiWeiGui);
                }else if(admin.getAuth().charAt(i) == '2'){
                    auths.add(AuthEnum.ChaKanRiZhi);
                }else if(admin.getAuth().charAt(i) == '3'){
                    auths.add(AuthEnum.TuiSong);
                }else if(admin.getAuth().charAt(i) == '4'){
                    auths.add(AuthEnum.WenZhang);
                }else if(admin.getAuth().charAt(i) == '5'){
                    auths.add(AuthEnum.ChaXunYongHuXinXi);
                }else if (admin.getAuth().charAt(i) == '6'){
                    auths.add(AuthEnum.TieZi);
                }else if(admin.getAuth().charAt(i) == '7'){
                    auths.add(AuthEnum.DongManBiaoQian);
                }else if(admin.getAuth().charAt(i) == '8'){
                    auths.add(AuthEnum.XiuGaiZhangHao);
                }else if(admin.getAuth().charAt(i) == '9'){
                    auths.add(AuthEnum.ShanChuZhangHao);
                }else if(admin.getAuth().charAt(i) == '0'){
                    auths.add(AuthEnum.CreateAdmin);
                }else {}
            }
            for (AuthEnum a: auths
            ) {
                System.out.println(a);
                authMapper.insertAuthById(admin.getAuth(),a);
            }
        }
        adminMapper.addAdmin(admin);
        AdminLogs adminLogs = new AdminLogs();
        adminLogs.setId(admin.getId());
        adminLogs.setOpt(OPT.CREATE);
        adminLogs.setOptor(admin.getOptor());
        adminLogsMapper.addAdminLogs(adminLogs);
    }

    /**
     * 返回管理员修改创建记录
     * @return AdminResult
     */
    public AdminResult searchAccountLogs(int page,int limit) throws ParseException {
        AdminResult result = new AdminResult();
        page = (page-1)*limit;
        result.setData(adminLogsMapper.findAll(page,limit));
        result.setCount(adminLogsMapper.counts());
        for (int i = 0; i < result.getData().size(); i++) {
            AdminLogs adminLogs = result.getData().get(i);
            adminLogs.setNo(i+1);
        }
        return result;
    }
}
