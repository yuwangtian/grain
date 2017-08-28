package com.grain.utils;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2014/6/16.
 */
public class CommonUtil {
    private static Logger logger = Logger.getLogger(CommonUtil.class);

    /**
     * 少于100 次
     * 3,325 次
     *
     * @param s
     * @return
     */
    public static int extractInt(String s) {
        int val = 0;
        if (s != null && !s.trim().equals("")) {
            String regEx = "((\\d{1,3},)?(\\d{3},)*\\d{3})|(\\d+)";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(s);
            boolean rs = matcher.find();
            if (rs) {
                String result = matcher.group();
                result = result.replace(",", "");
                val = Integer.parseInt(result);
            }
        }
        return val;
    }

    /**
     * 9,746次/30天
     * 1.0万次/30天
     *
     * @param s
     * @return
     */
    public static int extractBigInt(String s) {
        int val = 0;
        if (s != null && !s.trim().equals("")) {
            String regEx = "(((\\d{1,3},)?(\\d{3},)*\\d{3})|(\\d+))(\\.\\d+\\u4e07)?";
            logger.debug("regex:" + regEx);
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(s);
            boolean rs = matcher.find();
            if (rs) {
                String result = matcher.group();
                logger.debug("match result:" + result);
                result = result.replace(",", "");
                if (result.contains("万")) {
                    result = result.replace("万", "");
                    val = Math.round(Float.parseFloat(result) * 10000);
                } else {
                    val = Integer.parseInt(result);
                }
            }
        }
        return val;
    }

    /**
     * 20 元/月
     *
     * @param s
     * @return
     */
    public static double extractDouble(String s) {
        double val = 0;
        if (s != null && !s.trim().equals("")) {
            String regEx = "(((\\d{1,3},)?(\\d{3},)*\\d{3})|(\\d+))(\\.\\d+)?";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(s);
            boolean rs = matcher.find();
            if (rs) {
                String result = matcher.group();
                result = result.replace(",", "");
                val = Double.parseDouble(result);
            }
        }
        return val;
    }

    public static int extractMonth(String s) {
        int val = 0;
        if (s != null && !s.trim().equals("")) {
            val = extractInt(s);
            if (s.matches("^\\d+.*\\u6708")) {
                val = val;
            } else if (s.matches("^\\d+.*\\u5E74")) {
                val = val * 12;
            } else {
                val = 0;
            }
        }
        return val;
    }

    public static String getHtmlTagTitle(String s) {
        if (s == null) return null;
        return s.replaceAll("<(\\S+?)[^>]*(\\s*title\\s*=\\s*\"\\s*(\\S*)\\s*\"\\s*)[^>]*>(.*?)</\\1\\s*>", "$3");
    }

    /**
     * wzy
     * 2014-08-11 10:31:39
     * 去掉项目名称 和.do后面的参数
     *
     * @param url          访问的路径
     * @param projectCount 项目名称的长度
     * @param postfix      路径的后缀名
     * @return
     */
    public static String changeUrl(String url, int projectCount, String postfix) {
        String str1 = url.split(postfix)[0];
        return str1.substring(projectCount) + postfix;
    }
}