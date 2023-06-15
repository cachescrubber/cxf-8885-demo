package com.example.demo.ws;

import static com.example.demo.ws.HelloWebService.NAMESPACE;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"name"})
@XmlRootElement(namespace = NAMESPACE, name = "helloRequest")
public class HelloRequest {
  @XmlElement(required = true)
  private String name;

  public HelloRequest() {
    super();
  }

  public HelloRequest(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
