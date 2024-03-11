package com.beinternational.product_management_be.utils;

import java.util.UUID;

public class StringUtils {

	private StringUtils() {
		throw new IllegalStateException("Utility class should not be instantiated.");
	}

	public static final UUID INVALID_ID = null;

	public static final String EMPTY = "";

	public static UUID convertToUUID(String value) {
		try {
			return UUID.fromString(value);
		} catch (Exception e) {
			return INVALID_ID;
		}
	}

	public static boolean isEmpty(String string) {

		return string == null || string.isEmpty();
	}
}
