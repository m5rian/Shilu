package com.github.m5rian.shilu.client.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Marian
 * A help logging class, to log messages more nicley.
 */
public class Logger {
    /**
     * Resets the current colour.
     */
    public static final String RESET = "\033[0m";

    public static final String CYAN_BRIGHT = "\033[0;96m";
    public static final String GREEN_BRIGHT = "\033[0;92m";
    public static final String YELLOW_BRIGHT = "\033[0;93m";

    /**
     * Print some information out on the console.
     *
     * @param data The information to write in the console.
     */
    public static void info(Object... data) {
        for (Object out : data)
            System.out.printf("%s[%s]%s %s[INFO]%s %s%s%s%n", CYAN_BRIGHT, new SimpleDateFormat("HH:mm:ss").format(new Date()), RESET, GREEN_BRIGHT, RESET, YELLOW_BRIGHT, out, RESET);
    }

}
