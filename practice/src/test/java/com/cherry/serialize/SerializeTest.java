package com.cherry.serialize;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
public class SerializeTest {

    @Test
    public void test() {

        // StringRedisSerializer s = new StringRedisSerializer();
        // GenericJackson2JsonRedisSerializer

        // serialize StringRedisSerializer
        // deserialize GenericJackson2JsonRedisSerializer

        String s = "1234";
        System.out.println("origin: " + s);
        StringRedisSerializer str = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer json = new GenericJackson2JsonRedisSerializer();
        byte[] serialize = str.serialize(s);

        Object deserialize = json.deserialize(serialize);

        if (deserialize instanceof String) {
            System.out.println("String: " + deserialize);
        }

        if (deserialize instanceof Integer) {
            System.out.println("Integer: " + deserialize);
        }

    }

    @Test
    public void test2() {
        String s = "1234";
        System.out.println("origin: " + s);
        StringRedisSerializer str = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer json = new GenericJackson2JsonRedisSerializer();
        byte[] serialize = json.serialize(s);

        Object deserialize = json.deserialize(serialize);

        if (deserialize instanceof String) {
            System.out.println("String: " + deserialize);
        }

        if (deserialize instanceof Integer) {
            System.out.println("Integer: " + deserialize);
        }
    }
}
