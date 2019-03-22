package de.rwth.swc.qrs2019.execution;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingHelper {

    public static void info(String info) {
        System.out.println(now() + " - INFO: " + info);
    }

    private static String now() {

        String datePattern = "HH:mm:ss";
        DateTimeFormatter df = DateTimeFormatter.ofPattern(datePattern);

        LocalDateTime now = LocalDateTime.now();

        return df.format(now);
    }
}
