package com.tom.parking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Tester {
    public static void main(String[] args) {

        LocalDateTime enter =
                LocalDateTime.of(2018, 12, 25, 8, 0, 0);

        LocalDateTime leave =
                LocalDateTime.of(2018, 12, 25, 10, 0, 0);

        Car car = new Car("AA-0001", enter
//                enter.atZone( // 設定時區
//                        ZoneId.systemDefault()) // 使用系統預設時間
//                        .toEpochSecond()
        ); // 取得 long 值

        car.setLeave(leave);
        System.out.println(car.getDuration());

        long hours = (long) Math.ceil(car.getDuration() / 60.0);
        System.out.println(hours);
        System.out.println(30 * hours);

        // java8
//        java8();
//        java();
    }

    private static void java8() {
        Instant instant = Instant.now();
        System.out.println(instant); // 具有時區的概念，會產生格林威治的時間
        // local
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); // 用法一樣
        System.out.println(formatter.format(now));
        now.plus(Duration.ofHours(3)); // 加時間，要注意它是 Immutable 物件，所以吐出來的是新的物件
        LocalDateTime other = LocalDateTime.of(2018, 11, 23, 8, 0, 0);
        System.out.println(other);
    }

    private static void java() {
        // Date 內部使用 long 來儲存 1970/1/1 00:00:00 至今的毫秒數
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());

        // https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(sdf.format(date));
        String s = "2018/11/25 08:00:00";
        Date other = null;
        try {
            other = sdf.parse(s);
            System.out.println(other);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 能做時間或日期的運算
        Calendar calendar = Calendar.getInstance(); // Calendar 是 Mutable 物件
//        System.out.println(calendar); // 印出物件的資訊，而不是時間格式
        System.out.println(calendar.getTime());

        calendar.set(Calendar.MONTH, 9); // 月份是從 0 開始
        System.out.println(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        System.out.println(calendar.getTime());
    }
}
