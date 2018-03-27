package com.target.myretail.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@Lazy
public class AsyncExecutor {
    @Bean("productTaskExecutor")
    @Lazy
    public TaskExecutor productTaskExecutorProcess(@Value("${taskExecutor.corepool.size}") Integer corePoolSize, @Value("${taskExecutor.maxpool.size}") Integer maxPoolSize, @Value("${taskExecutor.queue.size}") Integer queueSize) {
        return createTaskExecutor(corePoolSize, maxPoolSize, queueSize);
    }

    public TaskExecutor createTaskExecutor(Integer corePoolSize, Integer maxPoolSize, Integer queueSize) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setQueueCapacity(queueSize);
        return taskExecutor;
    }
}
