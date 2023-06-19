package io.github.vdiskg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author vdisk
 * @version 1.0
 * @since 2023-06-19 22:00
 */
@Slf4j
@Getter
@AllArgsConstructor
@ToString
public class CronCommandRunner implements Runnable {

    private final String command;

    @Override
    public void run() {
        String[] commands = this.command.split(" ");
        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.inheritIO();
        Process process;
        try {
            process = pb.start();
        } catch (Throwable e) {
            log.error("[command:{}]start process error", this.command, e);
            return;
        }
        int exitValue;
        try {
            exitValue = process.waitFor();
        } catch (InterruptedException e) {
            log.error("[command:{}]wait for process interrupted", this.command);
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug("[command:{}]process exit value: {}", this.command, exitValue);
        }
    }
}
