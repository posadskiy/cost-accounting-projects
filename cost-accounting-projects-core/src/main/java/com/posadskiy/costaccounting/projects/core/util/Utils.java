package com.posadskiy.costaccounting.projects.core.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class Utils {
	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant()
			.atZone(ZoneId.systemDefault())
			.toLocalDateTime();
	}

	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant()
			.atZone(ZoneId.systemDefault())
			.toLocalDate();
	}

	public static LocalDateTime convertToLocalDateTimeViaMillisecond(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime())
			.atZone(ZoneId.systemDefault())
			.toLocalDateTime();
	}

	public static LocalDate convertToLocalDateViaMillisecond(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime())
			.atZone(ZoneId.systemDefault())
			.toLocalDate();
	}

	public static int convertDateToDays(Date dateToConvert) {
		return convertToLocalDateTimeViaInstant(dateToConvert).getDayOfMonth();
	}
	
	

	public static String getCurrentMonthAndYear() {
		return getCurrentMonth() + " " + getCurrentYear();
	}
	
	public static String getMonthAndYear(LocalDate date) {
		return getMonth(date) + " " + getYear(date);
	}

	public static String getMonthAndYear(Date date) {
		final LocalDate localDate = convertToLocalDateViaInstant(date);
		return getMonth(localDate) + " " + getYear(localDate);
	}

	public static String getCurrentMonth() {
		return getMonth(LocalDate.now());
	}

	public static String getMonth(LocalDate localDate) {
		return localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US);
	}

	public static Integer getCurrentYear() {
		return getYear(LocalDate.now());
	}

	public static Integer getYear(LocalDate localDate) {
		return localDate.getYear();
	}
}
