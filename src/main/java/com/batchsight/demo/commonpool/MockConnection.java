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

class MockConnection {

  private int id;

  MockConnection(int id) {
    this.id = id;
  }

  String getName() {
    return "Connection-" + id;
  }

  void reset() {
  }
}
