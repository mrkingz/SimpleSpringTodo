package com.simplespringtodo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Custom date.
 */
public class CustomDate {

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public String getTimestamp () {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-dd-MM HH:mm:ss a z");
        return dateFormat.format(new Date()).toUpperCase();
    };
}
