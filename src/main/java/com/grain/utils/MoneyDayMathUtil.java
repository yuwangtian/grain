package com.grain.utils;

/**
 * Created by hujinbo on 2015/8/22.
 */
public class MoneyDayMathUtil {

    public static String plusMoney(String money1, String money2) {
        String result = null;
        if (money1 == null || "".equals(money1)) return money2;
        if (money2 == null || "".equals(money2)) return money1;
        double dMoney = Double.parseDouble(money1) + Double.parseDouble(money2);
        result = new Double(dMoney).toString();
        return result;
    }

    public static String minusMoney(String money1, String money2) {
        String result = null;
        if (money1 == null || "".equals(money1)) money1 = "0";
        if (money2 == null || "".equals(money2)) money2 = "0";
        double dMoney = Double.parseDouble(money1) - Double.parseDouble(money2);
        result = new Double(dMoney).toString();
        return result;
    }

    public static Integer plusDays(Integer days1, Integer days2) {
        Integer result = null;
        if (days1 == null) return days2;
        if (days2 == null) return days1;
        int dDays = days1.intValue() + days2.intValue();
        result = new Integer(dDays);
        return result;
    }

    public static Integer minusDays(Integer days1, Integer days2) {
        Integer result = null;
        if (days1 == null) days1 = new Integer(0);
        if (days2 == null) days2 = new Integer(0);
        int dDays = days1.intValue() - days2.intValue();
        result = new Integer(dDays);
        return result;
    }

    public static String multiplyMoneyByDays(String priceOfDay, Integer days) {
        String result = "0.0";
        if (priceOfDay == null || "".equals(priceOfDay) || days == null || days == 0) {
            return result;
        }
        result = "" + (Double.parseDouble(priceOfDay) * days.intValue());
        return result;
    }

    public static String divideMoneyByDays(String money, Integer days) {
        String result = null;
        if (days == null || days == 0) return result;
        if (money == null) money = "0.0";
        result = "" + (Double.parseDouble(money) / days.intValue());
        return result;
    }

    public static String getServicedDaysPerDay(String servicedDays, Integer days) {
        String result = null;
        if (days == null || days == 0) return result;
        if (servicedDays == null) servicedDays = "0";
        result = "" + (Double.parseDouble(servicedDays.toString()) / Double.parseDouble(days.toString()));
        return result;
    }

    public static Integer divideMoneyByPrice(String money, String price) {
        if (money == null || "".equals(money)) return 0;
        if (price == null || "".equals(price)) return null;
        if (Double.parseDouble(price) < 0.001) return null;
        int result = (int) Math.round(Double.parseDouble(money) / Double.parseDouble(price));
        return new Integer(result);
    }

    public static String getPriceOfMonth(String money, Integer days) {
        String priceOfMoney = "";
        if (money == null || "".equals(money)) return priceOfMoney;
        if (days == null || days == 0) return priceOfMoney;
        double price = Double.parseDouble(money) / days.intValue() * 30;
        priceOfMoney = new Double(price).toString();
        return priceOfMoney;
    }

    public static boolean isZeroOrEmptyMoney(String moneyValue) {
        if (moneyValue == null || "".equals(moneyValue.trim())) {
            return true;
        } else {
            double dMoneyValue = Double.parseDouble(moneyValue);
            if (dMoneyValue == 0.0) {
                return true;
            }
        }
        return false;
    }
}
