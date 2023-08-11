package com.example.demo;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.ws.HelloRequest;
import com.example.demo.ws.HelloResponse;
import com.example.demo.ws.HelloWebService;
import java.util.List;
import org.apache.cxf.Bus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.spring.JaxWsProxyFactoryBeanDefinitionParser;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class Cxf8885Test {

  HelloWebService helloWebService(Bus bus) {
    JaxWsProxyFactoryBeanDefinitionParser.JAXWSSpringClientProxyFactoryBean factoryBean = new JaxWsProxyFactoryBeanDefinitionParser.JAXWSSpringClientProxyFactoryBean();
    factoryBean.setServiceClass(HelloWebService.class);
    factoryBean.setBus(bus);
    factoryBean.setFeatures(List.of(new LoggingFeature()));
    factoryBean.setAddress("http://localhost:8080/services/hello");
    return (HelloWebService) factoryBean.create();
  }

  @Test
  @Order(1)
  void sayHello_1(@Autowired Bus bus) throws Exception {

    int count = 500;
    {
      HelloWebService client = helloWebService(bus);
      for (int i = 0; i < count; i++) {
        HelloResponse helloResponse = client.hello(new HelloRequest("Klaus"));
        assertThat(helloResponse.getGreeting()).isEqualTo("Hello, Klaus");
      }
      if (client instanceof AutoCloseable autoCloseable) {
        autoCloseable.close();
      }
    }

    try {
      Thread.sleep(5000L);
    } catch (InterruptedException e) {
      ;
    }

    System.gc();

    List<Thread> threads = Thread.getAllStackTraces().keySet().stream()
        .filter(thread -> thread.getName().startsWith("HttpClient-"))
        .filter(thread -> thread.getName().endsWith("-SelectorManager"))
        .filter(Thread::isAlive)
        .toList();

    // currently, each invocation of super.testHello() leaves us with an active SelectorManager Thread.
    // assertThat(threads).hasSize(1);

    assertThat(threads).isEmpty();
  }

  @Test
  @Order(2)
  void sayHello_2(@Autowired Bus bus) throws Exception {

    int count = 500;

    for (int i = 0; i < count; i++) {
      HelloWebService client = helloWebService(bus);
      HelloResponse helloResponse = client.hello(new HelloRequest("Klaus"));
      assertThat(helloResponse.getGreeting()).isEqualTo("Hello, Klaus");
      if (client instanceof AutoCloseable autoCloseable) {
        autoCloseable.close();
      }
    }

    try {
      Thread.sleep(30000L);
    } catch (InterruptedException e) {
      ;
    }

    System.gc();

    List<Thread> threads = Thread.getAllStackTraces().keySet().stream()
        .filter(thread -> thread.getName().startsWith("HttpClient-"))
        .filter(thread -> thread.getName().endsWith("-SelectorManager"))
        .filter(Thread::isAlive)
        .toList();

    // currently, each invocation of super.testHello() leaves us with an active SelectorManager Thread.
    //assertThat(threads).hasSizeGreaterThanOrEqualTo(count);
    assertThat(threads).isEmpty();
  }

}
