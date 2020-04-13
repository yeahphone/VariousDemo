package com.batchsight.demo;

import org.apache.commons.math3.util.FastMath;
import org.junit.*;

/**
 * <p>Title: GenericProgramingDemo</p>
 * <p>Description: 泛型编程测试</p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2018</p>
 *
 * @author Yeahphone
 * @version 1.0
 * <pre>Histroy:
 *       2018/7/13    Yeahphone        Created
 * </pre>
 */
public class GenericProgramingDemo {

  @Test
  public void genericFunctionTest() {
    Generic<Integer> gi = new Generic<>(10);
    Generic<Double> gd = new Generic<>(FastMath.PI);
    Generic gu = new Generic();

    showKeyValue1(gi);
    showKeyValue1(gd);
    showKeyValue1(gu);

    showKeyValue2(1.5f);
    showKeyValue2(1.5);

    gu.setKey(123);
    showKeyValue2(gu.getKey());

    gu.setKey(12.3);
    showKeyValue2(gu.getKey());
  }

  static class Generic<T extends Number> {

    private T key;

    Generic() {}

    Generic(T key) {
      this.key = key;
    }

    void setKey(T key) {
      this.key = key;
    }

    T getKey() {
      return key;
    }
  }

  private static void showKeyValue1(Generic<?> obj){
    System.out.println("泛型测试-1 key value is " + obj.getKey());
  }

  private static <E extends Number> void showKeyValue2(E obj) {
    System.out.println("泛型测试-2 type is " + obj.getClass().getName());
  }
}
