package com.anime.endsystem.service;

import com.anime.endsystem.enums.AdminState;
import com.anime.endsystem.enums.AuthEnum;
import com.anime.endsystem.enums.OPT;
import com.anime.endsystem.exception.NoAuthException;
import com.anime.endsystem.mapper.AdminLogsMapper;
import com.anime.endsystem.mapper.AdminMapper;
import com.anime.endsystem.mapper.AuthMapper;
import com.anime.endsystem.message.AdminResult;
import com.anime.endsystem.message.MAData;
import com.anime.endsystem.message.MAResult;
import com.anime.endsystem.message.ResultBean;
import com.anime.endsystem.model.Admin;
import com.anime.endsystem.model.AdminLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    public AdminMapper adminMapper;
    @Autowired
    public AuthMapper authMapper;
    @Autowired
    public AdminLogsMapper adminLogsMapper;

    private static final HashMap<String,String> fanxiangMap;
    static {
        fanxiangMap = new HashMap<String,String>();
        fanxiangMap.put("处理违规","1");
        fanxiangMap.put("查看日志","2");
        fanxiangMap.put("发送推送","3");
        fanxiangMap.put("写动漫文章","4");
        fanxiangMap.put("查询用户信息","5");
        fanxiangMap.put("帖子管理","6");
        fanxiangMap.put("动漫标签管理","7");
        fanxiangMap.put("修改账号","8");
        fanxiangMap.put("删除账号","9");
        fanxiangMap.put("创建管理员","0");
    }

    //创建管理员
    public void createAdmin(Admin admin) throws DataAccessException, NoAuthException {
        //查看该用户是否有权限创建管理员
        String auth = adminMapper.findAuthById(admin.getOptor());
        List<AuthEnum> is_exist = authMapper.findAuth(auth);
        if (is_exist.indexOf(AuthEnum.CreateAdmin) == -1) {
            throw new NoAuthException("没有权限");
        }
        //添加管理员的权限
        List<AuthEnum> auths = new ArrayList<AuthEnum>();
        String supple = new String("创建了");

        for (int i = 0; i < admin.getAuth().length(); i++) {
            if (admin.getAuth().charAt(i) == '1') {
                auths.add(AuthEnum.ChuLiWeiGui);
                supple += "  处理违规  ";
            } else if (admin.getAuth().charAt(i) == '2') {
                auths.add(AuthEnum.ChaKanRiZhi);
                supple += "  查看日志  ";
            } else if (admin.getAuth().charAt(i) == '3') {
                auths.add(AuthEnum.TuiSong);
                supple += "  发送推送  ";
            } else if (admin.getAuth().charAt(i) == '4') {
                auths.add(AuthEnum.WenZhang);
                supple += "  写动漫文章  ";
            } else if (admin.getAuth().charAt(i) == '5') {
                auths.add(AuthEnum.ChaXunYongHuXinXi);
                supple += "  查询用户信息  ";
            } else if (admin.getAuth().charAt(i) == '6') {
                auths.add(AuthEnum.TieZi);
                supple += "  帖子管理  ";
            } else if (admin.getAuth().charAt(i) == '7') {
                auths.add(AuthEnum.DongManBiaoQian);
                supple += "  动漫标签管理  ";
            } else if (admin.getAuth().charAt(i) == '8') {
                auths.add(AuthEnum.XiuGaiZhangHao);
                supple += "  修改账号  ";
            } else if (admin.getAuth().charAt(i) == '9') {
                auths.add(AuthEnum.ShanChuZhangHao);
                supple += "  删除账号  ";
            } else if (admin.getAuth().charAt(i) == '0') {
                auths.add(AuthEnum.CreateAdmin);
                supple += "  创建管理员  ";
            } else {
            }
        }
        if (authMapper.findAuthById(admin.getAuth()).isEmpty()) {//如果权限表中没有相关权限记录在增加
            for (AuthEnum a : auths) {
                authMapper.insertAuthById(admin.getAuth(), a);
                supple += "  " + a + "  "; // 增加管理员创建记录表中supple字段说明
            }
        }
        supple += "权限";
        adminMapper.addAdmin(admin);
        AdminLogs adminLogs = new AdminLogs();
        adminLogs.setId(admin.getId());
        adminLogs.setOpt(OPT.CREATE);
        adminLogs.setOptor(admin.getOptor());
        adminLogs.setSupple(supple);
        adminLogsMapper.addAdminLogs(adminLogs);
    }

    /**
     * 返回管理员修改创建记录
     *
     * @return AdminResult
     */
    public AdminResult searchAccountLogs(int page, int limit) throws ParseException {
        AdminResult result = new AdminResult();
        page = (page - 1) * limit;
        result.setData(adminLogsMapper.findAll(page, limit));
        result.setCount(adminLogsMapper.counts());
        for (int i = 0; i < result.getData().size(); i++) {
            AdminLogs adminLogs = result.getData().get(i);
            adminLogs.setNo(page + i + 1);
        }
        return result;
    }

    public MAResult returnMList(int page, int limit, String id) {
        MAResult result = new MAResult();
        page = (page - 1) * limit;
        List<Admin> admins = adminMapper.findAdminByIdAndLimit(page, limit, id);
        result.setCount(adminMapper.findCount());
        //首先将查询到的用户添加到结果中,添加的信息有用户id和状态
        for (Admin a : admins) {
            MAData data = new MAData();
            data.setId(a.getId());
            data.setState(a.getState());
            result.getData().add(data);
        }
        ////将权限加载到结果中
        List<AuthEnum> enums = new ArrayList<AuthEnum>();
        for (int i = 0; i < admins.size(); i++) {
            List<AuthEnum> auths = authMapper.findAuth(admins.get(i).getAuth());
            String s = "";
            for (AuthEnum ae : auths) {
                if (ae.equals(AuthEnum.ChuLiWeiGui)) {
                    s += "处理违规  ";
                } else if (ae.equals(AuthEnum.ChaKanRiZhi)) {
                    s += "查看日志  ";
                } else if (ae.equals(AuthEnum.TuiSong)) {
                    s += "发送推送  ";
                } else if (ae.equals(AuthEnum.WenZhang)) {
                    s += "写动漫文章  ";
                } else if (ae.equals(AuthEnum.ChaXunYongHuXinXi)) {
                    s += "查询用户信息  ";
                } else if (ae.equals(AuthEnum.TieZi)) {
                    s += "帖子管理  ";
                } else if (ae.equals(AuthEnum.DongManBiaoQian)) {
                    s += "动漫标签管理  ";
                } else if (ae.equals(AuthEnum.XiuGaiZhangHao)) {
                    s += "修改账号  ";
                } else if (ae.equals(AuthEnum.ShanChuZhangHao)) {
                    s += "删除账号  ";
                } else if (ae.equals(AuthEnum.CreateAdmin)) {
                    s += "创建管理员  ";
                } else {
                }
                result.getData().get(i).setAuth(s);
            }
            //设置序号
            result.getData().get(i).setNo(page + i + 1);
        }
        return result;
    }

    public ResultBean tryDeleteAccount(MAData data, String id) {
        //首先判断账号是否处于一个被封锁的状态
        if(adminMapper.findAdminById(data.getId()).getState() == AdminState.BLOCK){
            return new ResultBean().error("账号已经处于封锁状态");
        }
        //其次判断是否有权限删除，如果没有则返回没有权限删除
        String a_id = adminMapper.findAuthById(id);
        List<AuthEnum> enums = authMapper.findAuth(a_id);
        if (enums.indexOf(AuthEnum.ShanChuZhangHao) == -1) {
            return new ResultBean().error("没有删除账号的权限");
        }
        //删除账号
        adminMapper.deleteAdminById(data.getId());
        //删除了管理员以后要在记录表中添加数据。
        AdminLogs adminLogs = new AdminLogs();
        adminLogs.setId(data.getId());
        adminLogs.setOpt(OPT.DELETE);
        adminLogs.setOptor(id);
        adminLogsMapper.addAdminLogs(adminLogs);
        return new ResultBean().success();
    }
    public ResultBean tryBlock(MAData data, String id){
        //首先查看账号是否已经处于封锁状态
        //其次判断是否有权限修改（封锁也在修改的范围内）,如果没有则返回没有权限修改账号的状态
        //如果有则把账号的状态封锁，并把词条操作加入到管理员创建记录表中
        if(adminMapper.findAdminById(data.getId()).getState() == AdminState.BLOCK){
            return new ResultBean().error("账号已经处于封锁状态");
        }
        String a_id = adminMapper.findAuthById(id);
        List<AuthEnum> enums = authMapper.findAuth(a_id);
        if(enums.indexOf(AuthEnum.XiuGaiZhangHao) == -1){
            return new ResultBean().error("没有权限修改账号的状态");
        }
        //开始修改状态
        adminMapper.updateState(AdminState.BLOCK,data.getId());
        //修改了状态以后要在记录表中添加数据
        AdminLogs adminLogs = new AdminLogs();
        adminLogs.setId(data.getId());
        adminLogs.setOpt(OPT.BLOCK);
        adminLogs.setOptor(id);
        adminLogsMapper.addAdminLogs(adminLogs);
        return new ResultBean().success();
    }

    public ResultBean tryEdit(MAData data,String auth,String id){
        String[] spil = data.getAuth().split("  ");
        String tmpAuth = "";
        for (int i = 0; i < spil.length; i++) {
            tmpAuth += fanxiangMap.get(spil[i]);
        }
        //后台先判断当前账号是否被封禁，如果被封禁则返回账号被封禁无法修改
        if(adminMapper.findAdminById(data.getId()).getState() == AdminState.BLOCK){
            return new ResultBean().error("账号已经处于封锁状态");
        }
        //其次判断当前的操作人是否有权限修改用户权限
        String a_id = adminMapper.findAuthById(id);
        List<AuthEnum> enums = authMapper.findAuth(a_id);
        if(enums.indexOf(AuthEnum.XiuGaiZhangHao) == -1){
            return new ResultBean().error("没有权限修改账号的状态");
        }
        /**
         * 最后判断当前被选择的管理员有哪些权限，操作人勾选了什么权限。
         * 如果原有的权限被取消则要在管理员记录表中添加内容，如果增加新的权限也是如此。
         */
        //判断增加和删除了哪些权限
        int len = Math.max(tmpAuth.length(),auth.length());
        String add = "增加了";
        String delete = "删除了";
        for (int i = 0; i < len; i++) {
            if(i<tmpAuth.length()&& i<auth.length()){
                //都是修改了的权限(增加和删除)
                if(auth.charAt(i) != tmpAuth.charAt(i)){
                    add += modifyAuth(auth.charAt(i));
                    delete += modifyAuth(tmpAuth.charAt(i));
                }
            }else if(i<tmpAuth.length()&&i>=auth.length()){
                //全是删除
                delete += modifyAuth(tmpAuth.charAt(i));
            }else if(i>=tmpAuth.length()&&i<auth.length()){
                //全是增加
                add += modifyAuth(auth.charAt(i));
            }
        }
        add += "权限";
        delete += "权限";
        //修改管理员表中的权限表
        adminMapper.updateAuthById(auth,data.getId());
        //在权限表中增加记录
        authAdd(auth);
        //在管理员记录表中增加记录
        AdminLogs logs = new AdminLogs();
        logs.setId(data.getId());
        logs.setOpt(OPT.MODIFY);
        logs.setOptor(id);
        //判断权限是仅仅增加了还是删除了
        if(add.equals("增加了权限")){
            logs.setSupple(delete);
        }else if(delete.equals("删除了权限")){
            logs.setSupple(add);
        }else{
            logs.setSupple(add+"<br>"+delete);
        }
        adminLogsMapper.addAdminLogs(logs);
        //设置新的权限内容通过ResultBean返回
        data.setAuth(newAuth(auth));
        return new ResultBean().success(data);
    }

    private String modifyAuth(char c){
        String s = "";
        if (c == '1') {
            s += "  处理违规  ";
        } else if (c == '2') {
            s += "  查看日志  ";
        } else if (c == '3') {
            s += "  发送推送  ";
        } else if (c == '4') {
            s += "  写动漫文章  ";
        } else if (c == '5') {
            s += "  查询用户信息  ";
        } else if (c == '6') {
            s += "  帖子管理  ";
        } else if (c == '7') {
            s += "  动漫标签管理  ";
        } else if (c == '8') {
            s += "  修改账号  ";
        } else if (c == '9') {
            s += "  删除账号  ";
        } else if (c == '0') {
            s += "  创建管理员  ";
        } else {
        }
        return s;
    }

    private String newAuth(String str){
        String s = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                s += "处理违规  ";
            } else if (str.charAt(i) == '2') {
                s += "  查看日志  ";
            } else if (str.charAt(i) == '3') {
                s += "  发送推送  ";
            } else if (str.charAt(i) == '4') {
                s += "  写动漫文章  ";
            } else if (str.charAt(i) == '5') {
                s += "  查询用户信息  ";
            } else if (str.charAt(i) == '6') {
                s += "  帖子管理  ";
            } else if (str.charAt(i) == '7') {
                s += "  动漫标签管理  ";
            } else if (str.charAt(i) == '8') {
                s += "  修改账号  ";
            } else if (str.charAt(i) == '9') {
                s += "  删除账号  ";
            } else if (str.charAt(i) == '0') {
                s += "  创建管理员";
            } else {
            }
        }
            return s;
    }

    private void authAdd(String a){
        List<AuthEnum> auths = new ArrayList<AuthEnum>();

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '1') {
                auths.add(AuthEnum.ChuLiWeiGui);
            } else if (a.charAt(i) == '2') {
                auths.add(AuthEnum.ChaKanRiZhi);
            } else if (a.charAt(i) == '3') {
                auths.add(AuthEnum.TuiSong);
            } else if (a.charAt(i) == '4') {
                auths.add(AuthEnum.WenZhang);
            } else if (a.charAt(i) == '5') {
                auths.add(AuthEnum.ChaXunYongHuXinXi);
            } else if (a.charAt(i) == '6') {
                auths.add(AuthEnum.TieZi);;
            } else if (a.charAt(i) == '7') {
                auths.add(AuthEnum.DongManBiaoQian);
            } else if (a.charAt(i) == '8') {
                auths.add(AuthEnum.XiuGaiZhangHao);
            } else if (a.charAt(i) == '9') {
                auths.add(AuthEnum.ShanChuZhangHao);
            } else if (a.charAt(i) == '0') {
                auths.add(AuthEnum.CreateAdmin);
            } else {
            }
        }
        if (authMapper.findAuthById(a).isEmpty()) {//如果权限表中没有相关权限记录在增加
            for (AuthEnum au : auths) {
                authMapper.insertAuthById(a, au);
            }
        }
    }

}
