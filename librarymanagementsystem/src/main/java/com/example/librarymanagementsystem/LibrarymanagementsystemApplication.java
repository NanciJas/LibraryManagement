package com.example.librarymanagementsystem;

import java.util.Properties;
import java.util.concurrent.Executor;

import javax.mail.MessagingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.librarymanagementsystem.service.EmailService;



@SpringBootApplication
@EnableAsync

public class LibrarymanagementsystemApplication {

	
	/*
	 * @Bean public ThreadPoolTaskExecutor taskExecutor() { ThreadPoolTaskExecutor
	 * executor = new ThreadPoolTaskExecutor(); executor.setCorePoolSize(2);
	 * executor.setMaxPoolSize(2); executor.setQueueCapacity(500);
	 * executor.setThreadNamePrefix("MyAsyncThread-");
	 * executor.setRejectedExecutionHandler((r, executor1) ->
	 * log.warn("Task rejected, thread pool is full and queue is also full"));
	 * executor.initialize(); return executor; }
	 */
	 
	 
	 @Bean(name = "threadPoolTaskExecutor")
	 public Executor asyncExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(4);
	    executor.setMaxPoolSize(4);
	    executor.setQueueCapacity(50);
	    executor.setThreadNamePrefix("AsynchThread::");
	    executor.initialize();
	    return executor;
	 }
	
	public static void main(String[] args) {
		SpringApplication.run(LibrarymanagementsystemApplication.class, args);
	
	}
	


}
