package com.anime.endsystem.mapper;

import com.anime.endsystem.model.AdminLogs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AdminLogsMapper {

    //插入一条记录
    @Insert("INSERT INTO t_admin_logs(id,opt,optor) VALUES(#{id},#{opt},#{optor})")
    void addAdminLogs(AdminLogs adminLogs) throws DataAccessException;

    //查询记录表的记录
    @Select("SELECT * FROM t_admin_logs ORDER BY time DESC LIMIT #{start},#{end}")
    List<AdminLogs> findAll(int start,int end);

    @Select("SELECT COUNT(*) FROM t_admin_logs")
    int counts();
}
