package io.tech1;

import com.lambdaworks.crypto.SCryptUtil;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.security.NoSuchAlgorithmException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HashTest {

    private static final String GLOBAL_VALUE = "JDV";
    private static final String GLOBAL_ENCODED_MD5_VALUE = "d7df1f0388aa696ce6ca0750afdebda5";
    private static final String GLOBAL_ENCODED_SHA_1_VALUE = "a624672ff7e7160788261f0cec3136a789ac758a";
    private static final String GLOBAL_ENCODED_SHA_256_VALUE = "d5a5d16c2b9261544191204c5e3344071b0cbc064cbd04a8b9238b946bd7a5ad";
    private static final String GLOBAL_ENCODED_SHA_384_VALUE = "1ae05faecad3bf1261fb0896b7ac3ef7460c776102ecb03432f83b680570f21ac5f4836e8a234d489f456af7ef0b468d";
    private static final String GLOBAL_ENCODED_SHA_512_VALUE = "5c840b09e9f7d93991bc59c7a6aad6457f294cfb0edc2522e5117393c51fdf8dc6793d77dda284ef000d3c8de2561b31199c64c3231c7e4a7bb0c63f795422fd";

    @Test
    public void md5ViaPureJavaTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.md5ViaPureJava(GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCODED_MD5_VALUE));
    }

    @Test
    public void md5ViaCommonsCodecTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.md5ViaCommonsCodec(GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCODED_MD5_VALUE));
    }

    @Test
    public void sha1ViaPureJavaTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.sha1ViaPureJava(GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCODED_SHA_1_VALUE));
    }

    @Test
    public void sha1ViaCommonsCodecTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.sha1ViaCommonsCodec(GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCODED_SHA_1_VALUE));
    }

    @Test
    public void sha256ViaPureJavaTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.sha256ViaPureJava(GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCODED_SHA_256_VALUE));
    }

    @Test
    public void sha256ViaCommonsCodecTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.sha256ViaCommonsCodec(GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCODED_SHA_256_VALUE));
    }

    @Test
    public void sha384ViaPureJavaTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.sha384ViaPureJava(GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCODED_SHA_384_VALUE));
    }

    @Test
    public void sha384ViaCommonsCodecTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.sha384ViaCommonsCodec(GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCODED_SHA_384_VALUE));
    }

    @Test
    public void sha512ViaPureJavaTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.sha512ViaPureJava(GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCODED_SHA_512_VALUE));
    }

    @Test
    public void sha512ViaCommonsCodecTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.sha512ViaCommonsCodec(GLOBAL_VALUE);

        // Assert
        assertThat(encodedValue, equalTo(GLOBAL_ENCODED_SHA_512_VALUE));
    }

    @Test
    public void bcryptViaBCryptTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.bcryptViaBCrypt(GLOBAL_VALUE);
        boolean matched = BCrypt.checkpw(GLOBAL_VALUE, encodedValue);

        // Assert
        assertTrue(matched);
    }

    @Test
    public void scryptViaLambdaWorksTest() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.scryptViaLambdaWorks(GLOBAL_VALUE);
        boolean matched = SCryptUtil.check(GLOBAL_VALUE, encodedValue);

        // Assert
        assertTrue(matched);
    }
}
