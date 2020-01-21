package com.start.test.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author st
 * @date 2018/6/1 下午2:34
 */
public class DateTimeFormatUtils {


    private static final DateTimeFormatter DATE_TIME_FORMATTER01 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_TIME_FORMATTER02 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER03 = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String formatyyyymmdd() {
        return FastDateFormat.getInstance("yyyyMMdd").format(new Date());
    }

    public static String formatyyyymmdd(long mills) {
        return FastDateFormat.getInstance("yyyyMMdd").format(mills);
    }

    public static String formatyyyymmddhhmmss(long mills) {
        return FastDateFormat.getInstance("yyyyMMddHHmmss").format(mills);
    }

    public static String formatyyyymmdd02() {
        return FastDateFormat.getInstance("yyyy-MM-dd").format(new Date());
    }

    public static String formatyyyymmdd02(long mills) {
        return FastDateFormat.getInstance("yyyy-MM-dd").format(mills);
    }

    public static Date pasreyyyymmdd(String format) throws ParseException {
        return FastDateFormat.getInstance("yyyy-MM-dd").parse(format);
    }

    public static Date pasreyyyymmddhhmmss(String format) throws ParseException {
        return FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").parse(format);
    }

    public static String formatyyyymmddhhmmss(Long mills) {
        return FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(mills);
    }

    public static String formatyyyymmddhhmm(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }


    /**
     * java.util.Date --> java.time.LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * java.util.Date --> java.time.LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     * java.util.Date --> java.time.LocalTime
     */
    public static LocalTime dateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalTime();
    }


    /**
     * java.time.LocalDateTime --> java.util.Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * java.time.LocalDateTime --> java.util.Date
     */
    public static Date localDateTimeToUdate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static String localDateTimeFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER01);
    }

    public static String formatyyyymmdd02(LocalDate localDate) {
        return localDate.format(DATE_TIME_FORMATTER02);
    }

    public static String formatyyyymmdd(LocalDate localDate) {
        return localDate.format(DATE_TIME_FORMATTER03);
    }


    public static LocalDate parseyyyymmdd(String format) {
        return LocalDate.parse(format, DATE_TIME_FORMATTER03);
    }

    public static LocalDateTime pasreFormatToLocalDateTime(String format) {
        return LocalDateTime.parse(format, DATE_TIME_FORMATTER01);
    }

    /**
     * java.time.LocalDate --> java.util.Date
     */
    public static Date localDateToUdate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * java.time.LocalTime --> java.util.Date
     */
    public static Date localTimeToUdate(LocalDate localDate, LocalTime localTime) {
//        LocalTime localTime = LocalTime.now();
//        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 判断是否是今天
     */
    public static boolean isToday(LocalDateTime localDateTime) {
        final LocalDate localDate = LocalDateTime.now().toLocalDate();
        return localDate.isEqual(localDateTime.toLocalDate());
    }

    public static String timeRangeEndMin(LocalDateTime startTime, LocalDateTime endTime) {
        String reservationTimeEndMin = DateTimeFormatUtils.formatyyyymmddhhmm(startTime);
        int hour = endTime.getHour();
        int minute = endTime.getMinute();
        StringBuilder stringBuilder = new StringBuilder(5);
        StringBuilder append = stringBuilder.append(reservationTimeEndMin)
                .append("-")
                .append(String.format("%02d", hour))
                .append(":")
                .append(String.format("%02d", minute));
        return append.toString();
    }


    public static void main(String[] args) {
        System.out.println(formatyyyymmddhhmmss(new Date(0).getTime()));
    }
}
