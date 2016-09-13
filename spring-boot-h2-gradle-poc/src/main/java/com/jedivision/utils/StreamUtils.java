package com.jedivision.utils;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class StreamUtils {

	public static <T> Stream<T> stream(Iterator<T> iterator) {
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(
						iterator,
						Spliterator.ORDERED
				),
				false
		);
	}

	public static <T> Stream<T> stream(Iterable<T> iterable) {
		return stream(iterable.iterator());
	}
}
