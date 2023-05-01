package com.tommy.creditloan.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private final static String DATE_FORMAT1 = "dd/MM/yyyy";

    public static String getFormatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT1);
        Calendar c = Calendar.getInstance();
        return sdf.format(date);
    }
}
