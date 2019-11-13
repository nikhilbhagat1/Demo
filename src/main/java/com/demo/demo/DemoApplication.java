package com.demo.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication(scanBasePackages="com.demo.demo.model")
public class DemoApplication {

	public static void main(String[] args) {

		System.out.println("+++++++++++++++Starts");
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
			String[] beans = ctx.getBeanDefinitionNames();
		//for(String s : beans) System.out.println(s);
		System.out.println("---------------Ends");
	}




}
