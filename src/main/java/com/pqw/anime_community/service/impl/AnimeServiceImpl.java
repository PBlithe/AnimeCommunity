package com.pqw.anime_community.service.impl;

import com.pqw.anime_community.entity.AnimeTopic;
import com.pqw.anime_community.repository.AnimeTopicRepository;
import com.pqw.anime_community.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeServiceImpl implements AnimeService {

    @Autowired
    private final AnimeTopicRepository animeTopicRepository;

    public AnimeServiceImpl(AnimeTopicRepository animeTopicRepository){
        this.animeTopicRepository=animeTopicRepository;
    }

    @Override
    public List<AnimeTopic> findAnimeTopic(AnimeTopic animeTopic, Integer startTime, Integer endTime, Pageable page) {

        List<AnimeTopic> list= new ArrayList<>();

        if(animeTopic.getAnimeLocation().equals("全部")&&
        animeTopic.getAnimeQuarter().equals("全部")&&
        startTime==0&&endTime==0&&
        animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findAllByOrderByAnimeHeatDesc(page);
        }else if(//地区
                 !animeTopic.getAnimeLocation().equals("全部")&&
                 animeTopic.getAnimeQuarter().equals("全部")&&
                 startTime==0&&endTime==0&&
                 animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeLocationOrderByAnimeHeatDesc
                    (animeTopic.getAnimeLocation(),page);
        }else if(//季度
                animeTopic.getAnimeLocation().equals("全部")&&
                !animeTopic.getAnimeQuarter().equals("全部")&&
                startTime==0&&endTime==0&&
                animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeQuarterOrderByAnimeHeatDesc
                    (animeTopic.getAnimeQuarter(),page);
        }else if(//类型
                animeTopic.getAnimeLocation().equals("全部")&&
                animeTopic.getAnimeQuarter().equals("全部")&&
                startTime==0&&endTime==0&&
                !animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeTypeContainingOrderByAnimeHeatDesc
                    (animeTopic.getAnimeType(),page);
        }else if(//时间
                animeTopic.getAnimeLocation().equals("全部")&&
                animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime!=0&&endTime!=0)&&
                animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeTimeOrderByAnimeHeatDesc
                    (startTime,endTime,page);
        }else if(//地区+季度
                !animeTopic.getAnimeLocation().equals("全部")&&
                !animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime==0&&endTime==0)&&
                animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeLocationAndAnimeQuarterOrderByAnimeHeatDesc
                    (animeTopic.getAnimeLocation(),animeTopic.getAnimeQuarter(),page);
        }else if(//地区+时间
                !animeTopic.getAnimeLocation().equals("全部")&&
                animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime!=0&&endTime!=0)&&
                animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeLocationAndAnimeTimeOrderByAnimeHeatDesc
                    (animeTopic.getAnimeLocation(),startTime,endTime,page);
        }else if(//地区+风格
                !animeTopic.getAnimeLocation().equals("全部")&&
                animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime==0&&endTime==0)&&
                !animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeLocationAndAnimeTypeOrderByAnimeHeatDesc
                    (animeTopic.getAnimeLocation(),animeTopic.getAnimeType(),page);
        }else if( //季度+时间
                animeTopic.getAnimeLocation().equals("全部")&&
                !animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime!=0&&endTime!=0)&&
                animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeQuarterAndAnimeTimeOrderByAnimeHeatDesc
                    (animeTopic.getAnimeQuarter(),startTime,endTime,page);
        }else if(//季度+风格
                animeTopic.getAnimeLocation().equals("全部")&&
                !animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime==0&&endTime==0)&&
                !animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeQuarterAndAnimeTypeOrderByAnimeHeatDesc
                    (animeTopic.getAnimeQuarter(),animeTopic.getAnimeType(),page);
        }else if(//时间+风格
                animeTopic.getAnimeLocation().equals("全部")&&
                animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime!=0&&endTime!=0)&&
                !animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeTimeAndAnimeTypeOrderByAnimeHeatDesc
                    (animeTopic.getAnimeType(),startTime,endTime,page);
        }else if(//地区+季度+时间
                !animeTopic.getAnimeLocation().equals("全部")&&
                !animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime!=0&&endTime!=0)&&
                animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeLocationAndAnimeQuarterAndAnimeTimeOrderByAnimeHeatDesc
                    (animeTopic.getAnimeLocation(),animeTopic.getAnimeQuarter(),startTime,endTime,page);
        }else if(//地区+季节+风格
                !animeTopic.getAnimeLocation().equals("全部")&&
                !animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime==0&&endTime==0)&&
                !animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeLocationAndAnimeQuarterAndAnimeTypeOrderByAnimeHeatDesc
                    (animeTopic.getAnimeLocation(),animeTopic.getAnimeQuarter(),animeTopic.getAnimeType(),page);
        }else if(//季度+时间+风格
                animeTopic.getAnimeLocation().equals("全部")&&
                !animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime!=0&&endTime!=0)&&
                !animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeQuarterAndAnimeTypeAndAnimeTimeOrderByAnimeHeatDesc
                    (animeTopic.getAnimeQuarter(),animeTopic.getAnimeType(),startTime,endTime,page);
        }else if(//地区+时间+风格
                !animeTopic.getAnimeLocation().equals("全部")&&
                animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime!=0&&endTime!=0)&&
                !animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeLocationAndAnimeTypeAndAnimeTimeOrderByAnimeHeatDesc
                    (animeTopic.getAnimeLocation(),animeTopic.getAnimeType(),startTime,endTime,page);
        }else if(//地区+季度+时间+风格
                !animeTopic.getAnimeLocation().equals("全部")&&
                !animeTopic.getAnimeQuarter().equals("全部")&&
                (startTime!=0&&endTime!=0)&&
                !animeTopic.getAnimeType().equals("全部")){
            list = animeTopicRepository.findByAnimeLocationAndAnimeQuarterAndAnimeTypeAndAnimeTimeOrderByAnimeHeatDesc
                    (animeTopic.getAnimeLocation(),animeTopic.getAnimeQuarter(),animeTopic.getAnimeType(),startTime,endTime,page);
        }else{

        }
        return list;
    }
}
