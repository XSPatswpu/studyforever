package com.cherry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.cherry.feign"})
@SpringBootApplication
public class HystrixUpApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixUpApplication.class, args);
    }
}