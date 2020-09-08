package io.tech1;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Encryption {
    // Constants
    private static final String AES = "AES";
    private static final String RC2 = "RC2";
    private static final String BLOWFISH = "Blowfish";
    private static final String RSA = "RSA";
    // Cipher instances constants (Algorithm name/Mode (optional)/Padding scheme (optional))
    private static final String CIPHER_AES_INSTANCE = "AES/CBC/PKCS5PADDING";
    private static final String CIPHER_DES_INSTANCE = "DES/ECB/PKCS5PADDING";
    private static final String CIPHER_RC2_INSTANCE = "RC2/CBC/PKCS5PADDING";

    public static String encryptAES(String key, String initVector, String value) throws UnsupportedEncodingException,
                                                                                        NoSuchPaddingException,
                                                                                        NoSuchAlgorithmException,
                                                                                        InvalidAlgorithmParameterException,
                                                                                        InvalidKeyException,
                                                                                        BadPaddingException,
                                                                                        IllegalBlockSizeException {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF_8));
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(UTF_8), AES);
        Cipher cipher = Cipher.getInstance(CIPHER_AES_INSTANCE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        byte[] encrypted = cipher.doFinal(value.getBytes(UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decryptAES(String key, String initVector, String encrypted) throws UnsupportedEncodingException,
                                                                                            NoSuchPaddingException,
                                                                                            NoSuchAlgorithmException,
                                                                                            InvalidAlgorithmParameterException,
                                                                                            InvalidKeyException,
                                                                                            BadPaddingException,
                                                                                            IllegalBlockSizeException {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF_8));
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(UTF_8), AES);
        Cipher cipher = Cipher.getInstance(CIPHER_AES_INSTANCE);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(original);
    }

    public static String encryptBlowfish(String username, String password, String value) throws NoSuchPaddingException,
                                                                                                NoSuchAlgorithmException,
                                                                                                InvalidKeyException,
                                                                                                BadPaddingException,
                                                                                                IllegalBlockSizeException,
                                                                                                UnsupportedEncodingException {
        byte[] keyData = (username + password).getBytes(UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, BLOWFISH);
        Cipher cipher = Cipher.getInstance(BLOWFISH);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encrypted = cipher.doFinal(value.getBytes(UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decryptBlowfish(String username, String password, String encrypted) throws NoSuchPaddingException,
                                                                                                    NoSuchAlgorithmException,
                                                                                                    InvalidKeyException,
                                                                                                    BadPaddingException,
                                                                                                    IllegalBlockSizeException,
                                                                                                    UnsupportedEncodingException {
        byte[] keyData = (username + password).getBytes(UTF_8);
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
                                                                            IllegalBlockSizeException,
                                                                            UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(CIPHER_DES_INSTANCE);
        cipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encrypted = cipher.doFinal(value.getBytes(UTF_8));
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

    public static String encryptRC2(String key, String initVector, String value) throws NoSuchPaddingException,
                                                                                        NoSuchAlgorithmException,
                                                                                        InvalidAlgorithmParameterException,
                                                                                        InvalidKeyException,
                                                                                        BadPaddingException,
                                                                                        IllegalBlockSizeException,
                                                                                        UnsupportedEncodingException {
        RC2ParameterSpec ivSpec = new RC2ParameterSpec(key.getBytes(UTF_8).length, initVector.getBytes(UTF_8));
        Cipher cipher = Cipher.getInstance(CIPHER_RC2_INSTANCE);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(UTF_8), RC2), ivSpec);
        byte[] encrypted = cipher.doFinal(value.getBytes(UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decryptRC2(String key, String initVector, String encrypted) throws NoSuchPaddingException,
                                                                                            NoSuchAlgorithmException,
                                                                                            InvalidAlgorithmParameterException,
                                                                                            InvalidKeyException,
                                                                                            BadPaddingException,
                                                                                            IllegalBlockSizeException,
                                                                                            UnsupportedEncodingException {
        RC2ParameterSpec ivSpec = new RC2ParameterSpec(key.getBytes(UTF_8).length, initVector.getBytes(UTF_8));
        Cipher cipher = Cipher.getInstance(CIPHER_RC2_INSTANCE);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(UTF_8), RC2), ivSpec);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(original);
    }

    public static String encryptRSA(PrivateKey privateKey, String value) throws NoSuchPaddingException,
                                                                                NoSuchAlgorithmException,
                                                                                InvalidKeyException,
                                                                                BadPaddingException,
                                                                                IllegalBlockSizeException,
                                                                                UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encrypted = cipher.doFinal(value.getBytes(UTF_8));
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
