package com.anime.endsystem.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FollowingMapper {

    @Select("SELECT COUNT(*) FROM t_following WHERE uid1 = #{uid1}")
    int followingCount(String uid1);

    @Select("SELECT uid2 FROM t_following WHERE uid1=#{uid1}")
    List<String> findFollowing(String uid1);
}
