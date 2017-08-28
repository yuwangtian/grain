package com.grain.utils.pageutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StringUtil {
    private static String beforeOid = "";
    private static int beforeOrder = 0;
    private static final char specialSign[] = {'\''};
    private static String[] hanArr = {"〇", "一", "二", "三", "四", "五", "六", "七", "八",
            "九"};
    private static String[] unitArr = {"十", "百", "千", "万", "十", "百", "千", "亿", "十",
            "百", "千"};
    private static Date datetime = null;

    public static String dealString4JSON(String ors) {
        ors = ors == null ? "" : ors;
        StringBuffer buffer = new StringBuffer(ors);
        ///在替换的时候不要使用 String.replaceAll("\\","\\\\"); 这样不会达到替换的效果 因为这些符号有特殊的意义,在正则     ///表达式里要用到
        int i = 0;
        while (i < buffer.length()) {
            if (isConstanSpecialS(buffer.charAt(i))) {
                buffer.insert(i, '\\');
                i += 2;
            } else {
                i++;
            }
        }
        String tmpstr = buffer.toString().replaceAll("\r\n", "<br/>");
        return tmpstr.replaceAll("\n", "<br/>");
    }

    private static boolean isConstanSpecialS(char specialS) {
        boolean isContailns = false;
        for (char child : specialSign) {
            if (specialS == child) {
                return true;
            }
        }
        return isContailns;
    }

    public static Date formatTo(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            datetime = format.parse(date.toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return datetime;
    }


}
