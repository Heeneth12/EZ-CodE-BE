package com.ezcode.utils;

public class StringUtils {

    /**
     * Checks if a string is not null, not empty, and not just whitespace.
     *
     * @param str The string to check
     * @return true if the string is not null, not empty, and not only whitespace
     */
    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * Checks if a string is null, empty, or contains only whitespace.
     *
     * @param str The string to check
     * @return true if the string is null, empty, or only whitespace
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Checks if a string is null or empty.
     *
     * @param str The string to check
     * @return true if the string is null or empty
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Checks if a string is not empty (not null and not an empty string).
     *
     * @param str The string to check
     * @return true if the string is not null and not empty
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    /**
     * Trims a string and returns an empty string if it's null.
     *
     * @param str The string to trim
     * @return Trimmed string or an empty string if input is null
     */
    public static String defaultIfNull(String str) {
        return str == null ? "" : str.trim();
    }
}
