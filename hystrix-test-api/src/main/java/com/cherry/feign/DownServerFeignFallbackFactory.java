package com.cherry.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DownServerFeignFallbackFactory implements FallbackFactory<DownServerFeign> {
    @Override
    public DownServerFeign create(Throwable cause) {
        return new DownServerFeign() {
            @Override
            public String downServerInfo() {
                log.error("DownServerFeignFallbackFactory error:{}", cause.getMessage());
                return "error";
            }
        };
    }
}
