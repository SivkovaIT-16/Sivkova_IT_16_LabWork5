import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ClassQueue {
    private Queue<Number> input;
    private  boolean result;

    public Queue<Number> getInput() {
        return input;
    }

    public boolean getResult() {
        return result;
    }

    public void setInput(Queue<Number> input) {
        Validator.validateNullQueue(input);
        this.input = input;
    }

    public ClassQueue(){
        this.input = new LinkedList<>();
    }

    public ClassQueue(Queue<Number> input) {
        Validator.validateNullQueue(input);
        this.input = input;
    }

    @Override
    public String toString() {
        return "Очередь: " + input +"\n" +
                "Результат проверки: " + result;
    }

    public void equality(int i, int j) {
        if (i < 0 || j < 0 || i >= j) {
            throw new IllegalArgumentException("Некорректные индексы: i должно быть меньше j и оба >= 0");
        }
        if (input.size() <= j) {
            throw new IllegalArgumentException("Индекс j превышает размер очереди");
        }
        Iterator<Number> iterator = input.iterator();
        for (int k = 0; k < i; k++) {
            iterator.next();
        }
        Number first = iterator.next();
        for (int k = i + 1; k <= j; k++) {
            Number current = iterator.next();
            if(!first.equals(current)) {
                result = false;
            }
        }
        result = true;
    }
}
