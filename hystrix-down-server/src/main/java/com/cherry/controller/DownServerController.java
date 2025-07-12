package com.cherry.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/down/server")
public class DownServerController {

    @GetMapping("/info")
    public String getInfo() {
        return "down server";
    }
}
