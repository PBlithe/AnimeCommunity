package com.anime.endsystem.mapper;

import com.anime.endsystem.model.UserLogs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserLogsMapper {
    @Insert("INSERT INTO t_user_logs(uid,opt,optor) VALUES(#{uid},#{opt},#{optor})")
    void insertUserLogs(UserLogs userLogs);

    @Select("SELECT * FROM t_user_logs ORDER BY time DESC LIMIT #{start},#{end}")
    List<UserLogs> findUserLogs(int start,int end);//分页查询

    @Select("SELECT COUNT(*) FROM t_user_logs")
    int findUserLogsCount();

    @Select("SELECT * FROM t_user_logs WHERE time BETWEEN #{start} AND #{end} LIMIT #{lstart},#{lend}")
    List<UserLogs> findUserLogsByTimeRange(String start, String end,int lstart,int lend);

    @Select("SELECT COUNT(*) FROM t_user_logs WHERE time BETWEEN #{start} AND #{end}")
    int findUserLogsByTimeRangeCount(String start, String end);

    @Select("SELECT * FROM t_user_logs WHERE uid=#{uid} LIMIT #{start},#{end}")
    List<UserLogs> findUserLogsByUid(String uid, int start, int end);

    @Select("SELECT COUNT(*) FROM t_user_logs WHERE uid=#{uid}")
    List<UserLogs> findUserLogsByUidCount(String uid);
}
