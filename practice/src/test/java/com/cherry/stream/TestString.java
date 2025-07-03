package com.cherry.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestString {

    @Test
    public void test1() {

        List<String> s = new ArrayList<>();
        s.add("text");

        String join = String.join(" ", s);
        System.out.println(join);

    }

    @Test
    public void test2() {

        String string = UUID.randomUUID().toString();
        System.out.println(string);
        String replace = string.replace("-", "");
        System.out.println(replace);
    }
}
