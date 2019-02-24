package com.batchsight.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
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
 *       2018/8/2    Zuo Yefeng        Created
 * </pre>
 */

class JacksonDemo {
    @Test
    void testJsonToArray() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "[\"aaa\", 123, 1.23, {\"bbb\":1, \"ccc\":\"ddd\"}]";
        List value = objectMapper.readValue(json, List.class);
        assertTrue(value.get(3) instanceof Map);
        assertEquals(ArrayList.class, value.getClass());

        json = "{\"a\":1, \"b\":\"c\"}";
        Map value2 = objectMapper.readValue(json, Map.class);
        assertEquals(LinkedHashMap.class, value2.getClass());
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
        assertEquals("{\"id\":123,\"uuid\":\"AAECAwQFBgcICQoLDA0ODw==\",\"generalVariable\":3.1415,\"valueType\":\"java.lang.Double\",\"key\":\"key1\"}", jsonString);
    }

    @Test
    void testJsonToObject() throws IOException {
      String jsonString = "{\"id\":123,\"uuid\":\"AAECAwQFBgcICQoLDA0ODw==\",\"valueType\":\"java.lang.Double\",\"generalVariable\":3.1415,\"key\":\"key1\"}";
      ObjectMapper objectMapper = new ObjectMapper();

      Entity entity = objectMapper.readValue(jsonString, Entity.class);
      assertEquals(123, (int)entity.getId());
      assertEquals("key1", entity.getConfigKey());
      assertEquals(Double.class, entity.getValueType());
      assertTrue(Arrays.equals(entity.getUuid(), new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF}));
      assertEquals(3.1415, entity.getGeneralVariable());
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

  private <T> T fromJsonString(String json, TypeReference typeRef) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, typeRef);
  }
}
