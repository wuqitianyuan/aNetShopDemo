package com.java.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.java.controller","com.java.service.impl"})
@MapperScan(basePackages = {"com.java.mapper"})
@EnableCaching
public class WebStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebStartApplication.class);
    }
}
