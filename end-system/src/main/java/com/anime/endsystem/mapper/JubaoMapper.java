package com.anime.endsystem.mapper;

import org.apache.ibatis.annotations.Select;

public interface JubaoMapper {
    @Select("SELECT COUNT(*) FROM t_jubao WHERE uid1=#{uid}")
    int jubaoCount(String uid1);
}
