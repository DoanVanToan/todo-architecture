package com.toandoan.todoapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by doan.van.toan on 12/27/17.
 */

public class DateUtils {
    private final static String FORMAT_DATE_DD_MM_YYYY = "dd/MM/yyy";

    public static String getDateFormat(Date date){
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_DD_MM_YYYY);
        return dateFormat.format(date);
    }
}
