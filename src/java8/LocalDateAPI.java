package java8;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * @author masuo
 * @data 27/12/2021 下午4:47
 * @Description java8 新特性之 LocalDate API
 * Java.util 包提供了Date（）类来封装当前日期时间。
 */

public class LocalDateAPI {

    //Date 时间类
    @Test
    public void DateTest() {
        //Date提供两个构造方法，无参
        Date date1 = new Date();
        //有参，参数为从 1970 年 1 月 1 日 08:00:00(24小时制)起的毫秒数。
        Date date2 = new Date(0);

        Date date3 = new Date(System.currentTimeMillis());

        /*
         * toString()格式: dow mon dd hh:mm:ss zzz yyyy
         *  dow 是一周中的某一天 (Sun, Mon, Tue, Wed, Thu, Fri, Sat)
         * */
        System.out.println(date1);
        //Tue Jan 04 13:04:37 CST 2022
        System.out.println(date2);
        //Thu Jan 01 08:00:00 CST 1970
        System.out.println(date3);
        //Tue Jan 04 13:04:37 CST 2022

        //获取时间的时间戳
        System.out.println(date1.getTime());
        //获取年月日，时分秒,在Date中的这些方法都被标记为过时方法了
        System.out.println(date1.getYear());
        System.out.println(date1.getMonth());
        System.out.println(date1.getDay());
        System.out.println(date1.getHours());
        System.out.println(date1.getMinutes());
        System.out.println(date1.getSeconds());
        /*
         * 时间比较，before，after，compareTo
         * 需要一个Date参数
         * */
        //before()在一个时间（传入的参数）之前，则返回true
        System.out.println(date1.before(date2));
        //after()在一个时间（传入的参数）之后，则返回true
        System.out.println(date1.after(date2));
        //compareTo(),和一个时间比较，相等返回0，在一个时间（传入的参数）之前则返回-1，否则返回1，
        // 其原理是：(thisTime<anotherTime ? -1 : (thisTime==anotherTime ? 0 : 1))
        System.out.println(date1.compareTo(date2));

        //当然大多数情况下，我们想要的是 yyyy-MM-dd HH:mm:ss 这种模式,此时我们可以使用时间格式化转换器SimpleDateFormat
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /*
         * 将Date转换成String
         * */
        //时间格式化转换器，支持将Date类型转换成你想要的格式的String类型,其中使用了日历类
        String data1f = format.format(date1);
        String data2f = format.format(date2);
        String data3f = format.format(date3);

        System.out.println(data1f);
        //2022-01-04 13:11:38
        System.out.println(data2f);
        //1970-01-01 08:00:00
        System.out.println(data3f);
        //2022-01-04 13:11:38

        /*
         * 将String转换成Date，
         * 由于我们获取的日期格式不一，所以我们需要在格式化String之前，选定自己的String格式
         * */
        String date4s = "2022-01-04 13:11:38";
        //创建指定格式的转换器
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd");
        String date5s = "01-04";
        try {
            Date date4 = format.parse(date4s);
            Date date5 = format1.parse(date5s);
            //Unparseable date: "01-04"

            System.out.println(date4);
            //Sun Jan 04 00:00:00 CST 1970
            System.out.println(date5);
            //Sun Jan 04 00:00:00 CST 1970,此处年份默认为1970年
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    //Calender 日历类

    /**
     * 平时我们从数据库取出来的数据都是标准Date类型
     */
    @Test
    public void CalenderTest() {
        //public abstract class Calendar
        // 由于日历类是抽象类且其构建方法被私有化，所以我们需要调用其开放API生成日历类，即 getInstance()
        Calendar calendar = Calendar.getInstance();
        //calendar.setTimeZone(new SimpleTimeZone(0, "US"));
        //因为Date方法中的获取年月日过时了，官方推荐使用日历获取当前日期的年月日，时分秒。
        Date date1 = calendar.getTime();
        System.out.println("获取当前时间：" + date1);
        System.out.println("获取当前年份：" + calendar.get(Calendar.YEAR));
        System.out.println("获取当前年份有几周：getWeeksInWeekYear() = " + calendar.getWeeksInWeekYear());
        //在获取月份时，需要+1，因为他是从0开始计算的
        System.out.println("获取当前月份：" + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("获取日期（=DAY_OF_MONTH）：" + calendar.get(Calendar.DATE));
        System.out.println("获取今天是这月的第几天：" + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("获取今天是今年的第几天：" + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("获取今天是这周的第几天（SUNDAY、MONDAY、TUESDAY、WEDNESDAY、THURSDAY、FRIDAY、SATURDAY）：" + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("获取今天所在周是这月的第几周：" + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("获取小时（12小时制）：" + calendar.get(Calendar.HOUR));
        System.out.println("获取小时（24小时制）：" + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("获取今天所在周是这月的第几周：" + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("获取今天所在周是这月的第几周：" + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));

    }
}
