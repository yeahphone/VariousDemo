package com.batchsight.demo;

import org.junit.Test;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class FunctionalProgrammingDemo {

  @Test
  public void testFunc01() {
    int[] nums = {35, 65, -55, 100, -676, 95};
    // 命令式编程关注怎么样做，而函数式编程中关注做什么
    IntStream.of(nums).parallel().min().ifPresent(System.out::println);
  }

  @Test
  public void testFunc02() {
    // 函数式编程让代码更加可读
    Runnable target2 = () -> System.out.println("新建了一个线程");
    Runnable target3 = () -> System.out.println("新建了一个线程");

    System.out.println(target2 == target3); // false
    new Thread(target2).start();
  }

  @Test
  public void testParallel() {
    long startTime = System.currentTimeMillis();   //获取开始时间
    LongStream.rangeClosed(1L, 10000L)
      .parallel()
      .forEach(in -> {
        Thread thread = Thread.currentThread();
        System.out.println("thread: " + thread.getName() + ", value: " + in);
        try {
          Thread.sleep(5);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      });
    long endTime = System.currentTimeMillis(); //获取结束时间
    System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
  }

  @Test
  public void testPredicate() {
    IntPredicate predicate = i -> i > 0;
    System.out.println(predicate.test(-9)); //false
  }

  @Test
  public void testConsumer() {
    Consumer<String> consumer = System.out::println;
    consumer.accept("输入的数据"); //输入的数据
  }
}
