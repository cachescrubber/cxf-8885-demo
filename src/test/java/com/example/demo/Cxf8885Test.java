package com.example.demo;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.apache.cxf.Bus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Cxf8885Test extends CxfJaxWsClientTest {

  @Override
  @Test
  void sayHello(@Autowired Bus bus) {

    int count = 5;
    for (int i = 0; i < count; i++) {
      super.sayHello(bus);
    }

    try {
      Thread.sleep(5000L);
    } catch (InterruptedException e) {
      ;
    }

    List<Thread> threads = Thread.getAllStackTraces().keySet().stream()
        .filter(thread -> thread.getName().startsWith("HttpClient-"))
        .filter(thread -> thread.getName().endsWith("-SelectorManager"))
        .filter(Thread::isAlive)
        .toList();

    // currently, each invocation of super.testHello() leaves us with an active SelectorManager Thread.
    assertThat(threads).hasSize(count);

    // FIXME
    // assertThat(threads).isEmpty();

  }
}
