package com.ghorabaa.cultureguide.Utilities;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Created by Ahmed Ibrahim on 4/27/18.
 */

public class PasswordEncrypter {

    private PasswordEncrypter(){};
    public static String encrypt(String text) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(text.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            String hex = String.format("%064x", new BigInteger(1, digest));
            return hex;
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

}
