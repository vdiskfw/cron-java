package io.github.vdiskg;

import java.util.concurrent.Future;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author vdisk
 * @version 1.0
 * @since 2024-10-08 22:20
 */
class CronJavaRunnerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void start() throws Exception {
        CronJavaProperties cronJavaProperties = new CronJavaProperties();
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setThreadNamePrefix("scheduling-");
        taskScheduler.initialize();
        CronJavaRunner cronJavaRunner = new CronJavaRunner(cronJavaProperties, taskScheduler);
        Future<?> task = cronJavaRunner.start();
        CronJavaShutdownHooks.setup(task, taskScheduler);
        Assertions.assertNotNull(task);
        Thread.sleep(11_000);
    }
}