package dbService.exception;

/**
 * DBException class
 *
 * This class represents an exception specific to database operations.
 *
 * @author razlivinsky
 * @since 21.03.2024
 */
public class DBException extends Exception {

    /**
     * Instantiates a new Db exception.
     *
     * @param throwable the throwable that caused this exception to be thrown
     */
    public DBException(Throwable throwable) {
        super(throwable);
    }
}