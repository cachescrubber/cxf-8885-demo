package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.ws.HelloRequest;
import com.example.demo.ws.HelloResponse;
import com.example.demo.ws.HelloWebService;
import java.util.List;
import org.apache.cxf.Bus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.spring.JaxWsProxyFactoryBeanDefinitionParser;

class CxfJaxWsClientTest {

  HelloWebService helloWebService(Bus bus) {
    JaxWsProxyFactoryBeanDefinitionParser.JAXWSSpringClientProxyFactoryBean factoryBean = new JaxWsProxyFactoryBeanDefinitionParser.JAXWSSpringClientProxyFactoryBean();
    factoryBean.setServiceClass(HelloWebService.class);
    factoryBean.setBus(bus);
    factoryBean.setFeatures(List.of(new LoggingFeature()));
    factoryBean.setAddress("http://localhost:8080/services/hello");
    return (HelloWebService) factoryBean.create();
  }

  void sayHello(Bus bus) {

    HelloWebService client = helloWebService(bus);
    HelloResponse helloResponse = client.hello(new HelloRequest("Klaus"));
    assertThat(helloResponse.getGreeting()).isEqualTo("Hello, Klaus");
  }

}
