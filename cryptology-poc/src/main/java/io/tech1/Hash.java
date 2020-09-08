package io.tech1;

import com.lambdaworks.crypto.SCryptUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Hash {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hash.class);
    // Algorithms
    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA-1";
    private static final String SHA256 = "SHA-256";
    private static final String SHA384 = "SHA-384";
    private static final String SHA512 = "SHA-512";

    public static String md5ViaPureJava(String value) throws NoSuchAlgorithmException {
        LOGGER.debug("Hashing {} with md5 hashing algorithm via pure Java", value);
        return hashViaPureJava(MD5, value);
    }

    public static String md5ViaCommonsCodec(String value) {
        LOGGER.debug("Hashing {} with md5 hashing algorithm via commons codec", value);
        return DigestUtils.md5Hex(value);
    }

    public static String sha1ViaPureJava(String value) throws NoSuchAlgorithmException {
        LOGGER.debug("Hashing {} with sha-1 hashing algorithm via pure Java", value);
        return hashViaPureJava(SHA1, value);
    }

    public static String sha1ViaCommonsCodec(String value) {
        LOGGER.debug("Hashing {} with sha-1 hashing algorithm via commons codec", value);
        return DigestUtils.sha1Hex(value);
    }

    public static String sha256ViaPureJava(String value) throws NoSuchAlgorithmException {
        LOGGER.debug("Hashing {} with sha-256 hashing algorithm via pure Java", value);
        return hashViaPureJava(SHA256, value);
    }

    public static String sha256ViaCommonsCodec(String value) {
        LOGGER.debug("Hashing {} with sha-256 hashing algorithm via commons codec", value);
        return DigestUtils.sha256Hex(value);
    }

    public static String sha384ViaPureJava(String value) throws NoSuchAlgorithmException {
        LOGGER.debug("Hashing {} with sha-384 hashing algorithm via pure Java", value);
        return hashViaPureJava(SHA384, value);
    }

    public static String sha384ViaCommonsCodec(String value) {
        LOGGER.debug("Hashing {} with sha-384 hashing algorithm via commons codec", value);
        return DigestUtils.sha384Hex(value);
    }

    public static String sha512ViaPureJava(String value) throws NoSuchAlgorithmException {
        LOGGER.debug("Hashing {} with sha-512 hashing algorithm via pure Java", value);
        return hashViaPureJava(SHA512, value);
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

    // ------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // ------------------------------------------------------------------------------------

    private static String hashViaPureJava(String algorithm, String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(value.getBytes(UTF_8));
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
