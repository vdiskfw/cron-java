package io.github.vdiskg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vdisk
 * @version 1.0
 * @since 2023-06-19 21:26
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "vdisk.cron-java")
public class CronJavaProperties {

    /**
     * cron config
     */
    private Resource config = new ClassPathResource("META-INF/hello-world.cron");

    /**
     * cron expression format
     */
    private CronFormat format = CronFormat.SIX_FIELDS;
}
