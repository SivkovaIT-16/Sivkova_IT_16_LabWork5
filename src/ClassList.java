import ru.sivkova.validator.*;

import java.util.ArrayList;
import java.util.List;

public class ClassList {
    private List<Number> list;
    private List<Number> result;

    public List<Number> getList() {
        return list;
    }

    public List<Number> getResult() {
        return result;
    }

    public void setList(List<Number> list) {
        Validator.validateNullList(list);
        this.list = list;
    }

    public ClassList() {
        this.list = new ArrayList<>();
        this.result = new ArrayList<>();
    }

    public ClassList(List<Number> list) {
        Validator.validateNullList(list);
        this.list = list;
        this.result = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Исходный список: " + list + "\n" +
                "Результат удаления указанного элемента: " + result;
    }

    public void remove(Number value) {
        if (list.isEmpty()) {
            return;
        }

        for (Number num: list) {
            if (!isEqual(num, value)) {
                this.result.add(num);
            }
        }
    }

    private boolean isEqual(Number num1, Number num2) {
        if (num1 == null && num2 == null) return true;
        if (num1 == null || num2 == null) return false;

        return num1.doubleValue() == num2.doubleValue();
    }
}