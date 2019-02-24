package com.batchsight.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.*;
import java.util.Map;

/**
 * <p>Title: FileAccessDemo</p>
 * <p>Description: 文件 IO 使用</p>
 *
 * @author Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2018/10/23    Zuo Yefeng        Created
 * </pre>
 */

class FileAccessDemo {
  @Test
  void testReadFile() throws IOException {
    String content = readFile();
    System.out.println(content);
  }

  @Test
  void testWriteFile() throws IOException {
    String key = "seq";
    String json = readFile();
    ObjectMapper objectMapper = new ObjectMapper();
    Map map1 = objectMapper.readValue(json, Map.class);
    Assert.notNull(map1, "map1 is null");
    int val = (Integer) map1.get(key);
    map1.put(key, ++val);

    StringWriter sw = new StringWriter();
    JsonGenerator jsonGenerator = new ObjectMapper().getFactory().createGenerator(sw);
    jsonGenerator.writeObject(map1);
    String jsonString = sw.toString();

    System.out.println(jsonString);

    writeFile(String.valueOf(jsonString));
  }

  private final String filename = "demo.config";

  private void writeFile(String fileContent) throws IOException {
    FileOutputStream fos = new FileOutputStream(filename);
    try (OutputStreamWriter writer = new OutputStreamWriter(fos)) {
      writer.write(fileContent);
    }
  }

  private String readFile() throws IOException {
    FileInputStream fis = new FileInputStream(filename);
    InputStreamReader reader = new InputStreamReader(fis);
    BufferedReader bufReader = new BufferedReader(reader);
    StringBuilder content = new StringBuilder();
    String line = bufReader.readLine();

    while (line != null) {
      content.append(line);
      line = bufReader.readLine();
    }

    return content.toString();
  }
}
