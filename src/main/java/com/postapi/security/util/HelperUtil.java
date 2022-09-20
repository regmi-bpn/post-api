package com.postapi.security.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;

@Slf4j
public class HelperUtil {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwzyz";

    private HelperUtil() {

    }

//    public static String generateAlphaNumeric(int length) {
//        int leftLimit = 48;
//        int rightLimit = 122;
//        Random random = new Random();
//        return random.ints(leftLimit, rightLimit + 1)
//                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//                .limit(length)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
//    }

    public static String generateNumeric(int length) {
        int leftLimit = 48;
        int rightLimit = 57;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1).limit(length).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public static LocalDateTime getLocalDateTimeOfUTC() {
        return ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
    }

}
