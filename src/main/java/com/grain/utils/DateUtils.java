package com.grain.utils;

import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yuchen
 * on 2015/7/9.
 */
public class DateUtils {
    /**
     *
     */
    public static final String MATRIX_NULL_FLAG = "/";
    public static final long MILSEC_PER_DAY = 24 * 3600000;


    public static String getLastDate(int year, int month) {
        int[] monthDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            monthDay[1] = 29;
        }
        int monthDayNum = monthDay[month - 1];
        String end = year + "-" + month + "-" + monthDayNum;
        return end;
    }

    /* 判断myDate是否为null */
    public static Date isDate(Date myDate) {
        if (myDate == null)
            return new Date();
        return myDate;
    }

    public static Date currentDate() {
        return currentDate(null);
    }

    /**
     * @return
     */
    public static Date currentDate(DateFormat format) {
        Date today = new Date();
        if (format != null) {
            String string = format.format(today);
            today = parse(string, format);
        }
        return today;
    }

    /**
     * 查询当天的前n天的具体时间
     *
     * @param n
     * @return
     */
    public static String getCurrentDateBefore(int n) {
        Calendar day = Calendar.getInstance();
        day.add(Calendar.DATE, -n);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(day.getTime());
        return result;
    }

    /**
     * 日期差(time1 - time2，返回负数，若time1在time2之前)
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long getQuot(Date time1, Date time2) {
        long quot = 0;
        try {
            Date date1 = time1;
            Date date2 = time2;
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quot;
    }


    // 判断日期为星期几,1为星期日com.vnvtrip.util,依此类推
    public static int dayOfWeek(Object date1) {
        Date date = (Date) date1;
        // 首先定义一个calendar，必须使用getInstance()进行实例化
        Calendar aCalendar = Calendar.getInstance();
        // 里面野可以直接插入date类型
        aCalendar.setTime(date);
        // 计算此日期是一周中的哪一天
        int x = aCalendar.get(Calendar.DAY_OF_WEEK);
        return x;
    }

    public static String dayOfWeek2(Object date1) {
        Date date = (Date) date1;
        // 首先定义一个calendar，必须使用getInstance()进行实例化
        Calendar aCalendar = Calendar.getInstance();
        // 里面野可以直接插入date类型
        aCalendar.setTime(date);
        // 计算此日期是一周中的哪一天
        int x = aCalendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeekByDayNum(x);
    }

    public static String dayOfWeekByDayNum(int x) {
        String str = "周日";
        if (x == 7) {
            str = "周六";
        } else if (x == 6) {
            str = "周五";
        } else if (x == 5) {
            str = "周四";
        } else if (x == 4) {
            str = "周三";
        } else if (x == 3) {
            str = "周二";
        } else if (x == 2) {
            str = "周一";
        }
        return str;
    }

    // 根据当前一个星期中的第几天得到它的日期
    public static Date getDateOfCurrentWeek(int day) {
        Calendar aCalendar = Calendar.getInstance();
        int x = aCalendar.get(Calendar.DAY_OF_WEEK);
        aCalendar.add(Calendar.DAY_OF_WEEK, day - (x - 1));
        return aCalendar.getTime();
    }

    // 某一天在一个月中的第几周
    public static int getWeekOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    public static Date addSomeDay(Date oldDate, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }

    // 把日期“2006-09-01”转化成20060901
    public static String Dateformat(String date) {
        int i = date.length();
        StringBuffer newdate = new StringBuffer(date.substring(0, 4));
        if (i == 8) {
            newdate.append(0);
            newdate.append(date.substring(5, 6));
            newdate.append(0);
            newdate.append(date.substring(7, 8));
        } else if (i == 9) {
            if (date.substring(7, 8).equalsIgnoreCase("-")) {
                newdate.append(date.substring(5, 7));
                newdate.append(0);
                newdate.append(date.substring(8, 9));
            } else {
                newdate.append(0);
                newdate.append(date.substring(5, 6));
                newdate.append(date.substring(7, 9));
            }
        } else {
            newdate.append(date.substring(5, 7));
            newdate.append(date.substring(8, 10));
        }
        return newdate.toString();
    }


    /* 将字符串转换成日期 */
    public static Date getDateByString(String rq) {
        DateFormat df = new SimpleDateFormat();
        Date d = null;
        try {
            d = df.parse(rq);
        } catch (Exception e) {
        }
        return d;
    }

    public static Date getDateByString(String str, String pattern) {
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat(pattern);
            return sdf.parse(str);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 比较时间是否相同
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean equals(Date start, Date end) {
        if (start != null && end != null && start.getTime() == end.getTime()) {
            return true;
        }
        return false;
    }

    public static final Date convertStringToDate(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
        try {
            date = df.parse(strDate);
        } catch (Exception pe) {
        }
        return (date);
    }

    /**
     * 当前月份第一天
     *
     * @return
     */
    public static Date getCurrentMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Calendar string2Cal(String arg) {
        SimpleDateFormat sdf = null;
        String trimString = arg.trim();
        if (trimString.length() > 14)
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        else
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(trimString);
        } catch (ParseException e) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal;
    }

    /**
     * 匹配是否在某个时间段中
     *
     * @param timePeriod "00:00-06:00"格式
     * @param time       "07:30"
     * @return
     */
    public static boolean isInPeriod(String timePeriod, String time) {
        String startTime = timePeriod.substring(0, 5);
        String endTime = timePeriod.substring(6);
        String timeTime = time;
        // 和时间段的开始或者结束刚好相等的时候
        if (startTime.equalsIgnoreCase(timeTime)
                || endTime.equalsIgnoreCase(timeTime)) {
            return true;
        }
        SimpleDateFormat d = new SimpleDateFormat("HH:mm");
        try {
            Date startDate = d.parse(startTime);
            Date endDate = d.parse(endTime);
            Date timeDate = d.parse(timeTime);
            if (timeDate.after(startDate) && timeDate.before(endDate)) {
                return true;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isBetween(Date date, Date from, Date to) {
        Assert.notNull(date, "date cannot be null.");
        Assert.notNull(from, "from cannot be null.");
        Assert.notNull(to, "to cannot be null.");
        Assert.isTrue(!from.after(to), "from cannot be after to.");
        return !date.before(from) && !date.after(to);
    }

    public static Date ifNull(Date date, Date defaultDate) {
        return date != null ? date : defaultDate;
    }


    public static Date parseUseDefaultFormat(String date) {
        return parse(date, getDayFormatter());
    }

    public static Date parse(String date, DateFormat df) {
        try {
            return df.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("parse date [" + date
                    + "] failed in use [" + getDayFormatter() + "]", e);
        }
    }

    // 增加或减少几个月
    public static Date addMonth(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.MONTH, num);
        return startDT.getTime();
    }

    // 增加或减少天数
    public static Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }


    /**
     * <li>SimpleDateFormat is not thread saft, so when you need, you should
     * create it</li>
     */
    public static SimpleDateFormat getDayFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static SimpleDateFormat getMinuteFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public static SimpleDateFormat getMonthFormatter() {
        return new SimpleDateFormat("yyyy-MM");
    }

    public static SimpleDateFormat getSecondFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 字符串格式时间转换到对象时间
     *
     * @param str
     * @return
     */
    public static Date string2DateTime(String str) {
        SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = fo.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static long diff(Date d1, Date d2, String field) {
        long d1t = d1.getTime();
        long d2t = d2.getTime();
        if ("middleNight".equalsIgnoreCase(field)) { // 计算间夜，先除后减
            return d1t / MILSEC_PER_DAY - d2t / MILSEC_PER_DAY;
        } else {
            return d2t - d1t;
        }
    }


    /**
     * 星期几
     *
     * @param date
     * @return
     */
    public static int getWeekByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 将某个日期转换成业务逻辑上面的星期几 calendar: 周一：2；周二：3；周三：4；周四：5；周五：6；周六：7；周日：1 业务逻辑 ：
     * 周一：1；周二：2；周三：3；周四：4；周五：5；周六：6；周日：7
     *
     * @param date
     * @return
     */
    public static String explainDayOfWeek(Date date) {
        int departDay = dayOfWeek(date);
        if (departDay == 1) {
            return "7";
        } else if (departDay == 2) {
            return "1";
        } else {
            return String.valueOf(departDay - 1);
        }
    }

    /**
     * 比较 是否当前日子以前的日子(不包含当前天)
     */
    public static boolean gtAfter(Date date) {
        return date.before(addDay(new Date(), -1));
    }

    /**
     * 获得当前日期字符串yyyy-mm-dd
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(new Date());
    }


    /**
     * 获得今日的毫秒数
     *
     * @return
     */
    public static Long getCurrentDateMillis() {

        return getDateMillis(getCurrentDate());
    }

    /**
     * 获得指定日的毫秒
     *
     * @param d 日期串格式yyyy-MM-dd
     * @return
     */
    public static Long getDateMillis(String d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = sdf.parse(d);

            return date.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return 数据格式:2014-14-19 12:30:30
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    /**
     * @return 数据格式:2014-14-19
     */
    public static String getDateString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        return dateFormat.format(date);
    }

    /**
     * 获取指定日期的季节
     *
     * @param date
     * @return
     * @Description:
     */
    public static String getSeaSon(Date date) {
        String[] monSeaSon = {"冬", "冬", "春", "春", "春", "夏", "夏", "夏", "秋",
                "秋", "秋", "冬"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return monSeaSon[calendar.get(Calendar.MONTH)];
    }

    /**
     * 获得当前年份yyyy
     *
     * @return
     */
    public static String getCurrentYear() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }




    /**
     * 获得指定日期上周的周五日期
     *
     * @return
     * @throws ParseException
     */
    public static Long getFriday(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Calendar cal = Calendar.getInstance(Locale.CHINA);

        cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.WEEK_OF_MONTH, -1);//周数减一，即上周
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);//日子设为星期五
        Date rdate = cal.getTime();
        try {
            //将时间转成没有时分秒的格式的字符串然后再转成时间,取毫秒
            return sf.parse(sf.format(rdate)).getTime();
        } catch (ParseException e) {
            throw new RuntimeException("parse date [" + rdate
                    + "] failed in use [getFriday(Date " + date + ")]", e);
        }
    }

    /**
     * 获得当前半小时的long
     *
     * @return long
     */
    public static long gethalfHour() {
        Date date = new Date();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        long times = 0;
        String dateStr = df1.format(date);
        String hours = dateStr.split(":")[1];
        int tempMin = Integer.parseInt(hours);
        if (tempMin > 30) {
            times = 1800000;
        }
        String dateStr2 = df2.format(date);
        try {
            date = df2.parse(dateStr2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() + times;
    }

    //计算天数
    public static int getDay(String hisDateStr) {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        int result = -1;
        Date currentDate = new Date();

        hisDateStr = hisDateStr.substring(0, 10);
        Date endDate;
        try {
            endDate = DATE_FORMAT.parse(hisDateStr);
            result = (int) ((getMillis(currentDate) - getMillis(endDate)) / (24 * 3600 * 1000));
        } catch (ParseException e) {
            result = -1;
            e.printStackTrace();
        }
        return result;
    }

    //获取秒数
    public static long getMillis(java.util.Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }


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


    // 获得天所在月天数列表
    public static List<String> getDaysOfWeek(Date date) {
        List<String> days = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        //当前月
        int month = (calendar.get(Calendar.MONTH)) + 1;
        //当前月的第几天：即当前日
        int day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= day_of_month; i++) {
            calendar.set(Calendar.MONTH, i);
            if (month < 10 && i < 10) {
                days.add(year + "-0" + month + "-0" + i);
            } else if (month < 10 && i >= 10) {
                days.add(year + "-0" + month + "-" + i);
            } else if (month > 10 && i < 10) {
                days.add(year + "-" + month + "-0" + i);
            } else {
                days.add(year + "-" + month + "-" + i);
            }

        }
        return days;
    }


    /**
     * 返回两个日期相差的天数,有一个时间为null返回-1
     *
     * @param d1 长的时间
     * @param d2 短的时间
     * @return int
     */
    public static int diff_in_date(Date d1, Date d2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d1);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return (int) ((calendar.getTime().getTime() - d2.getTime()) / MILSEC_PER_DAY);
    }

    /**
     * 获取本周周几
     * @return
     */
    public static Date getDayOfWeek(int dayOfWeek) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd", Locale.CHINA);
        Calendar calendar= Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        String day=simpleDateFormat.format(calendar.getTime());
        return string2DateTime(day);
    }





    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static Date getPastDate(Date date,int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
         date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(date);
        return string2DateTime(result);

    }

    /**
     * 获取未来 第 past 天的日期
     * @param past
     * @return
     */
    public static Date getFetureDate(Date date,int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = calendar.getTime();
        String result = format.format(date);
        return string2DateTime(result);
    }

    /**
     * 星期几
     * @param day
     * @return
     */
    public static int getDayOfWeek(Date day){
        Calendar c=Calendar.getInstance();
        c.setTime(day);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
        System.out.println(weekday);
        return weekday;
    }
    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//        String str = "2015-04-03";
//        Date d = sf.parse(str);
        //System.out.println(d.getTime());.
        System.out.println(getFetureDate(new Date(),0));

    }

}
