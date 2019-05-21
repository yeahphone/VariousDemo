package com.batchsight.demo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

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
}
