import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        Validator.validateNullMap(input);
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
            return "В файле не найдено строк с корректными данными участников.";
        }
        str = "\nСписок всех участников:\n";
        for (Map.Entry<String, String> applicant : input.entrySet()){
            str += applicant.getKey() + ": " + applicant.getValue() + ";\n" ;
        }
        str +=  "\nСписок участников, набравших максимальное количество баллов:\n";
        for (Map.Entry<String, String> applicant : result.entrySet()){
            str += applicant.getKey() + ": " + applicant.getValue() + ";\n" ;
        }
        return str;
    }

    private Map<String, String> readFile(File file) {
        Map<String, String> dataFile = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String firstLine = reader.readLine();
            int n;
            try {
                n = Integer.parseInt(firstLine.trim());
            } catch (NumberFormatException e) {
                System.out.println("Первая строка должна содержать количество участников.");
                return dataFile;
            }
        if (n > 250) {
            System.out.println("Количество участников превышает 250.");
            return dataFile;
        }
            for (int i = 0; i < n; i++) {
                String str = reader.readLine();
                if (str == null) {
                    System.out.println("Файл содержит меньше строк, чем указано.");
                    break;
                }
                String[] parts = str.split(" ");
                if (parts.length != 5) {
                    System.out.println("Cтрока \"" + str + "\" пропущена, т.к. содержит некорректные данные.");
                    continue;
                }
                if (!Validator.valIsNumber(parts[2]) || !Validator.valIsNumber(parts[3]) || !Validator.valIsNumber(parts[4])) {
                    System.out.println("Cтрока \"" + str + "\" пропущена, т.к. содержит некорректные значения баллов.");
                    continue;
                } else if (Integer.parseInt(parts[2]) < 0 || Integer.parseInt(parts[2]) > 25 ||
                        Integer.parseInt(parts[3]) < 0 || Integer.parseInt(parts[3]) > 25 ||
                        Integer.parseInt(parts[4]) < 0 || Integer.parseInt(parts[4]) > 25) {
                    System.out.println("Cтрока \"" + str + "\" пропущена, т.к. содержит некорректные значения баллов.");
                    continue;
                }
                dataFile.put(parts[0] + " " + parts[1], parts[2] + " " + parts[3] + " " + parts[4]);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла " + file + ".");
        }
        return dataFile;
    }

    private Map<String, String> selection(Map<String, String> input) {
        Map<String, String> result = new HashMap<>();
        int maxScore = 0;
        for (String name : input.keySet()) {
            String scores = input.get(name);
            String[] scoreParts = scores.split(" ");
            int score = Integer.parseInt(scoreParts[0]) +
                    Integer.parseInt(scoreParts[1]) +
                    Integer.parseInt(scoreParts[2]);
            if (score > maxScore) {
                maxScore = score;
            }
        }
        for (String name : input.keySet()) {
            String scores = input.get(name);
            String[] scoreParts = scores.split(" ");
            int score = Integer.parseInt(scoreParts[0]) +
                    Integer.parseInt(scoreParts[1]) +
                    Integer.parseInt(scoreParts[2]);
            if (score == maxScore) {
                result.put(name, scores);
            }
        }
        return result;
    }
}
