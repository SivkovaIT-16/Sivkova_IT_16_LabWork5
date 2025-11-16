import ru.sivkova.validator.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HumanStream {
    private File file;
    private Map<Integer, List<String>> humansStream;

    public File getFile() {
        return file;
    }

    public Map<Integer, List<String>> getHumansStream() {
        return humansStream;
    }

    public void setFile(File file) {
        Validator.valFile(file);
        this.file = file;
        this.humansStream = collectionHuman(file);
    }

    public HumanStream(File file) {
        Validator.valFile(file);
        this.file = file;
        this.humansStream = collectionHuman(file);
    }

    @Override
    public String toString() {
        if (humansStream == null || humansStream.isEmpty()) {
            return "Нет корректных данных для отображения.";
        }
        String str = "Сгруппированные данные:\n";
        for (Map.Entry<Integer, List<String>> human : humansStream.entrySet()){
            str += human.getKey() + ": " + human.getValue() + ";\n" ;
        }
        return str;
    }

    private Map<Integer, List<String>> collectionHuman(File file) {
        try {
            Map<Integer, List<String>> result = Files.lines(file.toPath())
                    .filter(str -> str.contains(":"))
                    .map(HumanStream::mapName)
                    .filter(human ->  human != null)
                    .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
            return result;
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


