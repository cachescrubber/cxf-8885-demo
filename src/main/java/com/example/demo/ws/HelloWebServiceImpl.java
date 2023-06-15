package com.example.demo.ws;

import org.springframework.stereotype.Component;

@Component
public class HelloWebServiceImpl implements HelloWebService {

  @Override
  public HelloResponse hello(HelloRequest helloRequest) {
    String greeting = "Hello, " + helloRequest.getName();
    return new HelloResponse(greeting);
  }
}
