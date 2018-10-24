package com.vinux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableHystrixDashboard//开启hystrixDashboard
@EnableZuulProxy//开启zuul的功能
@EnableDiscoveryClient
//@EnableHystrix //注解开启Hystrix
@EnableFeignClients//@EnableFeignClients注解开启Feign的功能
@RestController
@SpringBootApplication
public class PushManagerApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PushManagerApisApplication.class, args);
	}
	
	@RequestMapping("gg")
	public String gg() {
		return "你好";
	}
}
