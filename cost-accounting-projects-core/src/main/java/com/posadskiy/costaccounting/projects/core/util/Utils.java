package com.posadskiy.costaccounting.projects.core.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }

    public static String getMonthAndYear(Date date) {
        final LocalDate localDate = convertToLocalDateViaInstant(date);
        return getMonth(localDate) + " " + getYear(localDate);
    }

    public static String getMonth(LocalDate localDate) {
        return localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US);
    }

    public static Integer getYear(LocalDate localDate) {
        return localDate.getYear();
    }
}
