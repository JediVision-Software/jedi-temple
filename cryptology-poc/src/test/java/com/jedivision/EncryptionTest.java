package com.jedivision;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class EncryptionTest {
    private static final String GLOBAL_VALUE = "JDV";
    private static final String GLOBAL_ENCRYPTED_AES_VALUE = "qApcic/FO5kfjyhYIEKs7w==";
    private static final String GLOBAL_ENCRYPTED_BLOWFISH_VALUE = "quFqbk4Yuxk=";
    private static final String AES_KEY = "Zq4t7w!z%C*F-JdV";
    private static final String AES_INIT_VECTOR = "Random_JDVVector";
    private static final String BLOWFISH_USERNAME = "username";
    private static final String BLOWFISH_PASSWORD = "password";

    @Test
    public void encryptAESTest() throws NoSuchPaddingException,
                                        InvalidKeyException,
                                        UnsupportedEncodingException,
                                        IllegalBlockSizeException,
                                        BadPaddingException,
                                        NoSuchAlgorithmException,
                                        InvalidAlgorithmParameterException {
        // Act
        String encodedValue = Encryption.encryptAES(AES_KEY, AES_INIT_VECTOR, GLOBAL_VALUE);

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
        String decodedValue = Encryption.decryptAES(AES_KEY, AES_INIT_VECTOR, GLOBAL_ENCRYPTED_AES_VALUE);

        // Assert
        assertThat(decodedValue, equalTo(GLOBAL_VALUE));
    }

    @Test
    public void encryptBlowfishTest() throws IllegalBlockSizeException,
                                            InvalidKeyException,
                                            BadPaddingException,
                                            NoSuchAlgorithmException,
                                            NoSuchPaddingException {
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
                                            NoSuchPaddingException {
        // Act
        String decodedValue = Encryption.decryptBlowfish(BLOWFISH_USERNAME, BLOWFISH_PASSWORD, GLOBAL_ENCRYPTED_BLOWFISH_VALUE);

        // Assert
        assertThat(decodedValue, equalTo(GLOBAL_VALUE));
    }
}
