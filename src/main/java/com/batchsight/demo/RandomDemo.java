package com.batchsight.demo;

import org.junit.*;

import java.util.Random;

public class RandomDemo {
  @Test
  void testRandom() {
    Random random = new Random();

    for (int i = 0; i < 10; ++i) {
      System.out.println(random.nextInt(100));
    }
  }
}
