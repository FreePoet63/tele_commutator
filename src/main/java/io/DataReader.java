package io;

import java.util.ResourceBundle;

/**
 * DataReader class
 *
 * This class provides methods for reading data and query strings from resource bundles.
 *
 * @author razlivinsky
 * @since 23.03.2024
 */
public class DataReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
    private static ResourceBundle resourceBundleQuery = ResourceBundle.getBundle("query");

    /**
     * Retrieves a data string from the resource bundle.
     *
     * @param key the key to retrieve the data
     * @return the data string corresponding to the given key
     */
    public static String getData(String key){
        return resourceBundle.getString(key);
    }

    /**
     * Retrieves a query string from the resource bundle.
     *
     * @param key the key to retrieve the query
     * @return the query string corresponding to the given key
     */
    public static String getQuery(String key){
        return resourceBundleQuery.getString(key);
    }
}