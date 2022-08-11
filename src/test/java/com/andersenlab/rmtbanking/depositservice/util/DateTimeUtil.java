package com.andersenlab.rmtbanking.depositservice.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    static DateTimeFormatter getDefaultFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    static DateTimeFormatter getCustomFormatter(String string) {
        return DateTimeFormatter.ofPattern(string);
    }

    static LocalDateTime getDateTime(String time, DateTimeFormatter formatter){
       return LocalDateTime.parse(time, formatter);
    }
}
