package io.github.vdiskg;

/**
 * @author vdisk
 * @version 1.0
 * @since 2023-06-19 21:41
 */

public enum CronFormat {

    /**
     * 5 fields
     *   ┌───────────── minute (0 - 59)
     *   │ ┌───────────── hour (0 - 23)
     *   │ │ ┌───────────── day of the month (1 - 31)
     *   │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
     *   │ │ │ │ ┌───────────── day of the week (0 - 7)
     *   │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
     *   │ │ │ │ │
     *   * * * * *
     */
    FIVE_FIELDS,

    /**
     * 6 fields
     *   ┌───────────── second (0-59)
     *   │ ┌───────────── minute (0 - 59)
     *   │ │ ┌───────────── hour (0 - 23)
     *   │ │ │ ┌───────────── day of the month (1 - 31)
     *   │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
     *   │ │ │ │ │ ┌───────────── day of the week (0 - 7)
     *   │ │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
     *   │ │ │ │ │ │
     *   * * * * * *
     */
    SIX_FIELDS,

}
