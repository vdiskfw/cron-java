package io.github.vdiskg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author vdisk
 * @version 1.0
 * @since 2023-06-19 21:24
 */
@EnableScheduling
@SpringBootApplication
public class CronJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CronJavaApplication.class, args);
    }

}
