package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Log class provides logging functionality for informational, warning, error, fatal, and debug messages.
 *
 * This class allows the logging of messages using different log levels, including info, warn, error, fatal, and debug.
 *
 * @author razlivinsky
 * @since 23.03.2024
 */
public abstract class Log {
    private static Logger log = LogManager.getLogger(Log.class);

    /**
     * Logs an informational message.
     *
     * @param message the informational message to be logged
     */
    public static void info (String message) {
        log.info(message);
    }

    /**
     * Logs a warning message.
     *
     * @param message the warning message to be logged
     */
    public static void warn (String message) {
        log.warn(message);
    }

    /**
     * Logs an error message.
     *
     * @param message the error message to be logged
     */
    public static void error (String message) {
        log.error(message);
    }

    /**
     * Logs a fatal message.
     *
     * @param message the fatal message to be logged
     */
    public static void fatal (String message) {
        log.fatal(message);
    }

    /**
     * Logs a debug message.
     *
     * @param message the debug message to be logged
     */
    public static void debug (String message) {
        log.debug(message);
    }
}