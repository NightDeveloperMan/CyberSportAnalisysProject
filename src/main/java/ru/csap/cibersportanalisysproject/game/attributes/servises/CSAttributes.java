package ru.csap.cibersportanalisysproject.game.attributes.servises;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class CSAttributes {

    public static HashMap<String, String> timeInterval(int month)
    {
        HashMap<String, String> timeInterval = new  HashMap<String, String>();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder todayDate = new StringBuilder(dateFormat.format(calendar.getTime()));
        try {
            calendar.setTime(dateFormat.parse(todayDate.toString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        calendar.add(Calendar.MONTH, -month);
        String halfYearOldDate = dateFormat.format(calendar.getTime());
        timeInterval.put("halfYearOldDate", halfYearOldDate);
        timeInterval.put("todayDate",todayDate.toString());
        return timeInterval;
    }
}
