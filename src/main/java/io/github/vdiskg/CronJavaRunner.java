package io.github.vdiskg;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author vdisk
 * @version 1.0
 * @since 2023-06-19 21:25
 */
@Slf4j
@AllArgsConstructor
@Component
public class CronJavaRunner implements ApplicationRunner {

    private static final Pattern FIVE_FIELDS_PATTERN = Pattern.compile("^(?<expression>\\S+ \\S+ \\S+ \\S+ \\S+) (?<command>.+)$");

    private static final Pattern SIX_FIELDS_PATTERN = Pattern.compile("^(?<expression>\\S+ \\S+ \\S+ \\S+ \\S+ \\S+) (?<command>.+)$");

    private final CronJavaProperties cronJavaProperties;

    private final TaskScheduler taskScheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource config = this.cronJavaProperties.getConfig();
        Assert.notNull(config, "cron config must not be null");
        CronFormat cronFormat = this.cronJavaProperties.getFormat();
        try (InputStream inputStream = config.getInputStream(); Reader reader = new InputStreamReader(inputStream); BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = this.matcher(cronFormat, line);
                String originExpression = matcher.group("expression");
                String expression = this.getExpression(cronFormat, originExpression);
                String command = matcher.group("command");
                CronTrigger cronTrigger = new CronTrigger(expression);
                log.info("start cron [expression:{}][command:{}]", expression, command);
                this.taskScheduler.schedule(new CronCommandRunner(command), cronTrigger);
            }
        }
    }

    private Matcher matcher(CronFormat cronFormat, String line) {
        switch (cronFormat) {
            case FIVE_FIELDS -> {
                Matcher matcher = FIVE_FIELDS_PATTERN.matcher(line);
                if (!matcher.find()) {
                    throw new IllegalArgumentException("Invalid cron expression: " + line);
                }
                return matcher;
            }
            case SIX_FIELDS -> {
                Matcher matcher = SIX_FIELDS_PATTERN.matcher(line);
                if (!matcher.find()) {
                    throw new IllegalArgumentException("Invalid cron expression: " + line);
                }
                return matcher;
            }
            default -> throw new IllegalStateException("Unexpected value: " + cronFormat);
        }
    }

    private String getExpression(CronFormat cronFormat, String originExpression) {
        switch (cronFormat) {
            case FIVE_FIELDS -> {
                return "0 " + originExpression;
            }
            case SIX_FIELDS -> {
                return originExpression;
            }
            default -> throw new IllegalStateException("Unexpected value: " + cronFormat);
        }
    }
}
