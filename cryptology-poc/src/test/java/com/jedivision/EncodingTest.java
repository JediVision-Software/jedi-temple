package com.jedivision;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class EncodingTest {

    private static final String GLOBAL_VALUE = "JDV";
    private static final String GLOBAL_SPECIAL_VALUE = "JDV©";
    private static final String URL_VALUE = "http://www.jedivision-software.com";
    private static final String ENCODED_URL_VALUE = "http%3A%2F%2Fwww.jedivision-software.com";
    private static final String GLOBAL_ENCODED_BASE_64_VALUE = "SkRW";
    private static final byte[] GLOBAL_ENCODED_ASCII_UTF_8_VALUE = {74, 68, 86};
    private static final byte[] GLOBAL_ENCODED_ASCII_SPECIAL_VALUE = {74, 68, 86, 63};
    private static final byte[] GLOBAL_ENCODED_UTF_8_SPECIAL_VALUE = {74, 68, 86, -62, -87};
    private static final String GLOBAL_DECODED_ASCII_SPECIAL_VALUE = "JDV?";

    @Test
    public void encodeBase64Test() {
        // Act
        String encodedValue = Encoding.encodeBase64(GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCODED_BASE_64_VALUE));
    }

    @Test
    public void decodeBase64Test() {
        // Act
        String decodedValue = Encoding.decodeBase64(GLOBAL_ENCODED_BASE_64_VALUE);

        // Assert
        assertThat(decodedValue, equalTo(GLOBAL_VALUE));
    }

    @Test
    public void encodeASCIITest() {
        // Act
        byte[] encodedBytes = Encoding.encodeASCII(GLOBAL_VALUE);

        // Assert
        assertThat(encodedBytes, equalTo(GLOBAL_ENCODED_ASCII_UTF_8_VALUE));
    }

    @Test
    public void decodeASCIITest() {
        // Act
        String decodedValue = Encoding.decodeASCII(GLOBAL_ENCODED_ASCII_UTF_8_VALUE);

        // Assert
        assertThat(decodedValue, equalTo(GLOBAL_VALUE));
    }

    @Test
    public void encodeASCIISpecialTest() {
        // Act
        byte[] encodedBytes = Encoding.encodeASCII(GLOBAL_SPECIAL_VALUE);

        // Assert
        assertThat(encodedBytes, equalTo(GLOBAL_ENCODED_ASCII_SPECIAL_VALUE));
    }

    @Test
    public void decodeASCIISpecialTest() {
        // Act
        String decodedValue = Encoding.decodeASCII(GLOBAL_ENCODED_ASCII_SPECIAL_VALUE);

        // Assert
        assertThat(decodedValue, equalTo(GLOBAL_DECODED_ASCII_SPECIAL_VALUE));
    }

    @Test
    public void encodeUTF8Test() {
        // Act
        byte[] encodedBytes = Encoding.encodeUTF8(GLOBAL_VALUE);

        // Assert
        assertThat(encodedBytes, equalTo(GLOBAL_ENCODED_ASCII_UTF_8_VALUE));
    }

    @Test
    public void decodeUTF8Test() {
        // Act
        String decodedValue = Encoding.decodeUTF8(GLOBAL_ENCODED_ASCII_UTF_8_VALUE);

        // Assert
        assertThat(decodedValue, equalTo(GLOBAL_VALUE));
    }

    @Test
    public void encodeUTF8SpecialTest() {
        // Act
        byte[] encodedBytes = Encoding.encodeUTF8(GLOBAL_SPECIAL_VALUE);

        // Assert
        assertThat(encodedBytes, equalTo(GLOBAL_ENCODED_UTF_8_SPECIAL_VALUE));
    }

    @Test
    public void decodeUTF8SpecialTest() {
        // Act
        String decodedValue = Encoding.decodeUTF8(GLOBAL_ENCODED_UTF_8_SPECIAL_VALUE);

        // Assert
        assertThat(decodedValue, equalTo(GLOBAL_SPECIAL_VALUE));
    }

    @Test
    public void encodeURLTest() throws UnsupportedEncodingException {
        // Act
        String encodedURL = Encoding.encodeURL(URL_VALUE);

        // Assert
        assertThat(encodedURL, equalTo(ENCODED_URL_VALUE));
    }

    @Test
    public void decodeURLTest() throws UnsupportedEncodingException {
        // Act
        String decodedURL = Encoding.decodeURL(ENCODED_URL_VALUE);

        // Assert
        assertThat(decodedURL, equalTo(URL_VALUE));
    }
}
