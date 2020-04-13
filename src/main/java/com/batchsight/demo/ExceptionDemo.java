package com.batchsight.demo;

import org.junit.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2018</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2018/9/1    Zuo Yefeng        Created
 * </pre>
 */

class ExceptionDemo {
  @Test
  void restApi() {
    try {
      api();
    } catch (Exception e) {
      System.out.println(e.hashCode());
      e.printStackTrace();
      System.out.println("12345 " + e.getMessage());
    }
  }

  private void dao() throws RuntimeException {
    try {
      int i = 0;
      int j = 10 / i;
    } catch (Exception e) {
      MyException ex = new MyException(1001, "My exception of dao: " + e.getMessage());
      MyContext ctx = ex.getContext();
      ctx.put("ctx1", "aaa");
      ctx.put("ctx2", "bbb");

      System.out.println(ex.hashCode());
      throw ex;
    }
  }

  private void serviceC() throws RuntimeException {
    try {
    dao();
    } catch (RuntimeException e) {
      MyException ex = new MyException(1002, "My exception of service", e);
      MyContext ctx = ex.getContext();
      ctx.put("ctx3", "ddd");
      ctx.put("ctx4", "eee");

      throw ex;
    }
  }

  private void serviceB() {
    serviceC();
  }

  private void api() {
    serviceB();
  }
}
