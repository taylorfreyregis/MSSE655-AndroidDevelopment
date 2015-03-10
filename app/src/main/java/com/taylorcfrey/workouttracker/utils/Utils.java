package com.taylorcfrey.workouttracker.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static GregorianCalendar parseSqlToCalendar(String sqlDateTime) {

        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date = null;
        GregorianCalendar calendar = null;
        try {
            date = format.parse(sqlDateTime);
            calendar = new GregorianCalendar();
            calendar.setTime(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return calendar;
    }

    public static String parseCalendarToSql(GregorianCalendar calendar) {

        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        return format.format(calendar.getTime());

    }
}
