package com.example.demo.ws;

import static com.example.demo.ws.HelloWebService.NAMESPACE;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"greeting"})
@XmlRootElement(namespace = NAMESPACE, name = "helloResponse")
public class HelloResponse {

  @XmlElement(required = true)
  private String greeting;

  public HelloResponse() {
    super();
  }

  public HelloResponse(String greeting) {
    this.greeting = greeting;
  }

  public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }
}
