package com.example.demo.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

/**
 * Simple Soap Web Service for testing purposes.
 *
 */
@WebService(targetNamespace = HelloWebService.NAMESPACE, name = "HelloWebService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface HelloWebService {
  String NAMESPACE = "http://ws.demo.example.com/";

  @WebMethod(operationName = "hello")
  @WebResult(name = "HelloResponse", targetNamespace = NAMESPACE, partName = "response")
  HelloResponse hello(
      @WebParam(partName = "request", name = "HelloRequest", targetNamespace = NAMESPACE)
      HelloRequest helloRequest);

}
