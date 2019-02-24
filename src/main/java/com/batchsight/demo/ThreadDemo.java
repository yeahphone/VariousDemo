package com.batchsight.demo;

import org.junit.jupiter.api.Test;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2018</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2018/8/29    Zuo Yefeng        Created
 * </pre>
 */

class ThreadDemo {
  private final Integer signal = 0;

  @Test
  void testSynchronize() throws InterruptedException {
    Runnable thread1 = () -> {
      synchronized (signal) {
        for (int i = 0; i < 1000; ++i) {
          System.out.println("A - " + System.nanoTime());
    }}};

    Runnable thread2 = () -> {
      for (int i = 0; i < 1000; ++i) {
        System.out.println("B - " + System.nanoTime());
    }};

    Thread t1 = new Thread(thread1);
    Thread t2 = new Thread(thread2);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
  }
}
