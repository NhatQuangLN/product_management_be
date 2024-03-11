package com.beinternational.product_management_be.common;

import java.util.Objects;

import com.beinternational.product_management_be.utils.StringUtils;

public class MessageCommon {

	private MessageCommon() {
		throw new IllegalStateException("Common class should not be instantiated.");
	}

	public static final int NO_SUCH_REPLACE_PARAM = -1;

	public static final String NOT_FOUND = "{0} Not Found! Please try again.";

	public static final String INVALID = "Invalid {0}! Please try again.";

	public static final String EMPTY = "Empty {0}! Please try again.";

	public static final String EXISTED = "{0} Existed! Please try again.";

	public static final String INVALID_LENGTH = "Kindly include a minimum of {0} characters to describe {1}.";

	public static final String INTERNAL_ERROR = "Internal Error. Please try again";

	public static String setMessage(String message, String... params) {

		if (Objects.isNull(params)) {
			return message;
		}

		StringBuilder messageReturn = new StringBuilder(message);
		for (int i = 0; i < params.length; i++) {
			String replaceParam = "{" + i + "}";
			int startIndex = messageReturn.indexOf(replaceParam);
			if (Objects.isNull(params[i])) {
				params[i] = StringUtils.EMPTY;
			}
			if (startIndex == NO_SUCH_REPLACE_PARAM) {
				return messageReturn.toString();
			}
			messageReturn.replace(startIndex, startIndex + replaceParam.length(), params[i]);
		}
		return messageReturn.toString();
	}
}
