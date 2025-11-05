import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class ClassSet {
    private String text;
    private Set<Character> letters;

    public String getText() {
        return text;
    }

    public Set<Character> getLetters() {
        return letters;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ClassSet() {
        this.text = "";
        this.letters = new TreeSet<>();
    }

    public ClassSet(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Файл не может быть null.");
        }
        try {
            if (!file.exists()) {
                throw new IllegalArgumentException("Файл не найден.");
            }
            this.text = Files.readString(file.toPath());
            if (this.text.trim().isEmpty()) {
                throw new IllegalArgumentException("Файл пустой.");
            }
            this.letters = new TreeSet<>();
            Text();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла.") ;
        }
    }

    public String toString() {
        if (letters.isEmpty()) {
            return "Нет согласных, входящих ровно в одно слово";
        }
        return "Согласные входящие ровно в одно слово: " + letters;
    }

    private void Text() {
        Set<Character> symbols = Set.of( 'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м',
                'н', 'п', 'р', 'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ');
        if (text == null || text.trim().isEmpty()) {
            return;
        }
        String lowerText = text.toLowerCase();
        String[] words = lowerText.split(" ");
        Map<Character,Integer> count = new HashMap<>();
        for (String word : words) {
            Set<Character> letersWord = new HashSet<>();
            for (char ch : word.toCharArray()) {
                if (symbols.contains(ch)) {
                    letersWord.add(ch);
                }
            }
            for (char letter : letersWord) {
                if (count.containsKey(letter)) {
                    count.put(letter, count.get(letter) + 1);
                } else {
                    count.put(letter, 1);
                }
            }
        }
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                letters.add(entry.getKey());
            }
        }
    }
}
