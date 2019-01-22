package com.pgkk.common.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tanxueze on 2017/12/12.
 */

public class DateUtils {
    public static Calendar CALENDAR = Calendar.getInstance();

    /**
     * model info 类专用 format = yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTime() {
        return getNow("yyyyMMddHHmmss");
    }

    /**
     * 获取当前时间,format = yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNow() {
        return getNow("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据指定的format格式获取当前时间
     *
     * @param format
     * @return
     */
    public static String getNow(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * 根据指定的format格式获取指定时间
     *
     * @param milliseconds 毫秒数
     * @param format 格式
     * @return
     */
    public static String getCustomFormat(long milliseconds, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date(milliseconds);
        return dateFormat.format(date);
    }


    /**
     * 获取当前系统时间的字符串
     *
     * @return
     */
    public static String getDateTime() {
        String str = null;
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        str = sDateFormat.format(new Date(System.currentTimeMillis()));
        return str;
    }

    /**
     *
     */
    @SuppressLint("SimpleDateFormat")
    @SuppressWarnings("deprecation")
    public static String analysisDate(Long millis) {
        String pattern = "MM/dd/yy HH:mm";
        Date date = new Date(millis);
        if (date.getDate() == new Date().getDate())
            pattern = "HH:mm";
        return new SimpleDateFormat(pattern).format(date);
    }
    /**
     * 格式化时间
     *
     * @param timeMillis
     * @return yyyy-MM-dd
     */
    public static String formatDataToYMD(long timeMillis) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CALENDAR.setTimeInMillis(timeMillis);
        return formatter.format(CALENDAR.getTime());
    }

    public static String formatTime2(long showTime){
        StringBuilder sb = new StringBuilder();
        long min = showTime / 60;
        long second = showTime % 60;
        sb.append(min < 10?"0"+min:min)
                .append("'")
                .append(second < 10?"0"+second:second)
                .append("\"");
        return sb.toString();
    }

    public static String formatDate2String(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(time == null){
            return "未知";
        }
        try {
            long createTime = format.parse(time).getTime() / 1000;
            long currentTime = System.currentTimeMillis() / 1000;
            if (currentTime - createTime - 24 * 3600 > 0) { //超出一天
                return (currentTime - createTime) / (24 * 3600) + "天前";
            } else {
                return (currentTime - createTime) / 3600 + "小时前";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "未知";
    }
}
