package io;

/**
 * DataResult class
 *
 * This class provides access to data information such as URL, name, and password.
 *
 * @author razlivinsky
 * @since 23.03.2024
 */
public class DataResult {

    /**
     * The constant URL.
     * This constant represents the URL key for accessing data.
     */
    public static final String URL = "url";

    /**
     * The constant NAME.
     * This constant represents the name key for accessing data.
     */
    public static final String NAME = "name";

    /**
     * The constant PASS.
     * This constant represents the password key for accessing data.
     */
    public static final String PASS = "pass";

    /**
     * Gets the URL.
     *
     * @return the URL value
     */
    public static String getUrl() {
        return DataReader.getData(URL);
    }

    /**
     * Gets the name.
     *
     * @return the name value
     */
    public static String getName() {
        return DataReader.getData(NAME);
    }

    /**
     * Gets the password.
     *
     * @return the password value
     */
    public static String getPassword() {
        return DataReader.getData(PASS);
    }
}