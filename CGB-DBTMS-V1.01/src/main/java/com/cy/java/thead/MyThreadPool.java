package com.cy.java.thead;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
	//
	static int corePoolSize = 2;
	// 最大池容量
	static int maximumPoolSize = 3;
	// 存活时间
	static long keepAliveTime = 60;
	// 阻塞队列
	static BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(2);

	public static void main(String[] args) {
		ThreadPoolExecutor tPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
				TimeUnit.SECONDS, workQueue);
		//通过池中线程执行
		tPool.execute(new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println(name + "-->001");
				try {Thread.sleep(3000);} catch (Exception e) {}

			}
		});
		tPool.execute(new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println(name + "-->002");
				try {Thread.sleep(3000);} catch (Exception e) {}
			}
		});
		tPool.execute(new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println(name + "-->003");
				

			}
		});
		tPool.execute(new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println(name + "-->004");

			}
		});
		tPool.execute(new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println(name + "-->005");
				try {Thread.sleep(1000);} catch (Exception e) {}
			}
		});
		tPool.execute(new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println(name + "-->006");

			}
		});		
		
		
	}

}


