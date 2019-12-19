package com.pqw.anime_community.repository;

import com.pqw.anime_community.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findByUsername(String username);

}
