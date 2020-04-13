package com.batchsight.demo.commonpool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2019</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2019/5/21    Zuo Yefeng        Created
 * </pre>
 */

class CommonPoolDemo {

  private Random rnd = new Random();

  private void doSth(GenericObjectPool<MockConnection> pool) {

    MockConnection conn = null;

    try {
      conn = pool.borrowObject();
      System.out.println("S-" + conn.getName() + " " + pool.getNumIdle());
      int sleepTime = rnd.nextInt(3000);

      if (sleepTime > 2000)
        throw new Exception();

      Thread.sleep(sleepTime);
      pool.returnObject(conn);

    } catch (Exception e){

      System.out.println(conn.getName() + " is failed.");
      try {
        pool.invalidateObject(conn); // 失效该连接并从 pool 中丢弃
      } catch (Exception ignored) { }
    } finally {
      System.out.println("E-" + conn.getName() + " " + pool.getNumIdle());
    }
  }

  @Test
  void testGenericObjectPool() throws Exception {

    GenericObjectPool<MockConnection> pool = new GenericObjectPool<>(new HdbConnectionPool());
    pool.setMaxTotal(100);
    pool.setMaxIdle(10);
    pool.setMinIdle(3);
    pool.preparePool();
    System.out.println(pool.getNumIdle());

    List<Thread> threadList = new ArrayList<>();

    try {
      for (int i = 0; i < 300; ++i) {

        Thread thread = new Thread(()-> doSth(pool));
        thread.start();
        threadList.add(thread);
      }
    } catch(Exception ignored){
    }

    for(Thread t : threadList) {
      t.join();
    }

    pool.close();
  }
}
