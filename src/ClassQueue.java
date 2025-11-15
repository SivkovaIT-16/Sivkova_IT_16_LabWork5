import ru.sivkova.validator.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ClassQueue {
    private Queue<Number> queue;
    private  boolean result;

    public Queue<Number> getQueue() {
        return queue;
    }

    public boolean getResult() {
        return result;
    }

    public void setQueue(Queue<Number> queue) {
        Validator.validateNullQueue(queue);
        this.queue = queue;
    }

    public ClassQueue(){
        this.queue = new LinkedList<>();
    }

    public ClassQueue(Queue<Number> input) {
        Validator.validateNullQueue(input);
        this.queue = input;
    }

    @Override
    public String toString() {
        return "Очередь: " + queue +"\n" +
                "Результат проверки: " + result;
    }

    public void equality(int i, int j) {
        if (i < 0 || j < 0 || i >= j) {
            throw new IllegalArgumentException("Некорректные индексы: i должно быть меньше j и оба >= 0");
        }
        if (queue.size() <= j) {
            throw new IllegalArgumentException("Индекс j превышает размер очереди");
        }
        Iterator<Number> iterator = queue.iterator();
        for (int k = 0; k < i; k++) {
            iterator.next();
        }
        Number first = iterator.next();
        result = true;
        for (int k = i + 1; k <= j; k++) {
            Number current = iterator.next();
            if(!first.equals(current)) {
                result = false;
                break;
            }
        }
    }
}
