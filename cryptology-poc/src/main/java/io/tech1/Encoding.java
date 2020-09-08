package io.tech1;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Encoding {

    public static byte[] encodeASCII(String value) {
        return value.getBytes(US_ASCII);
    }

    public static String decodeASCII(byte[] bytes) {
        return new String(bytes, US_ASCII);
    }

    public static byte[] encodeUTF8(String value) {
        return value.getBytes(UTF_8);
    }

    public static String decodeUTF8(byte[] bytes) {
        return new String(bytes, UTF_8);
    }

    public static String encodeURL(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, UTF_8.toString());
    }

    public static String decodeURL(String value) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, UTF_8.toString());
    }

    public static String encodeBase64(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    public static String decodeBase64(String value) {
        return new String(Base64.getDecoder().decode(value.getBytes()));
    }
}
