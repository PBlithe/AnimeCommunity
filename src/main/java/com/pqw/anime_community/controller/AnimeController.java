package com.pqw.anime_community.controller;

import com.pqw.anime_community.entity.AnimeTopic;
import com.pqw.anime_community.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@RestController
public class AnimeController {

    @Autowired
    public AnimeService animeService;

    @RequestMapping("/anime_center")
    public List<AnimeTopic> animeSquareList(int curr){
        int page = curr,size = 10;
        Integer startTime = 0;
        Integer endTime = 0;
        AnimeTopic animeTopic = new AnimeTopic();
        animeTopic.setAnimeLocation("全部");
        animeTopic.setAnimeQuarter("全部");
        animeTopic.setAnimeType("全部");
        List<AnimeTopic> animeTopics = new ArrayList<>();
        Sort sort = Sort.by(Sort.Direction.DESC,"animeHeat");
        Pageable pageable = PageRequest.of(page,size,sort);
        animeTopics = animeService.findAnimeTopic(animeTopic,startTime,endTime,pageable);
        return animeTopics;
    }
}
