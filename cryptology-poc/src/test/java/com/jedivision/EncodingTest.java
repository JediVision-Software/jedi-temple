package com.jedivision;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class EncodingTest {

    private static final String globalValue = "JDV";
    private static final String globalSpecialValue = "JDV©";
    private static final String urlValue = "http://www.jedivision-software.com";
    private static final String encodedUrlValue = "http%3A%2F%2Fwww.jedivision-software.com";
    private static final String globalEncodedBase64Value = "SkRW";
    private static final byte[] globalEncodedASCII_UTF8Value = {74, 68, 86};
    private static final byte[] globalEncodedASCIISpecialValue = {74, 68, 86, 63};
    private static final byte[] globalEncodedUTF8SpecialValue = {74, 68, 86, -62, -87};
    private static final String globalDecodedASCIISpecialValue = "JDV?";

    @Test
    public void encodeBase64Test() {
        // Act
        String encodedValue = Encoding.encodeBase64(globalValue);

        // Assert
        assertThat(encodedValue, equalTo(globalEncodedBase64Value));
    }

    @Test
    public void decodeBase64Test() {
        // Act
        String decodedValue = Encoding.decodeBase64(globalEncodedBase64Value);

        // Assert
        assertThat(decodedValue, equalTo(globalValue));
    }

    @Test
    public void encodeASCIITest() {
        // Act
        byte[] encodedBytes = Encoding.encodeASCII(globalValue);

        // Assert
        assertThat(encodedBytes, equalTo(globalEncodedASCII_UTF8Value));
    }

    @Test
    public void decodeASCIITest() {
        // Act
        String decodedValue = Encoding.decodeASCII(globalEncodedASCII_UTF8Value);

        // Assert
        assertThat(decodedValue, equalTo(globalValue));
    }

    @Test
    public void encodeASCIISpecialTest() {
        // Act
        byte[] encodedBytes = Encoding.encodeASCII(globalSpecialValue);

        // Assert
        assertThat(encodedBytes, equalTo(globalEncodedASCIISpecialValue));
    }

    @Test
    public void decodeASCIISpecialTest() {
        // Act
        String decodedValue = Encoding.decodeASCII(globalEncodedASCIISpecialValue);

        // Assert
        assertThat(decodedValue, equalTo(globalDecodedASCIISpecialValue));
    }

    @Test
    public void encodeUTF8Test() {
        // Act
        byte[] encodedBytes = Encoding.encodeUTF8(globalValue);

        // Assert
        assertThat(encodedBytes, equalTo(globalEncodedASCII_UTF8Value));
    }

    @Test
    public void decodeUTF8Test() {
        // Act
        String decodedValue = Encoding.decodeUTF8(globalEncodedASCII_UTF8Value);

        // Assert
        assertThat(decodedValue, equalTo(globalValue));
    }

    @Test
    public void encodeUTF8SpecialTest() {
        // Act
        byte[] encodedBytes = Encoding.encodeUTF8(globalSpecialValue);

        // Assert
        assertThat(encodedBytes, equalTo(globalEncodedUTF8SpecialValue));
    }

    @Test
    public void decodeUTF8SpecialTest() {
        // Act
        String decodedValue = Encoding.decodeUTF8(globalEncodedUTF8SpecialValue);

        // Assert
        assertThat(decodedValue, equalTo(globalSpecialValue));
    }

    @Test
    public void encodeURLTest() throws UnsupportedEncodingException {
        // Act
        String encodedURL = Encoding.encodeURL(urlValue);

        // Assert
        assertThat(encodedURL, equalTo(encodedUrlValue));
    }

    @Test
    public void decodeURLTest() throws UnsupportedEncodingException {
        // Act
        String decodedURL = Encoding.decodeURL(encodedUrlValue);

        // Assert
        assertThat(decodedURL, equalTo(urlValue));
    }
}
