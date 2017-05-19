package com.jedivision;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class HashTest {

    private static final String md5Value = "JDV";
    private static final String md5EncodedValue = "d7df1f0388aa696ce6ca0750afdebda5";

    @Test
    public void md5ViaPureJava() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.md5ViaPureJava(md5Value);

        // Assert
        assertThat(encodedValue, equalTo(encodedValue));
    }

    @Test
    public void md5ViaCommonsCodec() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.md5ViaCommonsCodec(md5Value);

        // Assert
        assertThat(encodedValue, equalTo(encodedValue));
    }

}
