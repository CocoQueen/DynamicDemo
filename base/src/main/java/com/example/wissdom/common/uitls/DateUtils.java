package com.example.wissdom.common.uitls;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期 工具类
 */
@SuppressWarnings("unused")
public class DateUtils {

    /**静态单例内部类**/
    private DateUtils() {
    }

    public static DateUtils getInstance() {
        return DateUtils2.t;
    }

    private static class DateUtils2 {
        private static DateUtils t = new DateUtils();
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public String date2String(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (StringUtils.isEmptyString(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转换成日期
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public Date string2Date(String dateString, String pattern) {
        Date date = null;
        if (!StringUtils.isEmptyString(dateString)) {
            if (StringUtils.isEmptyString(pattern)) {
                pattern = "yyyy-MM-dd HH:mm:ss";
            }
            try {
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 字符串转日期Calendar
     *
     * @param dateString
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public Calendar string2Calendar(String dateString, String pattern) {
        Calendar calendar = null;
        try {
            if (!StringUtils.isEmptyString(dateString)) {
                if (StringUtils.isEmptyString(pattern)) {
                    pattern = "yyyy-MM-dd HH:mm:ss";
                }
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                Date d = sdf.parse(dateString);
                calendar = Calendar.getInstance();
                calendar.setTime(d);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    /**
     * 将日期字符串转换成相应格式的日期字符串
     *
     * @param dateString
     * @param pattern1   pattern2
     * @return
     */
    public String string2Date2DateString(String dateString, String pattern1, String pattern2) {
        String dateString_temp = "";
        if (!StringUtils.isEmptyString(dateString) && !StringUtils.isEmptyString(pattern1)) {
            if (StringUtils.isEmptyString(pattern2)) {
                pattern2 = "yyyy-MM-dd HH:mm:ss";
            }
            try {
                dateString_temp = date2String(string2Date(dateString, pattern1), pattern2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateString_temp;
    }

    /**
     * 获得指定日期的前一天
     *
     * @param dateString
     * @param pattern
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public String getBeforeDay(String dateString, String pattern) {
        String dayBefore = "";
        if (!StringUtils.isEmptyString(dateString)) {
            try {
                Calendar c = Calendar.getInstance();
                if (StringUtils.isEmptyString(pattern)) {
                    pattern = "yyyy-MM-dd HH:mm:ss";
                }

                Date date = new SimpleDateFormat(pattern).parse(dateString);
                c.setTime(date);
                int day = c.get(Calendar.DATE);
                c.set(Calendar.DATE, day - 1);
                dayBefore = new SimpleDateFormat(pattern).format(c.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param dateString
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public String getAfterDay(String dateString, String pattern) {
        String dayBefore = "";
        if (!StringUtils.isEmptyString(dateString)) {
            try {
                Calendar c = Calendar.getInstance();
                if (StringUtils.isEmptyString(pattern)) {
                    pattern = "yyyy-MM-dd HH:mm:ss";
                }
                Date date = new SimpleDateFormat(pattern).parse(dateString);
                c.setTime(date);
                int day = c.get(Calendar.DATE);
                c.set(Calendar.DATE, day + 1);
                dayBefore = new SimpleDateFormat(pattern).format(c.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dayBefore;
    }

    /**
     * 获得指定日期的1号
     *
     * @param dateString
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public String getFirstOfMonth(String dateString, String pattern) {
        String dayBefore = "";
        if (!StringUtils.isEmptyString(dateString)) {
            try {
                Calendar c = Calendar.getInstance();
                if (StringUtils.isEmptyString(pattern)) {
                    pattern = "yyyy-MM-dd HH:mm:ss";
                }
                Date date = new SimpleDateFormat(pattern).parse(dateString);
                c.setTime(date);
                c.set(Calendar.DATE, 1);
                dayBefore = new SimpleDateFormat(pattern).format(c.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dayBefore;
    }

    /**
     * 获取当前日期的字符串格式
     *
     * @return
     */
    public String getCurrentDateString() {
        return date2String(new Date(), null);
    }

    /**
     * 获取当前日期的前一天
     *
     * @return
     */
    public String getCurrentBeforeDay() {
        try {
            return getBeforeDay(date2String(new Date(), null), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取当前日期的后一天
     *
     * @return
     */
    public String getCurrentAfterDay() {
        try {
            return getAfterDay(date2String(new Date(), null), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * if true || d1 接近现在 || d1在d2的后边
     *
     * @param d1
     * @param d2
     * @return
     */
    public boolean compareDate(Date d1, Date d2) {
        boolean flag = false;
        if (d1.compareTo(d2) > 0) {
            flag = true;
        }
        return flag;
    }
}
