package com.batchsight.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ReactorDemo{

  private void sleep(int millis) {
    try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
  }

  private void println(Object obj) {
    System.out.println(obj.toString());
    System.out.println("ooo");
  }

  @Test
  public void testMono() {
    Mono.fromSupplier(()->{
      sleep(3000);
      return "Hello";
    }).subscribe(System.out::println);

    System.out.println("OK");
  }

  @Test
  public void testMonoCreator() {
    Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
    Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
    Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
  }

  @Test
  public void testSimpleCreator() throws Exception {
    System.out.println("*** 1 ***");
    Flux.just("Hello", "World").subscribe(System.out::println);

    System.out.println("*** 2 ***");
    Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);

    System.out.println("*** 3 ***");
    Flux.empty().subscribe(System.out::println);

    System.out.println("*** 4 ***");
    Flux.range(1, 10).subscribe(System.out::println);

    System.out.println("*** 5 ***");
    Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).subscribe(System.out::println);
    // Flux.intervalMillis(1000).subscribe(System.out::println);

    Thread.sleep(5000);
  }

  @Test
  public void testGenerate() {
    System.out.println("*******");
    Flux.generate(sink -> {
      sink.next("Hello");
      sink.complete();
    }).subscribe(System.out::println);

    final Random random = new Random();
    Flux.generate(ArrayList::new, (list, sink) -> {
      int value = random.nextInt(100);
      list.add(value);
      sink.next(value);
      if (list.size() == 10) {
        sink.complete();
      }
      return list;
    }).subscribe(this::println);
  }

  @Test
  public void testCreate() {
    System.out.println("*******");
    Flux.create(sink -> {
      for (int i = 0; i < 10; i++) {
        sink.next(i);
      }
      sink.complete();
    }).subscribe(this::println);
  }

  @Test
  public void subscribe() {
    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalStateException()))
        .subscribe(System.out::println, System.err::println);
  }

  @Test
  public void testHot() throws InterruptedException {
    final Flux<Long> source = Flux.interval(Duration.of(1, ChronoUnit.SECONDS))
      .take(10)
      .publish()
      .autoConnect();
    source.subscribe();
    Thread.sleep(5000);
    source
      .toStream()
      .forEach(this::println);
  }

  private static void onErrorReturn() {
    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalStateException()))
        .onErrorReturn(0)
        .subscribe(System.out::println);
  }

  private static void retry() {
    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalStateException()))
        .retry(1)
        .subscribe(System.out::println);
  }

  @Test
  public void testMap() throws InterruptedException {
    // map 逐个生成并输出
    Flux.just(1, 2, 3, 4)
      .log()
      .map(i -> {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return i * 2;
      })
      .subscribe(e -> log.info("get:{}", e));
  }

  @Test
  public void testFaltMap() throws InterruptedException {
    // flatMap 批量生成后逐个输出
    Flux.just(1, 2, 3, 4)
      .log()
      .flatMap(e -> {
        return Flux.just(e * 3).delayElements(Duration.ofSeconds(1));
      })
      .subscribe(e -> {
        log.info("get:{} thread_id: {}" ,e ,Thread.currentThread().getId());
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception ignored) {}
      });
    TimeUnit.SECONDS.sleep(10);
  }
}
