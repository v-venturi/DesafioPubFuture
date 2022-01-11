package com.vventuri.desafiopubfuture.utils;

import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static DateTimeFormatter getDateFormater(){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }
}