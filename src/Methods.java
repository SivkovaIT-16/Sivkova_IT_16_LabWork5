import ru.sivkova.validator.Validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Methods {
    //Работа со списками
    public static <T> List<T> removeAll(List<T> list, T value) {
        Validator.validateNullList(list);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (element instanceof Number && value instanceof Number) {
                if (((Number) element).doubleValue() != ((Number) value).doubleValue()) {
                    result.add(element);
                }
            } else {
                if (!Objects.equals(element, value)) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    //Работа с коллекцией
    public static void findParticipants(File file) {
        Validator.valFile(file);
        TreeMap<Integer, List<String>> participants = new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String firstLine = reader.readLine();
            int n;
            try {
                n = Integer.parseInt(firstLine.trim());
            } catch (NumberFormatException e) {
                System.out.println("Первая строка должна содержать количество участников.");
                return;
            }
            if (n > 250) {
                System.out.println("Количество участников превышает 250.");
                return;
            }
            for (int i = 0; i < n; i++) {
                String str = reader.readLine();
                if (str == null) {
                    System.out.println("Файл содержит меньше строк, чем указано.");
                    return;
                }
                String[] parts = str.split(" ");
                if (parts.length != 5) {
                    System.out.println("Cтрока \"" + str + "\" пропущена, т.к. содержит некорректные данные.");
                    continue;
                }
                if (!Validator.valIsInt(parts[2]) || !Validator.valIsInt(parts[3]) || !Validator.valIsInt(parts[4])) {
                    System.out.println("Cтрока \"" + str + "\" пропущена, т.к. содержит некорректные значения баллов.");
                    continue;
                }
                int score1 = Integer.parseInt(parts[2]);
                int score2 = Integer.parseInt(parts[3]);
                int score3 = Integer.parseInt(parts[4]);
                if (score1 < 0 || score1 > 25 || score2 < 0 || score2 > 25 || score3 < 0 || score3 > 25) {
                    System.out.println("Cтрока \"" + str + "\" пропущена, т.к. содержит некорректные значения баллов.");
                    continue;
                }
                int totalScore = score1 + score2 + score3;
                String name = parts[0] + " " + parts[1];
                if (!participants.containsKey(totalScore)) {
                    participants.put(totalScore, new ArrayList<>());
                }
                participants.get(totalScore).add(name);
            }
            reader.close();
            if (participants.isEmpty()) {
                System.out.println("Нет участников с корректными данными.");
            } else {
                Map.Entry<Integer, List<String>> lastEntry = participants.lastEntry();
                System.out.println("Участники с максимальным баллом (" + lastEntry.getKey() + "):");
                for (String participant : lastEntry.getValue()) {
                    System.out.println(participant);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла " + file + ".");
        }
    }

    //Работа с контейнером Set
    public static void findUniqueLetters(File file) {
        Validator.valFile(file);
        String text = "";
        try {
            text = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла " + file + ".");
        }
        Set<Character> result = new TreeSet<>();
        Set<Character> symbols = Set.of('б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м',
                'н', 'п', 'р', 'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ');
        String lowerText = text.toLowerCase();
        String[] words = lowerText.split(" ");
        Set<Character> lettersNotUnique = new HashSet<>();
        for (String word : words) {
            Set<Character> lettersWord = new HashSet<>();
            for (char ch : word.toCharArray()) {
                if (symbols.contains(ch)) {
                    lettersWord.add(ch);
                }
            }
            for (char letter : lettersWord) {
                if (lettersNotUnique.contains(letter)) {
                    continue;
                } else if (result.contains(letter)) {
                    result.remove(letter);
                    lettersNotUnique.add(letter);
                } else {
                    result.add(letter);
                }
            }
        }
        if (result.isEmpty()) {
            System.out.println("Нет согласных, входящих ровно в одно слово.");
            return;
        }
        System.out.println("Согласные входящие ровно в одно слово: " + result);
    }

    public static void toStringSet(Set<Character> letters) {
        if (letters.isEmpty()) {
            System.out.println("Нет согласных, входящих ровно в одно слово");
        }
        System.out.println("Согласные входящие ровно в одно слово: " + letters);
    }

    //Работа с очередью
    public static <T> void equality(Queue<T> queue, int i, int j) {
        Validator.validateNullQueue(queue);
        if (i < 0 || j < 0 || i >= j) {
            throw new IllegalArgumentException("Некорректные индексы: i должно быть меньше j и оба > 0");
        }
        if (j >= queue.size()) {
            throw new IllegalArgumentException("Индекс j превышает размер очереди");
        }
        Iterator<T> iterator = queue.iterator();
        for (int k = 0; k < i; k++) {
            iterator.next();
        }
        T first = iterator.next();
        boolean result = true;
        for (int k = i + 1; k <= j; k++) {
            T current = iterator.next();
            if (!first.equals(current)) {
                result = false;
                break;
            }
        }
        System.out.println("Очередь: " + queue + "\n" +
                "Результат проверки: " + result);
    }
}




