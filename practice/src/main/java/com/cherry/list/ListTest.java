package com.cherry.list;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
//        List<String> l = new ArrayList<>();
//        l.add("1");
//        l.add("2");
//        l.add("3");
//        l.add("4");
//
//        List<String> list = l.subList(0, 1);
//        System.out.println(list);
        List<String> s = new ArrayList<>();
        s.add("1");

        s.add(0, "2");
        System.out.println(s);
    }

}
