package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Insurance {
    public static enum FormatStyle {SHORT, LONG, FULL}

    private ZonedDateTime start;
    private Duration duration;

    public Insurance(ZonedDateTime start) {
        this.start = start;
    }

    public Insurance(String strStart, FormatStyle style) {
        DateTimeFormatter dtf;
        switch (style) {
            case SHORT: dtf = DateTimeFormatter.ISO_LOCAL_DATE;
                LocalDate ld = LocalDate.from(dtf.parse(strStart));
                start = ld.atStartOfDay(ZoneId.systemDefault());
                break;
            case LONG: dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                LocalDateTime ldt = LocalDateTime.from(dtf.parse(strStart));
                start = ldt.atZone(ZoneId.systemDefault());
                break;
            case FULL: dtf = DateTimeFormatter.ISO_ZONED_DATE_TIME;
                start = ZonedDateTime.from(dtf.parse(strStart));
        }
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDuration(ZonedDateTime expiration) {
        duration = Duration.between(start, expiration);
    }

    public void setDuration(int months, int days, int hours) {
        Period p = Period.ofMonths(months);
        duration = Duration.ofDays(p.getDays() + days);
        duration = duration.plusHours(hours);
    }

    public void setDuration(String strDuration, FormatStyle style) {
        switch (style) {
            case SHORT: duration = Duration.ofMillis(Long.parseLong(strDuration));
                break;
            case LONG: DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                LocalDateTime ldt = LocalDateTime.from(dtf.parse(strDuration));
                duration = Duration.between(LocalDateTime.of(0,1,1,0,0,0),ldt);
                break;
            case FULL: duration = Duration.parse(strDuration);
        }
    }

    public boolean checkValid(ZonedDateTime dateTime) {
        if (dateTime.compareTo(start) >= 0) {
            if (duration == null) return true;
            else {
                Duration checkDur = Duration.between(start, dateTime);
                if (duration.compareTo(checkDur) >= 0) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime dateTime = ldt.atZone(ZoneId.systemDefault());
        String validStr;
        if (checkValid(dateTime)) validStr = " is valid";
        else validStr = " is not valid";
        return "Insurance issued on " + start + validStr;
    }

    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        ZonedDateTime zdt2 = ldt.atZone(ZoneId.systemDefault());
        System.out.println(zdt2);
        LocalDate ld = LocalDate.now();
        ZonedDateTime zdt1 = ld.atStartOfDay(ZoneId.systemDefault());
        System.out.println(zdt1);
        String str1 = "2020-06-19";
        String str2 = "2020-06-19T03:29:08.967321";
        String str3 = "2020-06-19T03:29:08.967321+03:00[Europe/Moscow]";
        Insurance insurance = new Insurance(str2, FormatStyle.LONG);
        insurance.setDuration("2020-06-19T03:29:05", FormatStyle.LONG);
        System.out.println(insurance.checkValid(zdt2));
        System.out.println(insurance.toString());
        LocalTime lt = LocalTime.now();
    }
}
