package com.start.test.go;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @description: java新时间类 LocalDate、LocalTime、LocalDateTime
 * @author: start
 * @create: 2019-10-28 15:19
 */
public class LocalTimeTest {

    public static void main(String[] args) {
        //SimpleDateFormat相比，DateTimeFormatter是线程安全的
        //LocalDate 年月日
        LocalDate localDate = LocalDate.now();
        System.out.println("\n============localDate===============");
        System.out.println("年月日\t" + localDate.format(DateTimeFormatter.ISO_DATE));
        System.out.println("年\t" + localDate.getYear());
        System.out.println("月\t" + localDate.getMonth());
        System.out.println("月\t" + localDate.getMonthValue());
        System.out.println("周天\t" + localDate.getDayOfWeek());
        System.out.println("月天\t" + localDate.getDayOfMonth());
        System.out.println("年天\t" + localDate.getDayOfYear());
        //从19700101开始算的
        //https://baijiahao.baidu.com/s?id=1611184991110524995&wfr=spider&for=pc
        System.out.println("时间纪元\t" + localDate.toEpochDay());
        //前后设置
        System.out.println("10天后\t" + localDate.plusDays(10));
        System.out.println("10天前\t" + localDate.minusDays(10));
        System.out.println("今年第123天\t" + localDate.withDayOfYear(123));
        System.out.println("是否闰年\t" + localDate.isLeapYear());
        System.out.println("今年第一天\t" + localDate.with(TemporalAdjusters.firstDayOfYear()));

        //LocalTime 时分秒
        LocalTime localTime = LocalTime.now();
        System.out.println("\n=====================LocalTime====================");
        System.out.println("时分秒\t" + localTime.format(DateTimeFormatter.ISO_TIME));
        System.out.println("时\t" + localTime.getHour());
        System.out.println("分\t" + localTime.getMinute());
        System.out.println("秒\t" + localTime.getSecond());
        System.out.println("纳秒\t" + localTime.getNano());
        System.out.println("一天转换成纳秒\t" + localTime.toNanoOfDay());
        System.out.println("一天转换成秒\t" + localTime.toSecondOfDay());

        //前后设置
        System.out.println("66小时后\t" + localTime.plusHours(66));
        System.out.println("66小时前\t" + localTime.minusHours(66));
        System.out.println("今天第2个小时\t" + localTime.withHour(2));


        //LocalDateTime 年月日时分秒
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("\n======================LocalDateTime===============");
        System.out.println("年月日时分秒\t" + localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("年\t" + localDateTime.getYear());
        System.out.println("月\t" + localDateTime.getMonth());
        System.out.println("月\t" + localDateTime.getMonthValue());
        System.out.println("年日\t" + localDateTime.getDayOfYear());
        System.out.println("月日\t" + localDateTime.getDayOfMonth());
        System.out.println("周日\t" + localDateTime.getDayOfWeek());
        System.out.println("时\t" + localDateTime.getHour());
        System.out.println("分\t" + localDateTime.getMinute());
        System.out.println("秒\t" + localDateTime.getSecond());
        System.out.println("纳秒\t" + localDateTime.getNano());
        //前后设置一样

        // Instant秒
        Instant instant = Instant.now();
        System.out.println("\n======================Instant===============");
        System.out.println("秒\t" + instant.getEpochSecond());
        System.out.println("纳秒\t" + instant.getNano());
        System.out.println("毫秒\t" + instant.toEpochMilli());
        System.out.println("毫秒\t" + System.currentTimeMillis());
        //前后设置一样


    }

}
