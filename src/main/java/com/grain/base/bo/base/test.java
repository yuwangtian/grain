package com.grain.base.bo.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class test {
    public static void main(String[] args) {
        Map<Integer, String> infoMap = new HashMap<Integer, String>();
        infoMap.put(1, "a");   //a 替换成相应的问题
        infoMap.put(2, "b");
        infoMap.put(3, "c");
        infoMap.put(4, "f");
        infoMap.put(5, "j");
        infoMap.put(6, "d");
        infoMap.put(7, "k");
        infoMap.put(8, "r");
        Random r = new Random();
        int i = r.nextInt(8) + 1;
        //System.out.println("您随机的问题是:"+infoMap.get(i));
    }
}
