package com.anime.endsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.anime.endsystem.mapper")
public class EndSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndSystemApplication.class, args);
    }

}
