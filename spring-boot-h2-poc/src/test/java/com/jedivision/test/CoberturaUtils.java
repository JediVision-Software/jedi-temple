package com.jedivision.test;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CoberturaUtils {
	private static final Logger log = Logger.getLogger(CoberturaUtils.class);

	public static void classCoverageHook(Class<?> clazz) {
		try {
			defaultConstructorHook(clazz);
		} catch (ReflectiveOperationException e1) {
			log.error("Cannot initialize clazz by default constructor: " + clazz.getCanonicalName(), e1);
			try {
				privateConstructorHook(clazz);
			} catch (ReflectiveOperationException e2) {
				log.error("Cannot initialize clazz by private constructor: " + clazz.getCanonicalName(), e2);
			}
		}
	}

	private static void defaultConstructorHook(Class<?> clazz) throws ReflectiveOperationException {
		clazz.newInstance();
	}

	private static void privateConstructorHook(Class<?> clazz) throws ReflectiveOperationException {
		Constructor<?> constructor = clazz.getDeclaredConstructor();
		constructor.setAccessible(true);
		constructor.newInstance();
	}
}
