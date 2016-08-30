package com.jedivision.test;

import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.jedivision.test.InitializationUtils.ClassUtils.getRandomValueByClass;
import static com.jedivision.test.InitializationUtils.SetterUtils.processSetters;

public final class InitializationUtils {
	private static final Logger log = Logger.getLogger(InitializationUtils.class);

	private static final int FIRST_ELEMENT = 0;

	private InitializationUtils() {}

	public static <T> T entity(Class<? extends T> type) {
		try {
			return createWithDefaultConstructor(type);
		} catch (ReflectiveOperationException e1) {
			log.error("Cannot initialize object default constructor. Class: " + type.getCanonicalName(), e1);
			try {
				return createNoDefaultConstructor(type);
			} catch (ReflectiveOperationException e2) {
				log.error("Cannot initialize object without constructor. Class: " + type.getCanonicalName(), e2);
				return null;
			}
		}
	}

	public static <T> List<T> list(Class<? extends T> type, int size) {
		return IntStream.range(0, size).mapToObj(i -> entity(type)).collect(Collectors.toList());
	}

	public static <T> Set<T> set(Class<? extends T> type, int size) {
		return IntStream.range(0, size).mapToObj(i -> entity(type)).collect(Collectors.toSet());
	}

	// ------------------------------------------------------------------------------------------------------
	// Private Methods
	// ------------------------------------------------------------------------------------------------------
	private static <T> T createWithDefaultConstructor(Class<? extends T> type) throws ReflectiveOperationException {
		T instance = type.newInstance();
		processSetters(instance);
		return instance;
	}

	@SuppressWarnings("unchecked")
	private static <T> T createNoDefaultConstructor(Class<? extends T> type) throws ReflectiveOperationException {
		Constructor<?>[] constructors = type.getConstructors();
		if (constructors.length == 0) {
			throw new ReflectiveOperationException("There is no constructors to initiate instance of type: " + type);
		}
		Constructor<?> constructor = constructors[FIRST_ELEMENT];
		Object[] args = Stream.of(constructor.getParameterTypes())
				.map(ClassUtils::getRandomValueByClass)
				.toArray();
		T instance = (T) constructor.newInstance(args);
		processSetters(instance);
		return instance;
	}

	// ------------------------------------------------------------------------------------------------------
	// Protected Classes
	// ------------------------------------------------------------------------------------------------------
	static final class SetterUtils {

		private SetterUtils() {}

		static <T> void processSetters(T instance) throws ReflectiveOperationException {
			if (instance == null) {
				throw new ReflectiveOperationException("Setters cannot be processes cause instance is null.");
			}
			Stream.of(instance.getClass().getMethods())
					.filter(method -> {
						Class<?>[] params = method.getParameterTypes();
						return method.getName().startsWith("set")
								&& params != null
								&& params.length > 0;
					})
					.forEach(setter -> {
						try {
							setter.invoke(instance, getRandomValueByClass(setter.getParameterTypes()[0]));
						} catch (IllegalAccessException | InvocationTargetException e) {
							log.error("Cannot invoke setter", e);
						}
					});
		}
	}
	
	static final class ClassUtils {

		private ClassUtils() {}
		
		static Object getRandomValueByClass(Class<?> clazz) {
			Map<Predicate<Class>, Supplier> map = new HashMap<>();
			map.put(Class::isEnum, () -> RandomUtils.randomEnum(clazz));
			map.put(Boolean.class::equals, RandomUtils::randomBoolean);
			map.put(String.class::equals, RandomUtils::randomString);
			map.put(Integer.class::equals, RandomUtils::randomInteger);
			map.put(Long.class::equals, RandomUtils::randomLong);
			map.put(List.class::equals, Collections::emptyList);
			Optional<Map.Entry<Predicate<Class>, Supplier>> matches = map.entrySet().stream()
					.filter(item -> item.getKey().test(clazz))
					.findFirst();
			if (matches.isPresent()) {
				return matches.get().getValue().get();
			}
			log.warn("Please add random value for type: " + clazz.getCanonicalName());
			return null;
		}
	}
}
