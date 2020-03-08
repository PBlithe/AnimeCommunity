package com.anime.endsystem.mapper;

import com.anime.endsystem.enums.AuthEnum;
import com.anime.endsystem.model.Auth;
import org.apache.ibatis.annotations.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * 权限表
 */
public interface AuthMapper {

    @Select("SELECT * FROM t_auth WHERE a_id = #{a_id}")
    @Results({
            @Result(property = "a_id",column = "a_id",javaType = String.class),
            @Result(property = "auth",column = "auth",javaType = AuthEnum.class)
    })
    List<Auth> findAuthById(@Param("a_id") String a_id);//通过权限字符串查询查询有哪些权限

    @Insert("INSERT INTO t_auth(a_id,auth) VALUES(#{a_id},#{auth})")
    void insertAuthById(String a_id,AuthEnum auth) throws DataAccessException;//插入新的权限字符串

    @Select("SELECT auth FROM t_auth WHERE a_id=#{a_id}")
    List<AuthEnum> findAuth(String a_id);//通过权限字符串查询权限列表（仅仅包含权限）
}
