package com.batchsight.demo;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author Yeahphone
 * @version 1.0
 * <pre>Histroy:
 *       2018/8/29    Yeahphone        Created
 * </pre>
 */

class SpELDemo {
  @Getter
  private int num = 1825;

  @Test
  void testArrayList() {
    ExpressionParser parser = new SpelExpressionParser();
    List numbers = (List) parser.parseExpression("{1,2,3,4}").getValue();
    System.out.println(numbers);
  }

  @Test
  void testProperties() {
    ExpressionParser parser = new SpelExpressionParser();
    System.out.println(this);
    System.out.println(parser.parseExpression("num").getValue(this));
  }
}
