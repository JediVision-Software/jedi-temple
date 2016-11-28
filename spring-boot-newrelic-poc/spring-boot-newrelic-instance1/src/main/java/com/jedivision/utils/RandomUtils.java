package com.jedivision.utils;

import com.jedivision.entity.Sex;
import com.jedivision.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Character.toUpperCase;

@SuppressWarnings({ "rawtypes", "unchecked" })
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtils {
    private static Random random = ThreadLocalRandom.current();

    public static User randomUser() {
        return User.builder()
                .firstName(randomName())
                .lastName(randomName())
                .sex(randomEnum(Sex.class))
                .age(randomAge())
                .build();
    }

    private static String randomName() {
        String word = UUID.randomUUID().toString().replace("-", "");
        return toUpperCase(word.charAt(0)) + word.substring(1, 4 + random.nextInt(4));
    }

    private static <T extends Enum> T randomEnum(Class<?> enumClazz) {
        Object[] values = enumClazz.getEnumConstants();
        return (T) values[random.nextInt(values.length)];
    }

    private static int randomAge() {
        return 10 + random.nextInt(40);
    }
}
