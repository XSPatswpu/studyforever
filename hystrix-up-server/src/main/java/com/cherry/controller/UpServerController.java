package com.cherry.controller;

import com.cherry.feign.DownServerFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/up/server")
public class UpServerController {

    @Autowired
    private DownServerFeign downServerFeign;

    @GetMapping("/invoke/down")
    public String getDownServerInfo() {
        return downServerFeign.downServerInfo();
    }

}
