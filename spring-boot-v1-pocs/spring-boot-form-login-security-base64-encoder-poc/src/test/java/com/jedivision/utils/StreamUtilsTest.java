package com.jedivision.utils;

import com.jedivision.test.CoberturaUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.jedivision.test.RandomUtils.randomString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StreamUtilsTest {

    @BeforeClass
    public static void beforeClass() {
        CoberturaUtils.classCoverageHook(StreamUtils.class);
    }

    @Test
    public void streamIterator() {
        // Arrange
        List<String> iterable = new ArrayList<>();
        String first = randomString();
        iterable.add(first);

        // Act
        Stream<String> stream = StreamUtils.stream(iterable);

        // Assert
        assertThat(stream.iterator().hasNext(), equalTo(true));
    }

    @Test
    public void streamIterable() {
        // Arrange
        List<String> iterable = new ArrayList<>();
        String first = randomString();
        String second = randomString();
        iterable.add(first);
        iterable.add(second);

        // Act
        Stream<String> stream = StreamUtils.stream(iterable);

        // Assert
        assertThat(stream.count(), equalTo(2L));
    }
}
