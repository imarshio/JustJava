package com.marshio.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author masuo
 * @arithmetics 日期
 * @date 2021-5-29 10:53:42
 * @description
 */
public class DateTest {

    public static void main(String[] args) {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
            try {
                Date fromDate2 = sdf.parse("2018-03-01 14:00");
                Date toDate2 = sdf.parse("2018-03-01 12:00");
                long from2 = fromDate2.getTime();
                long to2 = toDate2.getTime();
                int hours = (int) ((to2 - from2) / (1000 * 60 * 60));
                System.out.println("" + hours);

                date = sdf.parse(sdf.format(date));
                System.out.println("date:" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(date);
            calendar.set(Calendar.YEAR, 2022);
            calendar.set(Calendar.HOUR_OF_DAY, 20);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            date = calendar.getTime();
            System.out.println(date);
            int yearString = calendar.get(Calendar.YEAR);
            String string = yearString + "";
//			System.out.println(string.getClass());
            String yString = yearString + "-01-07";
//			System.out.println(yString);
            Date data = sdfDateFormat.parse(yString);
            Date data1 = sdfDateFormat.parse("2021-04-07");
//			System.out.println(data1);
//			System.out.println(data);
            System.out.println(data1.compareTo(data) != -1);
        } catch (ParseException e) {
            //
            e.printStackTrace();
        }
    }
}
