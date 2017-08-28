package com.grain.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomId {
    private static Integer tmpID = 0;

    private static boolean tmpIDlocked = false;

    public static Integer getUniqueId() {
        Integer ltime = 0;
        while (true) {
            if (tmpIDlocked == false) {
                tmpIDlocked = true;
                ltime = Integer.parseInt(new SimpleDateFormat("yyMMddhhmmssSSS")
                        .format(new Date()).toString()) * 10000;
                if (tmpID < ltime) {
                    tmpID = ltime;
                } else {
                    tmpID = tmpID + 1;
                    ltime = tmpID;
                }
                tmpIDlocked = false;
                return ltime;
            }
        }
    }
}