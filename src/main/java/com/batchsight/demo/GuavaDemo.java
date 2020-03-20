package com.batchsight.demo;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2019</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2019/8/12    Zuo Yefeng        Created
 * </pre>
 */

public class GuavaDemo {
  @Test
  void testGuava() {
    Optional<Integer> possible = Optional.of(5);
    possible = Optional.ofNullable(null);
    System.out.println(possible.isPresent());
  }
}
