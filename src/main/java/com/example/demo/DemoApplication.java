package com.example.demo;

import com.example.demo.ws.HelloWebService;
import com.example.demo.ws.HelloWebServiceImpl;
import jakarta.xml.ws.Endpoint;
import java.util.List;
import org.apache.cxf.Bus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Bean
	public Endpoint checkAvailabilityEndpoint(Bus bus, HelloWebServiceImpl helloWebService) {
		EndpointImpl endpoint = new EndpointImpl(bus, helloWebService);
		endpoint.setFeatures(List.of(new LoggingFeature()));
		endpoint.publish("/hello");
		return endpoint;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
