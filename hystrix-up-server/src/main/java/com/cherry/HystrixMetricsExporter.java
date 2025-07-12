package com.cherry;

import javax.annotation.PostConstruct;

import com.soundcloud.prometheus.hystrix.HystrixPrometheusMetricsPublisher;
import io.prometheus.client.exporter.HTTPServer;
import org.springframework.stereotype.Component;

@Component
public class HystrixMetricsExporter {

    @PostConstruct
    public void start() throws Exception {
        System.out.println(">>> Initializing Prometheus + Hystrix exporter");
        HystrixPrometheusMetricsPublisher.register(); // 注册 Hystrix -> Prometheus exporter
        new HTTPServer(1234);                         // 暴露 /metrics
    }
}

