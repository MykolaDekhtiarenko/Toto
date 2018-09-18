package com.epam.training.toto.utilities;

import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GeneralUtils {

    private GeneralUtils() {
    }

    public static Integer getValueOfNumberColumn(String value) {
        String clearedValue = value.replaceAll("\\D", "");
        return Strings.isNotBlank(clearedValue) ? Integer.valueOf(clearedValue) : null;
    }

    public static Integer getValueOfCurrencyColumn(String value) {
        String clearedValue = value.replaceAll("\\D", "");
        return Strings.isNotBlank(clearedValue) ? Integer.valueOf(clearedValue) * 100 : null;
    }

    public static LocalDate getValueOfDateColumn(String value, String pattern) {
        return Strings.isNotBlank(value) ? LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern)) : null;
    }

    public static String getValueOfOutcomeColumn(String value) {
        return value.replaceAll("[^12X]", "");
    }

}
