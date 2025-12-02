import ru.sivkova.validator.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HumanStream {
    public static void collectionHuman(File file) {
        Validator.valFile(file);
        try {
            Map<Integer, List<String>> result = Files.lines(file.toPath())
                    .filter(str -> str.contains(":"))
                    .map(HumanStream::mapName)
                    .filter(human ->  human != null)
                    .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
            if (result == null || result.isEmpty()) {
                System.out.println("Нет корректных данных для отображения.");
                return;
            }
            String str = "Сгруппированные данные:\n";
            for (Map.Entry<Integer, List<String>> human : result.entrySet()){
                str += human.getKey() + ": " + human.getValue() + ";\n" ;
            }
            System.out.println(str);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private static Map.Entry<Integer, String> mapName(String str) {
        String[] words = str.split(":");
        if (words.length == 2 && !words[0].isEmpty() && !words[1].isEmpty()) {
            String name = words[0].trim();
            String numberStr = words[1].trim();
            name = words[0].substring(0, 1).toUpperCase() + words[0].substring(1).toLowerCase();
            if (!Validator.valIsInt(numberStr)) {
                return null;
            }
            int number = Integer.parseInt(numberStr);
            return Map.entry(number, name);
        }
        return null;
    }
}


