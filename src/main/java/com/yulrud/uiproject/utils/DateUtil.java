package com.yulrud.uiproject.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.yulrud.uiproject.utils.TestLogger.log;

public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "d MMM yyyy";

    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDateFromText(String date) {
        Date actualDate = null;
        try {
            actualDate = new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            log(String.format("Not able to parse %s as date", date));
            e.printStackTrace();
        }
        return actualDate;
    }
}
