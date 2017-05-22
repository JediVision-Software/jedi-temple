package com.jedivision;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class EncodingTest {

    private static final String globalValue = "JDV";
    private static final String globalEncodedBase64Value = "SkRW";

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

}
