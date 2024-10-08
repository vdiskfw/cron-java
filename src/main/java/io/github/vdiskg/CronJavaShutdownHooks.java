package io.github.vdiskg;

import java.util.concurrent.Future;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author vdisk
 * @version 1.0
 * @since 2024-10-08 22:31
 */
@Slf4j
public class CronJavaShutdownHooks {

    public static void setup(Future<?> task, ThreadPoolTaskScheduler taskScheduler) {
        Thread shutdownHook = new Thread(() -> {
            log.info("cron-java shutting down");
            task.cancel(true);
            taskScheduler.shutdown();
            log.info("cron-java terminated");
        });
        shutdownHook.setName("jcron-shutdown");
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }
}
