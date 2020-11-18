package com.fptu.paa.utils;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class DefaultUtils {
	public static String parseTimestampToFormattedString(Timestamp timestamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss:SSS");
		return formatter.format(timestamp.toLocalDateTime()).toString();
	}
}
