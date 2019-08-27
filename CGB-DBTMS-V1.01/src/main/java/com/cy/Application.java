package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync//启用异步操作（底层会创建一个池）
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
