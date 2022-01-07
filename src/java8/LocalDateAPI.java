package java8;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.function.Supplier;

/**
 * @author masuo
 * @data 27/12/2021 下午4:47
 * @Description java8 新特性之 LocalDate API
 * Java.util 包提供了Date（）类来封装当前日期时间。
 */

@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
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


        //获取日期的时间戳,获取时间戳，在后面时非常有用的，非常多的地方的转换都可以用到
        System.out.println(date1.toInstant().toEpochMilli());
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
        System.out.println("获取分钟：" + calendar.get(Calendar.MINUTE));
        System.out.println("获取秒：" + calendar.get(Calendar.SECOND));
        System.out.println("获取豪秒：" + calendar.get(Calendar.MILLISECOND));

        //设置当前时间,设置时间有三种方式
        /*
         * 1.分别设置年月日，时分秒
         *   calendar.set(Calendar.HOUR,10);
         *   calendar.set(Calendar.MINUTE,10);
         *   calendar.set(Calendar.SECOND,10);
         * 2.一次性设置年月日，时分秒：
         *   calendar.set(2021, Calendar.JUNE,21);
         * 3.设置一个Date类型
         *   calendar.setTime(new Date());
         * */
        calendar.set(2021, Calendar.JUNE, 21);
        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 10);

        System.out.println("输出设置的时间：" + calendar.getTime());

        //给时间增加一定时间
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        System.out.println("月增加5天后为：" + calendar.getTime());
        calendar.add(Calendar.DAY_OF_WEEK, 5);
        System.out.println("星期增加5天后为：" + calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 5);
        System.out.println("年增加5天后为：" + calendar.getTime());
        calendar.setTime(new Date());
        System.out.println("输出设置的时间：" + calendar.getTime());
    }

    // 时区
    @Test
    public void TimeZoneTest() {

        //获取默认的时区，即本地时区
        TimeZone aDefault = TimeZone.getDefault();

        //本地时区ID
        System.out.println(aDefault.getID());
        //时区偏移量，ms，相对本初子午线,1h = 1*60*60*100 ms
        System.out.println(aDefault.getRawOffset());
        System.out.println(aDefault.getRawOffset() / 360000);
        System.out.println(aDefault.getDisplayName());
        //中国标准时间
        System.out.println(aDefault.getDSTSavings());
        System.out.println(aDefault);
        //sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=29,lastRule=null]

        //可用时区的ID
        for (String availableID : TimeZone.getAvailableIDs()) {
            System.out.println(availableID);
        }

    }

    //since jdk 1.8，因为之前的SimpleDateFormat是线程不安全的
    // 虽然sun公司声明自jdk1.8之后的时间API是线程安全的，但是如果操作者使用不当还是不能保证线程安全的
    // 多线程并发如何保证线程安全 - 需要避免多个线程之间共享一个SimpleDateFormat对象，每个线程使用时都创建一次SimpleDateFormat对象
    // 创建和销毁对象的开销大 - 对使用format和parse方法的地方进行加锁 =>
    // 线程阻塞性能差 - 使用ThreadLocal保证每个线程最多只创建一次SimpleDateFormat对象
    //参考：https://www.cnblogs.com/huanshilang/p/12013386.html

    // LocalDate 只包括年月日
    @Test
    public void LocalDateTest() {
        //来看一下LocalDate的构造函数，这是一个私有的构造函数，即不能被外部声明，所以我们只能找到其外部API接口
        //private LocalDate(int year, int month, int dayOfMonth) {
        //        this.year = year;
        //        this.month = (short) month;
        //        this.day = (short) dayOfMonth;
        //    }
        LocalDate localDate;
        //当前时间
        {
            //无参，会传入默认的 now(Clock.systemDefaultZone());
            localDate = LocalDate.now();
            System.out.println(localDate);
            //2022-01-07
        }
        {
            //传入Clock参数
            localDate = LocalDate.now(Clock.systemDefaultZone());
            System.out.println(localDate);
            //2022-01-07
        }
        {
            //传入默认的ZoneId
            localDate = LocalDate.now(ZoneId.systemDefault());
            System.out.println(localDate);
            //2022-01-07
        }

        //指定时间
        {
            localDate = LocalDate.of(2021, 10, 6);
            System.out.println(localDate);
        }

        //根据字符串获取
        {
            //字符串默认拼接方式为
            //parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
            // ISO_LOCAL_DATE是一个静态代码块
            //static {
            //        ISO_LOCAL_DATE = new DateTimeFormatterBuilder()
            //                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)//年份最少4位，最长10位
            //                .appendLiteral('-')// 要用 - 拼接
            //                .appendValue(MONTH_OF_YEAR, 2)//月份固定2位
            //                .appendLiteral('-')// 要用 - 拼接
            //                .appendValue(DAY_OF_MONTH, 2)//天数固定2位
            //                .toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
            //    }
            localDate = LocalDate.parse("2021-10-09");
            System.out.println(localDate);
            //可以自定义拼接方式，
            localDate = LocalDate.parse("2021/10/4", DateTimeFormatter.ofPattern("yyyy/MM/d"));
            System.out.println(localDate);
            localDate = LocalDate.parse("2021/10/04", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            System.out.println(localDate);
        }
        //Date --》 LocalDate
        {
            //如果我有一个Date类型，获取它的LocalDate
            Date date = new Date();
            //获取它的时刻，瞬间
            Instant instant = date.toInstant();
            // 2022-01-07T03:21:09.596Z 精确到ns（纳秒）
            System.out.println(instant);
            //通过Instant获取那一时刻的LocalDate，同理可以获取LocalTime和LocalDateTime
            localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            System.out.println(instant.atZone(ZoneId.systemDefault()).toLocalTime());
            System.out.println(instant.atZone(ZoneId.systemDefault()).toLocalDateTime());
            System.out.println(localDate);
        }
        //LocalDate --》 Date
        {
            //先通过LocalDate获取zonedDateTime，
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
            //获取Instant
            Instant instant = zonedDateTime.toInstant();
            //转换成Date类型
            Date date = Date.from(instant);
            System.out.println(date);
            //Fri Jan 07 00:00:00 CST 2022
        }
        // 比较
        {
            System.out.println(localDate.isAfter(LocalDate.now()));
            System.out.println(localDate.isBefore(LocalDate.now()));
            System.out.println(localDate.isEqual(LocalDate.now()));
        }

    }

    @Test
    public void LocalTimeTest() {
        LocalTime localTime;
        //同LocalDate
        {
            //同样的，LocalTime也
            localTime = LocalTime.now();
            //private LocalTime(int hour, int minute, int second, int nanoOfSecond) {
            //        this.hour = (byte) hour;
            //        this.minute = (byte) minute;
            //        this.second = (byte) second;
            //        this.nano = nanoOfSecond;//纳秒
            //    }
            System.out.println(localTime);
            //11:13:36.199
        }
    }

    @Test
    public void LocalDateTimeTest() {
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        //2022-01-07T15:21:54.486

        //设置指定日期
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 10, 10, 10, 10, 10, 10);
        LocalDateTime localDateTime2 = LocalDateTime.of(2021, 10, 10, 10, 10, 10);
        LocalDateTime localDateTime3 = LocalDateTime.of(2021, 10, 10, 10, 10);
        LocalDateTime localDateTime4 = LocalDateTime.of(2021, Month.APRIL, 10, 10, 10, 10, 10);
        LocalDateTime localDateTime5 = LocalDateTime.of(2021, Month.APRIL, 10, 10, 10, 10);
        LocalDateTime localDateTime6 = LocalDateTime.of(2021, Month.APRIL, 10, 10, 10);


        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);
        System.out.println(localDateTime4);
        System.out.println(localDateTime5);
        System.out.println(localDateTime6);

        //标准解析
        LocalDateTime localDateTime8 = LocalDateTime.parse("2022-01-07T15:21:54.486");
        LocalDateTime localDateTime7 = LocalDateTime.parse("2022-01-07 15:21:54",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(localDateTime7);
        System.out.println(localDateTime8);

        //获取LocalDate
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localDate);
        System.out.println(localTime);
    }

    //Instant 一瞬间，时间戳
    @Test
    public void InstantTest() {
        //同样的，Instant也是私有构造方法，
        //private Instant(long epochSecond, int nanos) {
        //        super();
        //        this.seconds = epochSecond;
        //        this.nanos = nanos;
        //    }
        Instant instant = Instant.now();
        System.out.println(instant);
        //2022-01-07T04:04:36.648Z

        //当前时间戳
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(System.currentTimeMillis());

        //时间戳是一个long类型，如何通过一个时间戳得到一个Instant类型
        instant = Instant.ofEpochMilli(System.currentTimeMillis());
        //获取Instant
        System.out.println(instant);


        //增加1小时，值本身不会改变，需要一个新的变量去接受
        Instant plus = instant.plus(1, ChronoUnit.HOURS);
        System.out.println(instant);
        System.out.println(plus);

        //减两小时
        Instant minus = instant.minus(2, ChronoUnit.HOURS);
        System.out.println(minus);

        //因为Instant类是不处理 年月日这种人类的时间单位，
        // 如果要处理，需要把 Instant对象转换为 LocalDateTime 或者 ZonedDateTime 这时需要和一个时区绑定
        //获取ZonedDateTime
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());

        //获取LocalDateTime,通过ZonedDateTime获取
        System.out.println(zonedDateTime.toLocalDateTime());
        //获取LocalDateTime,通过Instant获取
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDateTime);

    }

    //Period 时期
    @Test
    public void PeriodTest() {
        LocalDate startDate = LocalDate.of(2017, 1, 20);
        LocalDate endDate = LocalDate.of(2017, 2, 10);
        //（2015.2.20，2017.1.15）
        Period period = Period.between(startDate, endDate);
        //获取两个时间之间的年数，前开后开
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        System.out.println(years);
        System.out.println(months);
        System.out.println(days);

    }

    //Duration 期间
    @Test
    public void DurationTest() {
        Instant start = Instant.parse("2017-10-03T10:15:30.00Z");
        Instant end = Instant.parse("2017-10-03T10:16:30.00Z");

        Duration duration = Duration.between(start, end);
        System.out.println(duration.getSeconds());
    }

    //ZoneId 时区
    @Test
    public void ZoneIdTest() {

        //查看所有的时区id
        ZoneId.getAvailableZoneIds().forEach(System.out::println);
    }

    //Clock 时钟
    @Test
    public void ClockTest() {

        Clock clock = Clock.systemUTC();
        Instant instant = clock.instant();
        long millis = clock.millis();
        ZoneId zone = clock.getZone();
        System.out.println(instant);
        System.out.println(millis);
        System.out.println(zone);
        System.out.println(clock);
    }
}
