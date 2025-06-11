package com.cherry.controller;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/trace/test")
public class TraceController {

    private Tracer tracer;
    @PostConstruct
    public void init() {
        OpenTelemetrySdk.builder()
                .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
                .buildAndRegisterGlobal();
        tracer = GlobalOpenTelemetry.get().getTracer("manual-sdk", "1.0.0");
    }

    @GetMapping("/id")
    public String getTraceId() {
        Span span = tracer.spanBuilder("parent").setSpanKind(SpanKind.SERVER).startSpan();
        String traceId = Span.current().getSpanContext().getTraceId();
        log.info("test traceId = {}", traceId);
        return traceId;
    }

}
