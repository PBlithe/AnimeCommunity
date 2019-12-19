package com.pqw.anime_community;

import com.pqw.anime_community.entity.AnimeTopic;
import com.pqw.anime_community.repository.AnimeTopicRepository;
import com.pqw.anime_community.service.AnimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class AnimeCommunityApplicationTests {

    @Autowired
    public AnimeTopicRepository animeTopicRepository;
    @Autowired
    public AnimeService animeService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testBaseQuery() throws Exception {
        int page=0,size=10;
        Sort sort = Sort.by(Sort.Direction.DESC,"animeHeat");
        Pageable pageable = PageRequest.of(page,size,sort);
        List<AnimeTopic> pages  = animeTopicRepository.findAllByOrderByAnimeHeatDesc(pageable);
        for(AnimeTopic p : pages){
            System.out.println(p);
        }
    }


}

