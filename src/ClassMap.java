import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ClassMap {
    private Map<String, String> input;
    private Map<String, String> result;

    public Map<String, String> getInput() {
        return input;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setInput(Map<String, String> input) {
        this.input = input;
        this.result = selection(input);
    }

    public ClassMap() {
        this.input = new HashMap<>();
        this.result = new HashMap<>();
    }

    public ClassMap(File file) {
        Validator.valFile(file);
        this.input = readFile(file);
        this.result = selection(input);

    }

    @Override
    public String toString() {
        String str = "";
        if (input.isEmpty()) {
            return "В файле не найдено строк с корректными данными абитуриента.";
        }
        if (result.isEmpty()) {
            return "Нет абитуриентов, не прошедших тестирование.";
        }
        str = "Список всех абитуриентов:\n";
        for (Map.Entry<String, String> applicant : input.entrySet()){
            str += applicant.getKey() + ": " + applicant.getValue() + ";\n" ;
        }
        str +=  "Список не допущенных до экзамена абитуриентов:\n";
        for (Map.Entry<String, String> applicant : result.entrySet()){
            str += applicant.getKey() + ": " + applicant.getValue() + ";\n" ;
        }
        return str;
    }

    private Map<String, String> readFile(File file) {
        List<String> lines;
        Map<String, String> dataFile = new HashMap<>();
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла " + file + ".");
        }
        for (String str : lines) {
            String[] parts = str.split(" ");
            if (parts.length != 4) {
                System.out.println("Cтрока \"" + str + "\" пропущена, т.к. содержит некорректные данные.");
                continue;
            }
            if (!Validator.valIsNumber(parts[2]) || !Validator.valIsNumber(parts[3])) {
                System.out.println("Cтрока \"" + str + "\" пропущена, т.к. содержит некорректные значения баллов.");
                continue;
            } else  if (Integer.parseInt(parts[2]) < 0 || Integer.parseInt(parts[2]) > 100 ||
                    Integer.parseInt(parts[3]) < 0 || Integer.parseInt(parts[3]) > 100) {
                System.out.println("Cтрока \"" + str + "\" пропущена, т.к. содержит некорректные значения баллов.");
                continue;
            }
            dataFile.put(parts[0] + " " + parts[1], parts[2] + " " + parts[3]);
        }
        return dataFile;
    }

    private Map<String, String> selection(Map<String, String> input) {
        Map<String, String> result = new HashMap<>();
        for (String name : input.keySet()) {
            String scores = input.get(name);
            String[] scoreParts = scores.split(" ");
            int score1 = Integer.parseInt(scoreParts[0]);
            int score2 = Integer.parseInt(scoreParts[1]);
            if (score1 < 30 || score2 < 30) {
                result.put(name, scores);
            }
        }
        return result;
    }
}
