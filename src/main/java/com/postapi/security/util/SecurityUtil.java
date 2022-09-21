package com.postapi.security.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityUtil {

    private SecurityUtil() {

    }

    public static String encode(String plainText) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(plainText.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert messageDigest != null;
        byte[] digest = messageDigest.digest();
        return base64Encoder(digest);
    }

    private static String base64Encoder(byte[] var) {
        return Base64.getEncoder().encodeToString(var);
    }
}
