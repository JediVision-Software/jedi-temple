package com.jedivision.test;

import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;

public final class CoberturaUtils {
    private static final Logger log = Logger.getLogger(CoberturaUtils.class);

    private CoberturaUtils() {
    }

    public static void classCoverageHook(Class<?> clazz) {
        try {
            defaultConstructorHook(clazz);
        } catch (ReflectiveOperationException var4) {
            log.error("Cannot initialize clazz by default constructor: " + clazz.getCanonicalName(), var4);

            try {
                privateConstructorHook(clazz);
            } catch (ReflectiveOperationException var3) {
                log.error("Cannot initialize clazz by private constructor: " + clazz.getCanonicalName(), var3);
            }
        }

    }

    private static void defaultConstructorHook(Class<?> clazz) throws ReflectiveOperationException {
        clazz.newInstance();
    }

    private static void privateConstructorHook(Class<?> clazz) throws ReflectiveOperationException {
        Constructor constructor = clazz.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        constructor.newInstance(new Object[0]);
    }
}
