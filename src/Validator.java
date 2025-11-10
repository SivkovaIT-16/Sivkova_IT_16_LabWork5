import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

    public static <T> void validateNull(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть null.");
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

}
