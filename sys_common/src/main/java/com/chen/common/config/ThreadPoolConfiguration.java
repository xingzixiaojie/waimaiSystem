package com.chen.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置
 */
@EnableAsync
@Configuration
public class ThreadPoolConfiguration {

    /** 核心线程数 */
    private static final int CORE_POOL_SIZE = 10;

    /** 最大线程数 */
    private static final int MAX_POOL_SIZE = 50;

    /** 当核心线程数达到最大时，新任务会放在队列中排队等待执行 */
    private static final int QUEUE_CAPACITY = 1000;

    /** 当线程数大于核心线程数时，多余的空闲线程存活的最长时间 */
    private static final Long KEEP_ALIVE_TIME = 1L;


    @Bean(name = "taskExecutor")
    public static ThreadPoolExecutor asyncExecutor() {
        /**
         * 1、corePoolSize线程池的核心线程数
         * 2、maximumPoolSize能容纳的最大线程数
         * 3、keepAliveTime当线程数大于核心线程数时，多余的空闲线程存活的最长时间
         * 4、unit 存活的时间单位
         * 5、workQueue 任务队列，用来储存等待执行任务的队列
         * 6、threadFactory 创建线程的工厂类
         * 7、handler 饱和策略，简单点说就是后面排队的线程就在那儿等着。
         * 被拒绝的任务在主线程中运行，所以主线程就被阻塞了，别的任务只能在被拒绝的任务执行完之后才会继续被提交到线程池执行
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return executor;
    }

}
