package com.batchsight.demo;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataStructureDemo {
  @Test
  public void testLinkedHashMap() {
    Map<String, String> map = new LinkedHashMap<>(16, 0.75f, true);
    map.put("apple", "苹果");
    map.put("watermelon", "西瓜");
    map.put("banana", "香蕉");
    map.put("peach", "桃子");

    // LinkedHashMap 将根据最近的访问排序，可以用来实现 LRU 缓存
    map.get("banana");
    map.get("apple");

    for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
      System.out.println(stringStringEntry.getKey() + " = " + stringStringEntry.getValue());
    }
  }
}
