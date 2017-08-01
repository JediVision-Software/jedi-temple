package com.jedivision;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.*;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class EncryptionTest {
    private static final String GLOBAL_VALUE = "JDV";
    private static final String GLOBAL_ENCRYPTED_AES_VALUE = "qApcic/FO5kfjyhYIEKs7w==";
    private static final String GLOBAL_ENCRYPTED_RC2_VALUE = "ww6eJe7S7Xw=";
    private static final String GLOBAL_ENCRYPTED_BLOWFISH_VALUE = "quFqbk4Yuxk=";
    private static final String ENCRYPTION_KEY = "Zq4t7w!z%C*F-JdV";
    private static final String ENCRYPTION_INIT_VECTOR = "Random_JDVVector";
    private static final String BLOWFISH_USERNAME = "username";
    private static final String BLOWFISH_PASSWORD = "password";
    private static final String RSA = "RSA";
    private static final String DES = "DES";

    private static KeyPair rsaKeyPair;
    private static SecretKey desKey;
    private static String globalEncryptedRsaValue;
    private static String globalEncryptedDesValue;

    @BeforeClass
    public static void runBeforeClass() throws NoSuchAlgorithmException,
                                                IllegalBlockSizeException,
                                                InvalidKeyException,
                                                BadPaddingException,
                                                NoSuchPaddingException,
                                                UnsupportedEncodingException {
        // DAS Encryption key preparing
        KeyGenerator keygenerator = KeyGenerator.getInstance(DES);
        desKey = keygenerator.generateKey();
        globalEncryptedDesValue = Encryption.encryptDES(desKey, GLOBAL_VALUE);
        // RSA Encryption keys preparing
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(1024);
        rsaKeyPair = keyPairGenerator.generateKeyPair();
        globalEncryptedRsaValue = Encryption.encryptRSA(rsaKeyPair.getPrivate(), GLOBAL_VALUE);
    }

    @Test
    public void encryptAESTest() throws NoSuchPaddingException,
                                        InvalidKeyException,
                                        UnsupportedEncodingException,
                                        IllegalBlockSizeException,
                                        BadPaddingException,
                                        NoSuchAlgorithmException,
                                        InvalidAlgorithmParameterException {
        // Act
        String encodedValue = Encryption.encryptAES(ENCRYPTION_KEY, ENCRYPTION_INIT_VECTOR, GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCRYPTED_AES_VALUE));
    }

    @Test
    public void decryptAESTest() throws NoSuchPaddingException,
                                        InvalidKeyException,
                                        UnsupportedEncodingException,
                                        IllegalBlockSizeException,
                                        BadPaddingException,
                                        NoSuchAlgorithmException,
                                        InvalidAlgorithmParameterException {
        // Act
        String decodedValue = Encryption.decryptAES(ENCRYPTION_KEY, ENCRYPTION_INIT_VECTOR, GLOBAL_ENCRYPTED_AES_VALUE);

        // Assert
        assertThat(decodedValue, equalTo(GLOBAL_VALUE));
    }

    @Test
    public void encryptBlowfishTest() throws IllegalBlockSizeException,
                                            InvalidKeyException,
                                            BadPaddingException,
                                            NoSuchAlgorithmException,
                                            NoSuchPaddingException,
                                            UnsupportedEncodingException {
        // Act
        String encodedValue = Encryption.encryptBlowfish(BLOWFISH_USERNAME, BLOWFISH_PASSWORD, GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCRYPTED_BLOWFISH_VALUE));
    }

    @Test
    public void decryptBlowfishTest() throws IllegalBlockSizeException,
                                            InvalidKeyException,
                                            BadPaddingException,
                                            NoSuchAlgorithmException,
                                            NoSuchPaddingException,
                                            UnsupportedEncodingException {
        // Act
        String decodedValue = Encryption.decryptBlowfish(BLOWFISH_USERNAME, BLOWFISH_PASSWORD, GLOBAL_ENCRYPTED_BLOWFISH_VALUE);

        // Assert
        assertThat(decodedValue, equalTo(GLOBAL_VALUE));
    }

    @Test
    public void encryptDESTest() throws IllegalBlockSizeException,
                                        InvalidKeyException,
                                        BadPaddingException,
                                        NoSuchAlgorithmException,
                                        NoSuchPaddingException,
                                        UnsupportedEncodingException {
        // Act
        String encodedValue = Encryption.encryptDES(desKey, GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(globalEncryptedDesValue));
    }

    @Test
    public void decryptDESTest() throws IllegalBlockSizeException,
                                        InvalidKeyException,
                                        BadPaddingException,
                                        NoSuchAlgorithmException,
                                        NoSuchPaddingException {
        // Act
        String encodedValue = Encryption.decryptDES(desKey, globalEncryptedDesValue);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_VALUE));
    }

    @Test
    public void encryptRC2Test() throws NoSuchPaddingException,
                                        InvalidKeyException,
                                        NoSuchAlgorithmException,
                                        IllegalBlockSizeException,
                                        BadPaddingException,
                                        InvalidAlgorithmParameterException,
                                        UnsupportedEncodingException {
        // Act
        String encodedValue = Encryption.encryptRC2(ENCRYPTION_KEY, ENCRYPTION_INIT_VECTOR, GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCRYPTED_RC2_VALUE));
    }

    @Test
    public void decryptRC2Test() throws NoSuchPaddingException,
                                        InvalidKeyException,
                                        NoSuchAlgorithmException,
                                        IllegalBlockSizeException,
                                        BadPaddingException,
                                        InvalidAlgorithmParameterException,
                                        UnsupportedEncodingException {
        // Act
        String encodedValue = Encryption.decryptRC2(ENCRYPTION_KEY, ENCRYPTION_INIT_VECTOR, GLOBAL_ENCRYPTED_RC2_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_VALUE));
    }

    @Test
    public void encryptRSATest() throws IllegalBlockSizeException,
                                        InvalidKeyException,
                                        BadPaddingException,
                                        NoSuchAlgorithmException,
                                        NoSuchPaddingException,
                                        UnsupportedEncodingException {
        // Act
        String encodedValue = Encryption.encryptRSA(rsaKeyPair.getPrivate(), GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(globalEncryptedRsaValue));
    }

    @Test
    public void decryptRSATest() throws IllegalBlockSizeException,
                                        InvalidKeyException,
                                        BadPaddingException,
                                        NoSuchAlgorithmException,
                                        NoSuchPaddingException {
        // Act
        String decodedValue = Encryption.decryptRSA(rsaKeyPair.getPublic(), globalEncryptedRsaValue);

        // Assert
        assertThat(decodedValue, equalTo(GLOBAL_VALUE));
    }
}
