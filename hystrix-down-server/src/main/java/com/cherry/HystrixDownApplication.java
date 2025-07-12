package com.cherry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HystrixDownApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDownApplication.class, args);
    }
}