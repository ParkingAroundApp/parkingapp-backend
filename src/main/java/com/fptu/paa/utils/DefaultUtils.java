package com.fptu.paa.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DefaultUtils {
	public static String parseTimestampToFormattedString(Timestamp timestamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss:SSS");
		return formatter.format(timestamp.toLocalDateTime()).toString();
	}

	public static boolean isBetween(LocalDateTime time, LocalDateTime start, LocalDateTime end, String operator) {
		boolean result = false;
		switch (operator) {
		case "!=":
			if (time.isAfter(start) && time.isBefore(end)) {
				result = true;
			}
			break;
		case "==":
			if (time.compareTo(start) >= 0 && time.compareTo(end) <= 0) {
				result = true;
			}
			break;
		default:
			throw new IllegalArgumentException("Operator " + operator + " invalid only (!= and ==)");
		}
		return result;
	}
}
