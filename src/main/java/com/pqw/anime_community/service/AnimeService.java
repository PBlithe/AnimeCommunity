package com.pqw.anime_community.service;

import com.pqw.anime_community.entity.AnimeTopic;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnimeService {

    List<AnimeTopic> findAnimeTopic(AnimeTopic animeTopic, Integer start, Integer end, Pageable page);
}
