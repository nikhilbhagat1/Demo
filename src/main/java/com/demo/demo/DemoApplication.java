package com.demo.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;




@SpringBootApplication(scanBasePackages="com.demo.demo")
public class DemoApplication {



	@Bean("threadPoolTaskExecutor")
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(1000);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("Async-");
		return executor;
	}

	public static void main(String[] args) {

		System.out.println("+++++++++++++++Starts");
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
			String[] beans = ctx.getBeanDefinitionNames();
		//for(String s : beans) System.out.println(s);
		System.out.println("---------------Ends");
	}




}
