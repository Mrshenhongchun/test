package com.cy.pj.common.config;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SpringAsyncConfig implements AsyncConfigurer{
	private int corePoolSize=10;
	private int maximumPoolSize=20;
	private int keepAliveTime=60;
	private int capacity=20;
	private ThreadFactory threadFactory = new ThreadFactory() {
		private AtomicLong number =new AtomicLong(100);
		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "async-thread" + number.getAndIncrement());
		}
	};

	@Bean("asuncThread")
	public ThreadPoolExecutor newThreadPoolExecutor() {
		ThreadPoolExecutor poo1 = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(capacity), threadFactory);

		return poo1;

	}
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(corePoolSize);
		pool.setMaxPoolSize(maximumPoolSize);
		pool.setKeepAliveSeconds(keepAliveTime);
		pool.setThreadFactory(threadFactory);
		pool.initialize();
		return pool;
	}
}
