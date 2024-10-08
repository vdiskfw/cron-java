package io.github.vdiskg;

import java.util.concurrent.Future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author vdisk
 * @version 1.0
 * @since 2023-06-19 21:24
 */
@SpringBootApplication
public class CronJavaApplication {

    public static void main(String[] args) throws Exception {
        CronJavaProperties cronJavaProperties;
        try (ConfigurableApplicationContext context = SpringApplication.run(CronJavaApplication.class, args)){
            cronJavaProperties = context.getBean(CronJavaProperties.class);
        }
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setThreadNamePrefix("scheduling-");
        taskScheduler.initialize();
        CronJavaRunner cronJavaRunner = new CronJavaRunner(cronJavaProperties, taskScheduler);
        Future<?> task = cronJavaRunner.start();
        CronJavaShutdownHooks.setup(task, taskScheduler);
    }

}
