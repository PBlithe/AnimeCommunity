package com.anime.endsystem.service;

import com.anime.endsystem.enums.AuthEnum;
import com.anime.endsystem.enums.UserOpt;
import com.anime.endsystem.enums.UserState;
import com.anime.endsystem.mapper.*;
import com.anime.endsystem.message.ResultBean;
import com.anime.endsystem.message.UserInfo;
import com.anime.endsystem.message.UserInfoResult;
import com.anime.endsystem.message.UserLogsResult;
import com.anime.endsystem.model.User;
import com.anime.endsystem.model.UserLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FunMapper funMapper;
    @Autowired
    private FollowingMapper followingMapper;
    @Autowired
    private JubaoMapper jubaoMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private UserLogsMapper userLogsMapper;


    public UserInfoResult userInfo(int page,int limit){
        //首先在t_user表中查看用户ID、昵称、手机号、账号状态
        List<User> users = userMapper.findUsers(page,limit);
        int count = userMapper.findUsersCount();
        return userInfoTempelet(users,count);
    }

    public UserInfoResult userInfoUID(String uid,int page,int limit){
        //首先在t_user表中查看用户ID、昵称、手机号、账号状态
        List<User> users = userMapper.findUserByUID(uid,page,limit);
        int count = userMapper.findUserByUIDCount(uid);
        return userInfoTempelet(users,count);
    }
    public UserInfoResult userInfoNickname(String nickname,int page,int limit){
        //首先在t_user表中查看用户ID、昵称、手机号、账号状态
        List<User> users = userMapper.findUserByNickname(nickname,page,limit);
        int count = userMapper.findUserByNicknameCount(nickname);
        return userInfoTempelet(users,count);
    }
    public UserInfoResult userInfoTel(String tel,int page,int limit){
        //首先在t_user表中查看用户ID、昵称、手机号、账号状态
        List<User> users = userMapper.findUserByTel(tel,page,limit);
        int count = userMapper.findUserByTelCount(tel);
        return userInfoTempelet(users,count);
    }
    public ResultBean tryBlock(UserInfo data, String id){
        //首先查看账号是否已经处于封锁状态
        ///首先判断是否有处理违规的权限,如果没有则返回没有处理违规的权限
        //如果有则把账号的状态封锁，并把词条操作加入到管理员创建记录表中
        if(userMapper.findUserStateByUid(data.getUid()) == UserState.BLOCK){
            return new ResultBean().error("账号已经处于封锁状态");
        }
        String a_id = adminMapper.findAuthById(id);
        List<AuthEnum> enums = authMapper.findAuth(a_id);
        if(enums.indexOf(AuthEnum.XiuGaiZhangHao) == -1){
            return new ResultBean().error("没有处理账号的权限");
        }
        //开始修改状态
        userMapper.updateStateByUid(UserState.BLOCK,data.getUid());
        //修改了状态以后要在记录表中添加数据
        UserLogs userLogs = new UserLogs();
        userLogs.setUid(data.getUid());
        userLogs.setOpt(UserOpt.BLOCK);
        userLogs.setOptor(id);
        userLogsMapper.insertUserLogs(userLogs);
        return new ResultBean().success();
    }
    public ResultBean trydisBlock(UserInfo data, String id){
        //判断管理员是否有权限处理违规账号
        String a_id = adminMapper.findAuthById(id);
        List<AuthEnum> enums = authMapper.findAuth(a_id);
        if(enums.indexOf(AuthEnum.XiuGaiZhangHao) == -1){
            return new ResultBean().error("没有处理账号的权限");
        }
        //如果有修改user状态
        userMapper.updateStateByUid(UserState.NORMAL,data.getUid());
        //修改了状态以后要在记录表中添加数据
        UserLogs userLogs = new UserLogs();
        userLogs.setUid(data.getUid());
        userLogs.setOpt(UserOpt.RELIEVE);
        userLogs.setOptor(id);
        userLogsMapper.insertUserLogs(userLogs);
        return new ResultBean().success();
    }

    public void following(String uid, Model model){
        //搜索关注列表
        List<String> following = new ArrayList<String>();
        following = followingMapper.findFollowing(uid);
        List<User> users = new ArrayList<>();
        for (String f: following) {
            User user = new User();
            user = userMapper.findUserByUid(f);
            users.add(user);
        }
        model.addAttribute("followings",users);
        model.addAttribute("uid",uid);
    }

    public void funs(String uid,Model model){
        //搜搜粉丝列表
        List<String> funs = new ArrayList<String>();
        funs = funMapper.findFuns(uid);
        List<User> users = new ArrayList<>();
        for (String f: funs) {
            User user = new User();
            user = userMapper.findUserByUid(f);
            users.add(user);
        }
        model.addAttribute("funs",users);
        model.addAttribute("uid",uid);
    }

    public UserLogsResult userLogs(int page,int limit){
        List<UserLogs> ullist = userLogsMapper.findUserLogs(page,limit);
        int count = userLogsMapper.findUserLogsCount();
        return userLogsTemplet(ullist,page,count);
    }

    public UserLogsResult userLogsUid(String uid,int page,int limit){
        List<UserLogs> ullist = userLogsMapper.findUserLogsByUid(uid,page,limit);
        int count = 1;
        return userLogsTemplet(ullist,page,count);
    }

    public UserLogsResult userLogsTime(String time,int page,int limit){
        String start = time.substring(0,19);
        String end = time.substring(22);
        List<UserLogs> ullist = new ArrayList<>();
        ullist = userLogsMapper.findUserLogsByTimeRange(start,end,page,limit);
        int count = userLogsMapper.findUserLogsByTimeRangeCount(start,end);
        return userLogsTemplet(ullist,page,count);
    }

    private UserLogsResult userLogsTemplet(List<UserLogs> ullist,int page,int count){

        UserLogsResult result = new UserLogsResult();
        result.setCount(count);//设置数量;
        for (int i = 0; i < ullist.size(); i++) {
            ullist.get(i).setNo(page+i+1);
        }
        result.setData(ullist);//设置数据
        return result;
    }

    private UserInfoResult userInfoTempelet(List<User> users,int count){
        /**
         * 在t_funs表和t_following表中查询关注的人和粉丝数量
         * t_jubao表中查被举报次数
         */
        List<UserInfo> data = new ArrayList<UserInfo>();
        for (User user : users) {
            //查询粉丝数量
            int funs = funMapper.funsCount(user.getUid());
            //查询关注的数量
            int following = followingMapper.followingCount(user.getUid());
            //查询被举报的次数
            int jubaoed = jubaoMapper.jubaoCount(user.getUid());
            //设置UserInfo
            UserInfo userInfo = new UserInfo(user.getUid(),user.getNickname(),user.getTel(),following,funs,jubaoed,user.getState());
            data.add(userInfo);
        }
        UserInfoResult result = new UserInfoResult();
        result.setCount(count);
        result.setData(data);
        return result;
    }

}
