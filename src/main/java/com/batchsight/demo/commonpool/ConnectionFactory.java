package com.batchsight.demo.commonpool;

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

class ConnectionFactory {
  private static final Object lock = new Object();
  private static Integer id = 0;

  MockConnection getConnection() {
    synchronized (lock) {
      return new MockConnection(++id);
    }
  }
}
