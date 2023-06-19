package io.github.vdiskg;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author vdisk
 * @version 1.0
 * @since 2023-06-19 21:28
 */
@EnableConfigurationProperties(CronJavaProperties.class)
@Configuration
public class CronJavaConfiguration {

}
