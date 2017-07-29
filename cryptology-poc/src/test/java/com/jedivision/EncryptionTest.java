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
    private static final String KEY = "Zq4t7w!z%C*F-JdV";
    private static final String INIT_VECTOR = "Random_JDVVector";

    @Test
    public void encryptAESTest() throws NoSuchPaddingException,
                                        InvalidKeyException,
                                        UnsupportedEncodingException,
                                        IllegalBlockSizeException,
                                        BadPaddingException,
                                        NoSuchAlgorithmException,
                                        InvalidAlgorithmParameterException {
        // Act
        String encodedValue = Encryption.encryptAES(KEY, INIT_VECTOR, GLOBAL_VALUE);

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
        String decodedValue = Encryption.decryptAES(KEY, INIT_VECTOR, GLOBAL_ENCRYPTED_AES_VALUE);

        // Assert
        assertThat(decodedValue, equalTo(GLOBAL_VALUE));
    }
}
