package ru.sivkova.validator;

import ru.sivkova.fraction.*;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.Queue;

public class Validator {
    public static boolean valIsInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(value.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean valIsDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(value.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean valIsNumber(String value) {
        return valIsInt(value) || valIsDouble(value);
    }

    public static <T> void validateNullList(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть null.");
        }
    }

    public static <T> void validateNullMap(Map<T, T> map) {
        if (map == null) {
            throw new IllegalArgumentException("Контейнер не может быть null.");
        }
    }

    public static <T> void validateNullQueue(Queue<T> queue) {
        if (queue == null) {
            throw new IllegalArgumentException("Очередь не может быть null.");
        }
    }

    public static void valFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Файл не может быть null.");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл " + file + " не найден.");
        }
        if (file.length() == 0) {
            throw new IllegalArgumentException("Файл " + file + " пустой.");
        }
    }

    public static void validateDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю.");
        }
    }

    public static void validateFraction(Fraction fraction) {
        if (fraction == null) {
            throw new IllegalArgumentException("Дробь не может быть null");
        }
        if (fraction.getNumerator() == 0) {
            throw new ArithmeticException("Деление на нулевую дробь.");
        }
    }

    public static void validateNumber(int number) {
        if (number == 0) {
            throw new ArithmeticException("Деление на ноль.");
        }

    }

}
