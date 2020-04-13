package com.batchsight.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import lombok.Data;
import org.joda.time.DateTime;
import org.junit.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.text.FieldPosition;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2018</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2018/8/2    Zuo Yefeng        Created
 * </pre>
 */

class JacksonDemo {
    @Test
    void testJsonToArray() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "[\"aaa\", 123, 1.23, {\"bbb\":1, \"ccc\":\"ddd\"}]";
        List value = objectMapper.readValue(json, List.class);
        Assert.assertTrue(value.get(3) instanceof Map);
        Assert.assertEquals(ArrayList.class, value.getClass());

        json = "{\"a\":1, \"b\":\"c\"}";
        Map value2 = objectMapper.readValue(json, Map.class);
        Assert.assertEquals(LinkedHashMap.class, value2.getClass());
    }

    @Test
    void testObjectToJson() throws IOException {
        Entity<Double> entity = new Entity<>();
        entity.setId(123);
        entity.setConfigKey("key1");
        entity.setUuid(new byte[]{0,1,2,3,4,5,6,7,8,9,0xA,0xB,0xC,0xD,0xE,0xF});
        entity.setGeneralVariable(3.1415);

        StringWriter sw = new StringWriter();
        JsonGenerator jsonGenerator = new ObjectMapper().getFactory().createGenerator(sw);
        jsonGenerator.writeObject(entity);
        String jsonString = sw.toString();
        System.out.println(jsonString);
        Assert.assertEquals("{\"id\":123,\"uuid\":\"AAECAwQFBgcICQoLDA0ODw==\",\"generalVariable\":3.1415,\"valueType\":\"java.lang.Double\",\"key\":\"key1\"}", jsonString);
    }

    @Test
    void testJsonToObject() throws IOException {
      String jsonString = "{\"id\":123,\"uuid\":\"AAECAwQFBgcICQoLDA0ODw==\",\"valueType\":\"java.lang.Double\",\"generalVariable\":3.1415,\"key\":\"key1\"}";
      ObjectMapper objectMapper = new ObjectMapper();

      Entity entity = objectMapper.readValue(jsonString, Entity.class);
      Assert.assertEquals(123, (int)entity.getId());
      Assert.assertEquals("key1", entity.getConfigKey());
      Assert.assertEquals(Double.class, entity.getValueType());
      Assert.assertTrue(Arrays.equals(entity.getUuid(), new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF}));
      Assert.assertEquals(3.1415, entity.getGeneralVariable());
    }

  private byte[] uuidToByte(UUID uuid) {
    if (uuid == null) {
      return null;
    }
    ByteArrayOutputStream ba = new ByteArrayOutputStream(16);
    DataOutputStream da = new DataOutputStream(ba);
    try {
      da.writeLong(uuid.getMostSignificantBits());
      da.writeLong(uuid.getLeastSignificantBits());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ba.toByteArray();
  }

  private byte[] longToByteArray(long data) {
    return new byte[]{
        (byte) ((data >> 56) & 0xff),
        (byte) ((data >> 48) & 0xff),
        (byte) ((data >> 40) & 0xff),
        (byte) ((data >> 32) & 0xff),
        (byte) ((data >> 24) & 0xff),
        (byte) ((data >> 16) & 0xff),
        (byte) ((data >> 8) & 0xff),
        (byte) (data & 0xff),
    };
  }

  private void printByteArray(byte[] byteArray) {
    for (byte b : byteArray) {
      System.out.print(" " + String.format("%02X", b & 0xFF));
    }
  }

  @Test
  void testUuidToByteArray() {
    UUID uuid = UUID.randomUUID();
    System.out.println(uuid);

    long msb = uuid.getMostSignificantBits();
    byte[] bytes = longToByteArray(msb);
    printByteArray(bytes);

    long lsb = uuid.getLeastSignificantBits();
    bytes = longToByteArray(lsb);
    printByteArray(bytes);
  }

  @Test
  void testTypeReference() throws IOException {
    TypeReference<HashMap<String, Integer>> typeRef
        = new TypeReference<HashMap<String, Integer>>() {};

    String json = "{\"aaa\":123, \"bbb\":456}";

    HashMap<String, Integer> o = fromJsonString(json, typeRef);
    System.out.println("Got " + o);
  }

  @Test
  void testMapToString() {
      Map<String, String> map = new HashMap<String, String>();
      map.put("abc", "123");
      map.put("def", "456");
      System.out.println(map.toString());
  }

  private <T> T fromJsonString(String json, TypeReference<T> typeRef) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, typeRef);
  }

  class RFC3339DateFormat extends ISO8601DateFormat {

    // Same as ISO8601DateFormat but serializing milliseconds.
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
      String value = ISO8601Utils.format(date, true);
      toAppendTo.append(value);
      return toAppendTo;
    }

  }

  @Test
  void testJoda() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .registerModule(new JodaModule())
        .setDateFormat(new RFC3339DateFormat());

    DateTime now = DateTime.now();

    StringWriter sw = new StringWriter();
    JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(sw);
    jsonGenerator.writeObject(now);
    String jsonString = sw.toString();
    System.out.println(jsonString);
  }

  @Data
  static class TestDTO {
      private int varIable01 = 1;
      private int vAriable02 = 2;
      private int VAriable03 = 3;
      private int VARiable04 = 4;
      private int VARIABLE05 = 5;
      private int VARIablE06 = 6;
      private int vaRiablE07 = 7;
      private int variable07 = -7;
      private int vARIABLE08 = 8;
      private int VarIable09 = 9;
      private boolean variable10 = true;
  }

  @Test
  void testCapitalization() throws IOException {
    TestDTO testDTO01 = new TestDTO();
    System.out.println(testDTO01.toString());

    StringWriter sw = new StringWriter();
    JsonGenerator jsonGenerator = new ObjectMapper().getFactory().createGenerator(sw);
    jsonGenerator.writeObject(testDTO01);
    String jsonString = sw.toString();

    System.out.println(jsonString);

    ObjectMapper objectMapper = new ObjectMapper()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .registerModule(new JodaModule())
        .setDateFormat(new RFC3339DateFormat());

    TestDTO testDTO02 = objectMapper.readValue(jsonString, TestDTO.class);
    System.out.println(testDTO02.toString());
  }
}
