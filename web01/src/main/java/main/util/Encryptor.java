package main.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: C/S efpms system
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company: Guangzhou Teczasky
 * </p>
 *
 * @author Awen
 * @version 1.0
 */
public class Encryptor {
    public static String encrypt(String plainText) {
        MessageDigest md;
        BASE64Encoder encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();

        try {
            md = MessageDigest.getInstance("MD5");
            String encryptText = encoder.encode(md.digest(decoder
                    .decodeBuffer(plainText)));
            return encryptText;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Description: 16位小写MD5
     */
    public static String parseStrToMd5U16(String str){
        String result = DigestUtils.md5Hex(str);
        if (result != null){
            result = result.toLowerCase().substring(8, 24);
        }
        return result;
    }
}
