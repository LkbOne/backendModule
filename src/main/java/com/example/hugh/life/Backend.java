package com.example.hugh.life;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.hugh.life.dao.api")
@ImportResource("classpath:spring/applicationContext.xml")

@EnableScheduling
//public class Backend extends SpringBootServletInitializer {
public class Backend  {

    public static void main(String[] args) {
        SpringApplication.run(Backend.class, args);
    }
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(Backend.class);
//    }
}
