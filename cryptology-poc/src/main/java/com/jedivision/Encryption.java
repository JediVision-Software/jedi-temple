package com.jedivision;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

public class Encryption {
    // Constants
    private static final String AES = "AES";
    private static final String BLOWFISH = "Blowfish";
    private static final String RSA = "RSA";
    private static final String UTF8 = "UTF-8";
    // Cipher instances constants (Algorithm name/Mode (optional)/Padding scheme (optional))
    private static final String CIPHER_AES_INSTANCE = "AES/CBC/PKCS5PADDING";
    private static final String CIPHER_DES_INSTANCE = "DES/ECB/PKCS5PADDING";

    public static String encryptAES(String key, String initVector, String value) throws UnsupportedEncodingException,
                                                                                        NoSuchPaddingException,
                                                                                        NoSuchAlgorithmException,
                                                                                        InvalidAlgorithmParameterException,
                                                                                        InvalidKeyException,
                                                                                        BadPaddingException,
                                                                                        IllegalBlockSizeException {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF8));
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(UTF8), AES);
        Cipher cipher = Cipher.getInstance(CIPHER_AES_INSTANCE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        byte[] encrypted = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decryptAES(String key, String initVector, String encrypted) throws UnsupportedEncodingException,
                                                                                            NoSuchPaddingException,
                                                                                            NoSuchAlgorithmException,
                                                                                            InvalidAlgorithmParameterException,
                                                                                            InvalidKeyException,
                                                                                            BadPaddingException,
                                                                                            IllegalBlockSizeException {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF8));
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(UTF8), AES);
        Cipher cipher = Cipher.getInstance(CIPHER_AES_INSTANCE);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(original);
    }

    public static String encryptBlowfish(String username, String password, String value) throws NoSuchPaddingException,
                                                                                            NoSuchAlgorithmException,
                                                                                            InvalidKeyException,
                                                                                            BadPaddingException,
                                                                                            IllegalBlockSizeException {
        byte[] keyData = (username + password).getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, BLOWFISH);
        Cipher cipher = Cipher.getInstance(BLOWFISH);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encrypted = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decryptBlowfish(String username, String password, String encrypted) throws NoSuchPaddingException,
                                                                                                NoSuchAlgorithmException,
                                                                                                InvalidKeyException,
                                                                                                BadPaddingException,
                                                                                                IllegalBlockSizeException {
        byte[] keyData = (username + password).getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, BLOWFISH);
        Cipher cipher = Cipher.getInstance(BLOWFISH);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(original);
    }

    public static String encryptDES(SecretKey desKey, String value) throws NoSuchPaddingException,
                                                                            NoSuchAlgorithmException,
                                                                            InvalidKeyException,
                                                                            BadPaddingException,
                                                                            IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(CIPHER_DES_INSTANCE);
        cipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encrypted = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decryptDES(SecretKey desKey, String encrypted) throws NoSuchPaddingException,
                                                                            NoSuchAlgorithmException,
                                                                            InvalidKeyException,
                                                                            BadPaddingException,
                                                                            IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(CIPHER_DES_INSTANCE);
        cipher.init(Cipher.DECRYPT_MODE, desKey);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(original);
    }

    public static String encryptRSA(PrivateKey privateKey, String value) throws NoSuchPaddingException,
                                                                                NoSuchAlgorithmException,
                                                                                InvalidKeyException,
                                                                                BadPaddingException,
                                                                                IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encrypted = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decryptRSA(PublicKey publicKey, String encrypted) throws NoSuchPaddingException,
                                                                                    NoSuchAlgorithmException,
                                                                                    InvalidKeyException,
                                                                                    BadPaddingException,
                                                                                    IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(original);
    }
}
