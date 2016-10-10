package com.jedivision.test;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({ "rawtypes", "unchecked" })
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtils {
    private static Random rnd = ThreadLocalRandom.current();

    private static final int UNIQUE_VALUE = 1;

    public static <T extends Enum> T randomEnum(Class<?> enumClazz) {
        Object[] values = enumClazz.getEnumConstants();
        return (T) values[rnd.nextInt(values.length)];
    }

    public static boolean randomBoolean() {
        return rnd.nextBoolean();
    }

    public static String randomString() {
        return UUID.randomUUID().toString();
    }

    public static int randomUnique() {
        return UNIQUE_VALUE;
    }

    public static Integer randomSmallInteger() {
        return rnd.nextInt(4) + 1;
    }

    public static Integer randomIntegerByBound(int bound) { return rnd.nextInt(bound); }

    public static Integer randomInteger() {
        return rnd.nextInt();
    }

    public static Long randomLong() {
        return rnd.nextLong();
    }

    public static Double randomDouble() {
        return rnd.nextDouble();
    }

    public static Short randomShort() {
        return (short) rnd.nextInt(Short.MAX_VALUE + 1);
    }
}
