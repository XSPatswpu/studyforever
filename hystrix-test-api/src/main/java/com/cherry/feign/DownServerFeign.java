package com.cherry.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "downServer", url = "http://127.0.0.1:8080", fallbackFactory = DownServerFeignFallbackFactory.class)
public interface DownServerFeign {

    @GetMapping("/down/server/info")
    String downServerInfo();
}
