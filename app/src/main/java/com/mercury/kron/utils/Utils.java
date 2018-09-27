package com.mercury.kron.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    // получаем md5 строки
    public static final String md5(String val) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(val.getBytes());

            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2) h = "0" + h;
                hexString.append(h);

            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}