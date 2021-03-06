package com.java.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.java.controller","com.java.service.impl"})
@MapperScan({"com.java.mapper"})
@EnableEurekaClient
public class AdminStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminStartApplication.class);
    }
}

