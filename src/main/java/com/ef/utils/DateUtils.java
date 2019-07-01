package com.ef.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date addHoursToDate(Date startDate, String duration){
        int timeToAdd = 0;
        if (duration.equals(Duration.hourly)) {
            timeToAdd = 1;

        } else if (duration.equals(Duration.daily)){
            timeToAdd = 24;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, timeToAdd);
        return cal.getTime();
    }


    public static Date parseDate(String startDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startingTime = null;
        try {
            startingTime = sdf.parse(startDate);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return startingTime;
    }
}
