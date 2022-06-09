package com.mf.crypto.common;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class TimestampStringGenerator {
    public String generate() {
        final LocalDateTime timestamp = LocalDateTime.now();

        final int date = timestamp.getDayOfMonth();
        final int month = timestamp.getMonthValue();
        final int year = timestamp.getYear();
        final int hour = timestamp.getHour();
        final int minute = timestamp.getMinute();
        final int second = timestamp.getSecond();

        final String dateValue = convertToString(date);
        final String monthValue = convertToString(month);
        final String yearValue = convertToString(year);
        final String hourValue = convertToString(hour);
        final String minuteValue = convertToString(minute);
        final String secondValue = convertToString(second);

        final String authIdPrefix = dateValue + monthValue + yearValue + hourValue + minuteValue + secondValue;
        return authIdPrefix;
    }

    private String convertToString(int numericValue) {
        String stringValue = String.valueOf(numericValue);
        stringValue = stringValue.length() == 1 ? "0" + stringValue : stringValue;
        return stringValue;
    }
}
