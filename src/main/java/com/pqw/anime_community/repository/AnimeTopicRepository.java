package com.pqw.anime_community.repository;

import com.pqw.anime_community.entity.AnimeTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AnimeTopicRepository extends JpaRepository<AnimeTopic,String> {

    @Transactional
    @Modifying
    @Query("update AnimeTopic set anime_small_photo = ?1 where anime_id = ?2")
    int modifyByAnimeId(String  anime_small_photo, String anime_id);
    //按热度查找所有动漫
    List<AnimeTopic> findAllByOrderByAnimeHeatDesc(Pageable page);
    //按动漫地区排序降序查找动漫
    List<AnimeTopic> findByAnimeLocationOrderByAnimeHeatDesc(String animeLocation,Pageable page);
    //按动漫季度降序排序查找动漫
    List<AnimeTopic> findByAnimeQuarterOrderByAnimeHeatDesc(String animeQuarter,Pageable page);
    //按动漫播出时间降序查找动漫
    @Transactional
    @Query("select a from AnimeTopic as a where year(a.animeTime) >=?1 and year(a.animeTime)<=?2 order by a.animeHeat desc")
    List<AnimeTopic> findByAnimeTimeOrderByAnimeHeatDesc(Integer startTime,Integer endTime,Pageable page);
    //按动漫播出类型降序查找动漫
    List<AnimeTopic> findByAnimeTypeContainingOrderByAnimeHeatDesc(String animeType,Pageable page);
    //地区+季度查找
    List<AnimeTopic> findByAnimeLocationAndAnimeQuarterOrderByAnimeHeatDesc(String animeLocation,String animeQuarter,Pageable page);
    //地区+时间查找
    @Transactional
    @Query("select a from AnimeTopic as a where a.animeLocation= ?1 and year(a.animeTime) >=?2 and year(a.animeTime)<=?3 order by a.animeHeat desc")
    List<AnimeTopic> findByAnimeLocationAndAnimeTimeOrderByAnimeHeatDesc(String animeLocation,Integer startTime,Integer endTime,Pageable page);
    //地区+风格查找
    List<AnimeTopic> findByAnimeLocationAndAnimeTypeOrderByAnimeHeatDesc(String animeLocation,String animeType,Pageable page);
    //季度+时间查找
    @Transactional
    @Query("select a from AnimeTopic a where a.animeQuarter=?1 and year(a.animeTime)>=?2 and year(a.animeTime)<=?3 order by a.animeHeat desc")
    List<AnimeTopic> findByAnimeQuarterAndAnimeTimeOrderByAnimeHeatDesc(String animeQuarter,Integer startTime,Integer endTime,Pageable page);
    //季度+风格查找
    List<AnimeTopic> findByAnimeQuarterAndAnimeTypeOrderByAnimeHeatDesc(String animeQuarter,String animeType,Pageable page);
    //时间+风格查找
    @Transactional
    @Query("select a from AnimeTopic a where a.animeType=?1 and year(a.animeTime)>=?2 and year(a.animeTime)<=?3 order by a.animeHeat desc")
    List<AnimeTopic> findByAnimeTimeAndAnimeTypeOrderByAnimeHeatDesc(String animeType,Integer startTime,Integer endTime,Pageable page);
    //地区+季度+时间
    @Transactional
    @Query("select a from AnimeTopic a where a.animeLocation=?1 and a.animeQuarter=?2 and year(a.animeTime)>=?3 and year(a.animeTime)<=?4 order by a.animeHeat desc")
    List<AnimeTopic> findByAnimeLocationAndAnimeQuarterAndAnimeTimeOrderByAnimeHeatDesc
        (String animeLocation,String animeQuarter,Integer startTime,Integer endTime,Pageable page);
    //地区+季节+风格
    List<AnimeTopic> findByAnimeLocationAndAnimeQuarterAndAnimeTypeOrderByAnimeHeatDesc
        (String animeLocation,String animeQuarter,String animeType,Pageable page);
    //季度+时间+风格
    @Transactional
    @Query("select a from AnimeTopic a where a.animeQuarter=?1 and a.animeType=?2 and year(a.animeTime)>=?3 and year(a.animeTime)<=?4 order by a.animeHeat desc")
    List<AnimeTopic> findByAnimeQuarterAndAnimeTypeAndAnimeTimeOrderByAnimeHeatDesc
        (String animeQuarter,String animeType,Integer startTime,Integer endTime,Pageable page);
    //地区+时间+风格
    @Transactional
    @Query("select a from AnimeTopic a where a.animeLocation=?1 and a.animeType=?2 and year(a.animeTime)>=?3 and year(a.animeTime)<=?4 order by a.animeHeat desc")
    List<AnimeTopic> findByAnimeLocationAndAnimeTypeAndAnimeTimeOrderByAnimeHeatDesc
        (String animeLocation,String animeType,Integer startTime,Integer endTime,Pageable page);
    //地区+季度+时间+风格
    @Transactional
    @Query("select a from AnimeTopic a where a.animeLocation=?1 and a.animeQuarter=?2 and a.animeType=?3 and year(a.animeTime)>=?4 and year(a.animeTime)<=?5 order by a.animeHeat desc")
    List<AnimeTopic> findByAnimeLocationAndAnimeQuarterAndAnimeTypeAndAnimeTimeOrderByAnimeHeatDesc
            (String animeLocation,String animeQuarter,String animeType,Integer startTime,Integer endTime,Pageable page);
}
