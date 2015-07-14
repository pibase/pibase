package com.justinoboyle.pibase.core.java.util;

import java.net.URLDecoder;

public class UtilHTTP {

    private static final String DEFAULT_ENCODING = "UTF-8";

    public static String decode(String input, String encoding) {
        try {
            return URLDecoder.decode(input, encoding);
        } catch (Exception ex) {
            return input;
        }
    }

    public static String decode(String input) {
        return decode(input, DEFAULT_ENCODING);
    }

}
