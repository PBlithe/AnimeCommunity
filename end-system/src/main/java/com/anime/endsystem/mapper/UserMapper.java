package com.anime.endsystem.mapper;

import com.anime.endsystem.enums.UserState;
import com.anime.endsystem.model.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM t_user LIMIT #{start},#{end}")
    List<User> findUsers(int start,int end);//分页查找用户

    @Select("SELECT COUNT(*) FROM t_user")
    int findUsersCount();//分页查找用户返回数量

    @Select("SELECT * FROM t_user WHERE nickname LIKE CONCAT('%',#{nickname},'%') LIMIT #{start},#{end}")
    List<User> findUserByNickname(String nickname,int start,int end);

    @Select("SELECT COUNT(*) FROM t_user WHERE nickname LIKE CONCAT('%',#{nickname},'%')")
    int findUserByNicknameCount(String nickname);

    @Select("SELECT * FROM t_user WHERE uid = #{uid} LIMIT #{start},#{end}")
    List<User> findUserByUID(String uid,int start,int end);

    @Select("SELECT COUNT(*) FROM t_user WHERE uid = #{uid}")
    int findUserByUIDCount(String uid);

    @Select("SELECT * FROM t_user WHERE tel = #{tel} LIMIT #{start},#{end}")
    List<User> findUserByTel(String tel,int start,int end);

    @Select("SELECT COUNT(*) FROM t_user WHERE tel = #{tel}")
    int findUserByTelCount(String tel);

    @Select("SELECT state FROM t_user WHERE uid=#{uid}")
    UserState findUserStateByUid(String uid);

    @Update("UPDATE t_user SET state = #{state} WHERE uid=#{uid}")
    void updateStateByUid(UserState state,String uid);

    @Select("SELECT * FROM t_user WHERE uid=#{uid}")
    User findUserByUid(String uid);
}
