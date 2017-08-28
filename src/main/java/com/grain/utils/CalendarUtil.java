package com.grain.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarUtil {

    //下月第一天
    public static Date getNextMonth(Date beginTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(beginTime);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    //当天几号
    public static int getMonthOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    //当月有多少天
    public static int getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.getActualMaximum(Calendar.DATE);
        return month;
    }

    //当前月 -1
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        return month;
    }

    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year;
    }

    //当月第一天
    public static Date getFirstDayOfMonth(Date beginTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(beginTime);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    //获得当周第几天  i=0表示星期一
    public static String getNowWeekBegin(Date date, int i) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.DAY_OF_WEEK, Calendar.MONTH + i);
        if (i == 6) {
            cd.set(Calendar.DAY_OF_WEEK, Calendar.MONTH + 5);
            cd.add(cd.DATE, 1);
        }
        Date monday = cd.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }

    //获得当周第几天，返回日期形。i=0表示星期一
    public static Date getNowWeekBeginDate(Date date, int i) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.DAY_OF_WEEK, Calendar.MONTH + i);
        if (i == 6) {
            cd.set(Calendar.DAY_OF_WEEK, Calendar.MONTH + 5);
            cd.add(cd.DATE, 1);
        }
        Date monday = cd.getTime();
        return monday;
    }

    //获得本周日期
    public static List<String> getDaysOfWeek(Date date) {
        List<String> days = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            days.add(getNowWeekBegin(date, i));
        }
        return days;
    }

    //获得本周日期
    public static List<Date> getDaysOfWeekDate(Date date) {
        List<Date> days = new ArrayList<Date>();
        for (int i = 0; i < 7; i++) {
            days.add(getNowWeekBeginDate(date, i));
        }
        return days;
    }

    /**
     * 获得当月第几周
     *
     * @param date
     * @param week
     * @return
     */
    public static List<String> getWeeksOfMonth(Date date, int week) {
        int day = 7 * week + 1;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, day);
        return getDaysOfWeek(c.getTime());
    }

    //本月最后一天
    public static Date getDateOfLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
        return c.getTime();
    }

    //获得本月第几天,0表示第一天
    public static Date getDayOfMonth(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, i + 1);
        return c.getTime();
    }

    //本月共几周
    public static int getWeekyofMonth(Date date) {
        Date d1 = getFirstDayOfMonth(date);//第一天
        Date d2 = getDateOfLastDayOfMonth(date);//最后一天
        Date mon1 = getNowWeekBeginDate(d1, 0);//第一天所在周星期一
        Date mon2 = getNowWeekBeginDate(d2, 0);//最后一天所在周星期一
        return (int) ((mon2.getTime() - mon1.getTime()) / 3600 / 24 / 1000) / 7;
    }

    public static List<String> getWeekName() {
        List<String> list = new ArrayList<String>();
        list.add("周一");
        list.add("周二");
        list.add("周三");
        list.add("周四");
        list.add("周五");
        list.add("周六");
        list.add("周日");
        return list;

    }

    /**
     * 首先获得 定一个时间，就2014-07-14为单周
     * 然后获得传过来日期的星期一与这个时间除以7.
     * 然后结果除以2，如果余数是0，这是单周，否则是双周
     *
     * @param date
     * @return
     */
    public static String getDanShuangWeek(Date date) {
        Date beginDate = StringToDate("2014-07-14");
        Date nowDate = getNowWeekBeginDate(date, 0);
        int temp = (int) ((nowDate.getTime() - beginDate.getTime()) / 3600 / 24 / 1000);
        int danshuang = temp % 2;
        String str = null;
        if (danshuang == 0) {
            str = "单周";
        } else {
            str = "双周";
        }
        return str;
    }

    public static int getDanShuangWeekInt(Date date) {
        Date beginDate = StringToDate("2014-07-14");

        Date nowDate = getNowWeekBeginDate(date, 0);
        int temp = (int) ((nowDate.getTime() - beginDate.getTime()) / 3600 / 24 / 1000);
        int danshuang = temp % 2;
        return danshuang;
    }

    /**
     * 把字符串类型日期转化为时间格式
     *
     * @param strDate
     * @return
     */
    public static Date StringToDate(String strDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 把日期类型转化为String类型 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = null;
        dateStr = df.format(date);
        return dateStr;
    }

    public static Date DateToDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = df.format(date);
        return StringToDate(strDate);
    }

    public static long DateSubtractDate(Date endTime, Date beginTime) {
        long dayCount = 0;
        long beginTemp = beginTime.getTime();
        long endTemp = endTime.getTime();
        long dayTemp = endTemp - beginTemp;
        dayCount = dayTemp / 3600 / 24 / 1000;
        return dayCount;
    }

    /**
     * @param date
     * @param i
     * @return
     */
    public static Date getDay(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, i);
        return c.getTime();
    }

    /**
     * 根据日期获得日期列表
     *
     * @param beginDate
     */
    public static List<String> getStrDate(Date beginDate, Date endDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<String>();
        long time1 = endDate.getTime();
        long time2 = beginDate.getTime();
        int a = Math.round((time1 - time2) / 1000 / 3600 / 24);
        for (int i = 0; i <= a; i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(beginDate);
            c.add(Calendar.DATE, i);
            list.add(df.format(c.getTime()));
        }
        return list;
    }

    public static String getMonthOfDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        return df.format(date);
    }

    /**
     * 根据指定参数  获得再过多少天是几月几号
     *
     * @return
     */
    public static Date getDatePlusNum(Integer rest_service_days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, rest_service_days);
        return cal.getTime();
    }

    public static Date getDatePlusDays(Date baseDate, Integer days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(baseDate);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static String datetimeToString(Date date) {
        if (date == null) return null;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr = null;
        dateStr = df.format(date);
        return dateStr;
    }

    public static Date stringToDatetime(String strDate) {
        if (strDate == null || "".equals(strDate.trim())) return null;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
//     SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//     System.out.println( sf.format(getDatePlusNum(83)));
        Date date1 = getDay(new Date(), -9);
        //System.out.println(getStrDate(date1,new Date()));
        //System.out.println(getMonthOfDay(new Date()));
        // SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        //Date date1 = StringToDate("2014-09-15");
        //	//System.out.println(getDanShuangWeek(StringToDate("2014-08-02"))); ;
        // //System.out.println(getDayOfMonth(new Date(),10));
//	 //System.out.println(DateSubtractDate(DateToDate(new Date()),date1));
//     getDatePlusNum(10);
    }
}
