package com.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@Configuration
public class taskScheduler {
    @Bean(name = "commonTaskScheduler")
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler(); // You can replace with ThreadPoolTaskScheduler if needed
    }
}
