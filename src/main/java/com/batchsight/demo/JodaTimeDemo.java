package com.batchsight.demo;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2018</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2018/8/28    Zuo Yefeng        Created
 * </pre>
 */

class JodaTimeDemo {
  @Test
  void testTimestamp() {
    long timestamp = 1535440913L * 1000;
    DateTime dateTime = new DateTime(timestamp);
    System.out.println(dateTimeToString(dateTime) + " " + dateTime.getZone().toString());
  }

  private String dateTimeToString(DateTime dateTime) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    return dateTimeFormatter.print(dateTime);
  }

  @Test
  void testDate() throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = format.parse( "2019-5-5 21:36:15" );
    System.out.println(date);
  }

  @Test
  void testDuration() {
    DateTime time1 = new DateTime(2020, 3, 1, 0, 0, 0);
    DateTime time2 = new DateTime(2020, 3, 3, 23, 59, 59);
    Duration duration = new Duration(time1, time2);
    System.out.println(duration.getStandardDays());     // 2
    System.out.println(duration.getStandardHours());    // 71
    System.out.println(duration.getStandardMinutes());  // 4319
    System.out.println(duration.getStandardSeconds());  // 259199 = 86400 * 2 + 86399

    long t1 = DateTimeUtils.getInstantMillis(time1);
    long t2 = DateTimeUtils.getInstantMillis(time2);
    System.out.println(t2 - t1);               // 259199000
    System.out.println(duration.getMillis());  // 259199000

    Period period = duration.toPeriod();
    System.out.println(period.getYears());     // 0
    System.out.println(period.getWeeks());     // 0
    System.out.println(period.getDays());      // 0
    System.out.println(period.getHours());     // 71
    System.out.println(period.getMinutes());   // 59
    System.out.println(period.getSeconds());   // 59
    System.out.println(period.getMillis());    // 0

    period = new Period(time2, time1);
    System.out.println(period.getYears());     // 0
    System.out.println(period.getWeeks());     // 0
    System.out.println(period.getDays());      // -2
    System.out.println(period.getHours());     // -23
    System.out.println(period.getMinutes());   // -59
    System.out.println(period.getSeconds());   // -59
    System.out.println(period.getMillis());    // 0
  }
}
