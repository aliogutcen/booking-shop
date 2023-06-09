package com.ogutcenali.utility;

import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class PasswordEncrypt {


    public static byte[] getSHA(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }


    public String generateEncryptPassword(String password) {
        try {
            byte[] passwordGetSHA = getSHA(password);
            return toHexString(passwordGetSHA);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }


}
