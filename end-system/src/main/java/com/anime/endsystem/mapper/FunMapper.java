package com.anime.endsystem.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FunMapper {
    @Select("SELECT COUNT(*) FROM t_funs WHERE uid1 = #{uid1}")
    int funsCount(String uid1);

    @Select("SELECT uid2 FROM t_funs WHERE uid1=#{uid1}")
    List<String> findFuns(String uid1);
}
