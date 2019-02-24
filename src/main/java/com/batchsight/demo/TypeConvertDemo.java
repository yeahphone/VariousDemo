package com.batchsight.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2018</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2018/7/31    Yeahphone        Created
 * </pre>
 */

class TypeConvertDemo {

    private final String strPI = "3.14159";
    private final Double PI = 3.14159;

    @Test
    void testValueOfString() {
        Object obj = null;
        String val = String.valueOf(obj);
        assertEquals(val, "null");

        val = String.valueOf(3.14159);
        assertEquals(strPI, val);
    }

    @Test
    void testStringToBoolean() {
        boolean b = Boolean.valueOf("abc");
        assertFalse(b);

        b = Boolean.valueOf("true");
        assertTrue(b);

        b = Boolean.valueOf("True");
        assertTrue(b);

        b = Boolean.valueOf("TRUE");
        assertTrue(b);

        b = Boolean.valueOf("tRue");
        assertTrue(b);
    }

    @Test
    void testStringToNumber() {
        double val = Double.valueOf(strPI);
        assertEquals(PI, val, 1e-11);

        val = Double.valueOf("abc");
        assertEquals(0, val);
    }

    @Test
    void testNumberUtils() {
        /* 可解析数字 */
        assertTrue(NumberUtils.isParsable("123"));
        assertTrue(NumberUtils.isParsable("123.456"));
        assertTrue(NumberUtils.isParsable("-123.456"));
        assertTrue(NumberUtils.isParsable(".123"));
        assertTrue(NumberUtils.isParsable("001"));

        /* 不可解析数字 */
        assertFalse(NumberUtils.isParsable("+1"));
        assertFalse(NumberUtils.isParsable("0x123"));
        assertFalse(NumberUtils.isParsable("123L"));
        assertFalse(NumberUtils.isParsable("123f"));
    }

    @Test
    void testIsNumber() {
        double d = 3.14159;
        Object pi = d;

        assertTrue(pi instanceof Number);
    }

    @Test
    void testToLong() {
        try {
            NumberUtils.createLong("123.0");
        } catch (NumberFormatException e) {
            assertEquals(e.getMessage(), "For input string: \"123.0\"");
        }
    }

    @Test
    void testUuidToString() {
        System.out.println(UUID.randomUUID().toString());
    }

    @Test
    void testTypeCast() {
      /* cast 不会进行类型转换，所以以下用法会抛出异常 */
      System.out.println(String.class.cast(123));
    }

    @Test
    void testTypeClass() {
      double val = 0;
      foo(double.class);
    }

    @Test
    void testSetConverter() throws IOException {
      Set<String> set1 = new HashSet<>();
      set1.add("TEST");
      set1.add("测试");
      set1.add("12.345");
      String json1 = toJsonString(set1);
      HashSet set2 = jsonToObject(json1, HashSet.class);
      String json2 = toJsonString(set2);
      System.out.println(json1 + "\n" + json2);
      assertEquals(json1, json2);
    }

    @Test
    void testClassTypeConverter() throws IOException {
      Class<?> type1 = DateTime.class;
      String json1 = toJsonString(type1);
      System.out.println(json1);
      Class type2 = jsonToObject(json1, Class.class);
      System.out.println(type2);
    }

    @Getter
    @Setter
    public static class Dto1 {
      private String str;
      private List<Dto2> dto2s;

      @JsonManagedReference
      public List<Dto2> getDto2s() {
        return this.dto2s;
      }

      public void setStr(String s) {
        this.str = s;
      }

      public String getStr() {
        return this.str;
      }

      void addDto2(Dto2 dto2) {
        if (this.dto2s == null) this.dto2s = new ArrayList<>();
        this.dto2s.add(dto2);
      }
    }

    @Getter
    @Setter
    public static class Dto2 {
      private String str;
      private Dto1 dto1;

      @JsonBackReference
      public Dto1 getDto1() {
        return this.dto1;
      }

      public void setDto1(Dto1 dto1) {
        this.dto1 = dto1;
      }
    }

    @Test
    void testToJsonString() throws IOException {
      System.out.println(toJsonString(DateTime.now()));
      System.out.println(toJsonString(DateTime.class));

      Dto1 dto1 = new Dto1();
      dto1.setStr(null);
      System.out.println(toJsonString(dto1));
    }

    @Test
    void testToJsonStringRecursive() throws IOException {
      Dto2 dto2 = new Dto2();

      Dto1 dto1 = new Dto1();
      dto1.setStr(null);

      dto2.setDto1(dto1);
      dto1.addDto2(dto2);

      String strDto1 = toJsonString(dto1);
      String strDto2 = toJsonString(dto2);

      System.out.println(strDto1);
      System.out.println(strDto2);

      Dto1 oDto1 = jsonToObject(strDto1, Dto1.class);
      Dto2 oDto2 = jsonToObject(strDto2, Dto2.class);
    }

    @Test
    void testNullConvert() throws IOException {
      DateTime dateTime = null;
      String json = toJsonString(dateTime);
      /* 对象为 null 时返回的 json 字符串为空字符串 "" */
      assertEquals("", json);
      dateTime = jsonToObject(json, DateTime.class);
      System.out.println(String.valueOf(dateTime));
    }

    @Test
    void testBase64() {
      String test = "this is a test.";
      String str = Base64.getEncoder().encodeToString(test.getBytes());
      System.out.println(str);
    }

    @Test
    void testApiResult2Json() throws IOException {
      APIResult<String> result = new APIResult<>();
      System.out.println(toJsonString(result));
    }

    @Test
    void testSplit() {
      String str = "172.16.11.123, 172.16.11.124";
      String[] ips = str.split(",");
      Arrays.asList(ips).forEach(i -> System.out.println(i.trim()));

      str = "172.16.11.123";
      ips = str.split(",");
      Arrays.asList(ips).forEach(i -> System.out.println(i.trim()));
    }

    private <T> void foo(Class<T> type) {
      if (type.isAssignableFrom(Number.class)) {
        System.out.println("OK");
      }
    }

  private <T> T jsonToObject(String json, Class<T> type) throws IOException {
    if (StringUtils.isEmpty(json)) return null;

    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, type);
  }

  private static String toJsonString(Object obj) throws IOException {
    StringWriter sw = new StringWriter();
    JsonGenerator jsonGenerator = new ObjectMapper().getFactory().createGenerator(sw);
    jsonGenerator.writeObject(obj);
    return sw.toString();
  }
}
