package com.jedivision;

import com.lambdaworks.crypto.SCryptUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hash.class);
    private static final String MD5 = "MD5";

    public static String md5ViaPureJava(String value) throws NoSuchAlgorithmException {
        LOGGER.debug("Hashing {} with md5 hashing algorithm via pure Java", value);
        MessageDigest md = MessageDigest.getInstance(MD5);
        md.update(value.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    public static String md5ViaCommonsCodec(String value) {
        LOGGER.debug("Hashing {} with md5 hashing algorithm via commons codec", value);
        return DigestUtils.md5Hex(value);
    }

    public static String sha1ViaCommonsCodec(String value) {
        LOGGER.debug("Hashing {} with sha-1 hashing algorithm via commons codec", value);
        return DigestUtils.sha1Hex(value);
    }

    public static String sha256ViaCommonsCodec(String value) {
        LOGGER.debug("Hashing {} with sha-256 hashing algorithm via commons codec", value);
        return DigestUtils.sha256Hex(value);
    }

    public static String sha384ViaCommonsCodec(String value) {
        LOGGER.debug("Hashing {} with sha-384 hashing algorithm via commons codec", value);
        return DigestUtils.sha384Hex(value);
    }

    public static String sha512ViaCommonsCodec(String value) {
        LOGGER.debug("Hashing {} with sha-512 hashing algorithm via commons codec", value);
        return DigestUtils.sha512Hex(value);
    }

    public static String bcryptViaBCrypt(String value) {
        LOGGER.debug("Hashing {} with bcrypt hashing algorithm", value);
        return BCrypt.hashpw(value, BCrypt.gensalt(12));
    }

    public static String scryptViaLambdaWorks(String value) {
        LOGGER.debug("Hashing {} with scrypt hashing algorithm", value);
        return SCryptUtil.scrypt(value, 16, 16, 16);
    }
}
