import ru.sivkova.cat.*;
import ru.sivkova.fraction.*;
import ru.sivkova.validator.*;
import ru.sivkova.point.*;

import java.io.File;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n;
        do {
            System.out.println("Введите номер задачи:\n" +
                    "0.Завершить программу.\n" +
                    "1.Кэшировать дробь.\n" +
                    "2.Кот мяукает.\n" +
                    "3.Список.\n" +
                    "4.Мап.\n" +
                    "5.Сет.\n" +
                    "6.Очередь.\n" +
                    "7.Собрать точки в ломанную.\n" +
                    "8.Сгруппировать людей.");
            n = scanner.nextInt();
            scanner.nextLine();
            switch (n) {
                case 0:
                    System.out.println("Программа завершена.");
                    break;
                case 1: {
                    Fraction frac1 = new Fraction(3, -6);

                    System.out.println("Дробь 1: " + frac1);
                    System.out.println();

                    FractionCache fracCache1 = new FractionCache(frac1);
                    System.out.println("Вычисление и кэширование вещественного значения дроби 1: " + fracCache1.getDoubleFraction());
                    System.out.println("Повторный вызов нахождения вещественного значения (не считаем заново, берём из кэша): " + fracCache1.getDoubleFraction());

                    System.out.println("Изменяем значение дроби 1.");
                    fracCache1.setFraction(77, 2);
                    System.out.println("Дробь 1: " + fracCache1);
                    System.out.println();

                    System.out.print("Вещественное значение в кэше пересчитано: " + fracCache1.getDoubleFraction());
                    System.out.println();

                    Fraction frac2 = new Fraction(3, 5);
                    Fraction frac3 = new Fraction(75, 125);
                    System.out.println("Сравнение: " + frac2 + " и " + frac3 + " = " + frac2.equals(frac3));
                    System.out.println("Хэш-код " + frac2 + " : " + frac2.hashCode());
                    System.out.println("Хэш-код " + frac3 + " : " + frac3.hashCode());
                    System.out.println();
                    System.out.println("Сравнение: " + frac1 + " и " + frac2 + " = " + frac1.equals(frac2));
                    System.out.println("Хэш-код " + frac1 + " : " + frac1.hashCode());
                    System.out.println("Хэш-код " + frac2 + " : " + frac2.hashCode());
                    System.out.println();
                    break;
                }
                case 2: {
                    CountMeowCat countMeowCat = null;
                    System.out.println("Создайте кота.");
                    while(true) {
                        System.out.print("Введите имя кота: ");
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("Ошибка: введите корректное имя кота");
                            continue;
                        }
                        Cat cat = new Cat(name);
                        countMeowCat = new CountMeowCat(cat);
                        System.out.println(cat + " создан.");
                        break;
                    }
                    int m;
                    do {
                        System.out.println("Выберете действие:\n" +
                                "0.Завершить программу.\n" +
                                "1.Вызвать мяуканье.\n" +
                                "2.Сколько раз кот мяукал?");
                        m = scanner.nextInt();
                        scanner.nextLine();
                        switch (m) {
                            case 0:
                                System.out.println("Программа \"Кот мяукает\" завершена.");
                                break;
                            case 1:
                                countMeowCat.meow();
                                break;
                            case 2:
                                System.out.println(countMeowCat);
                                break;
                            default:
                                System.out.println("Некорректный номер действия.\n");
                                break;
                        }
                    } while (m != 0);
                    break;
                }
                case 3: {
                    int m;
                    do {
                        System.out.println("Выберете действие:\n" +
                                "0.Завершить программу.\n" +
                                "1.Работа со списком чисел.\n" +
                                "2.Работа со списком строк.");
                        m = scanner.nextInt();
                        scanner.nextLine();
                        switch (m) {
                            case 0:
                                System.out.println("Программа \"Список\" завершена.");
                                break;
                            case 1:
                                try {
                                    List<Number> list1 = new ArrayList<>();
                                    Number val1 = inputListNumber(list1);
                                    List<Number> result1 = Methods.removeAll(list1, val1);
                                    System.out.println("Исходный список: " + list1);
                                    System.out.println("Удаляем элемент: " + val1);
                                    System.out.println("Результат удаления: " + result1 + "\n");
                                } catch (Exception e) {
                                    System.out.println("Ошибка: " + e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                List<String> list2 = new ArrayList<>();
                                String val2 = inputListString(list2);
                                List<String> result2 = Methods.removeAll(list2, val2);
                                System.out.println("Исходный список: " + list2);
                                System.out.println("Удаляем элемент: " + val2);
                                System.out.println("Результат удаления: " + result2 + "\n");
                                } catch (Exception e) {
                                    System.out.println("Ошибка: " + e.getMessage());
                                }
                                break;
                            default:
                                System.out.println("Некорректный номер действия.\n");
                                break;
                        }
                    } while (m != 0);
                    break;
                }
                case 4: {
                    try {
                        System.out.println("Обработка файла с корректными и некорректными строками (один участник с максимальным баллом):");
                        File file1 = new File("ClassMapOneMax.txt");
                        Methods.findParticipants(file1);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Обработка файла с только некорректными строками:");
                        File file2 = new File("ClassMapNotVal.txt");
                        Methods.findParticipants(file2);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Обработка файла с только корректными строками (несколько участников с максимальным баллом):");
                        File file3 = new File("ClassMapManyMax.txt");
                        Methods.findParticipants(file3);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Обработка некорректного файла (первая строка не содержит количество участников):");
                        File file4 = new File("ClassSet.txt");
                        Methods.findParticipants(file4);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Обработка некорректного файла (содержит меньше участников, чем указано в первой строке):");
                        File file5 = new File("ClassMapFewStr.txt");
                        Methods.findParticipants(file5);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Обработка некорректного файла (первая строка содержит количество участников более 250):");
                        File file6 = new File("ClassMapManyStr.txt");
                        Methods.findParticipants(file6);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с файлом null:");
                        File file7 = null;
                        Methods.findParticipants(file7);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с несуществующим файлом:");
                        File file8 = new File("Map.txt");
                        Methods.findParticipants(file8);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с пустым файлом:");
                        File file9 = new File("Empty.txt");
                        Methods.findParticipants(file9);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с указанием директории:");
                        File file10= new File("D:/IdeaProjects");
                        Methods.findParticipants(file10);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    break;
                }
                case 5: {
                    try {
                        System.out.println("Обработка корректного файла:");
                        File file1 = new File("ClassSet.txt");
                        Methods.findUniqueLetters(file1);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Обработка файла, содержащего только гласные:");
                        File file2 = new File("ClassSetVowel.txt");
                        Methods.findUniqueLetters(file2);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с файлом null:");
                        File file3 = null;
                        Methods.findUniqueLetters(file3);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с несуществующим файлом:");
                        File file4 = new File("Set.txt");
                        Methods.findUniqueLetters(file4);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с пустым файлом:");
                        File file5 = new File("Empty.txt");
                        Methods.findUniqueLetters(file5);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с указанием директории:");
                        File file6 = new File("D:/IdeaProjects");
                        Methods.findUniqueLetters(file6);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    break;
                }
                case 6: {
                    int m;
                    do {
                        System.out.println("Выберете действие:\n" +
                                "0.Завершить программу.\n" +
                                "1.Работа с очередью чисел.\n" +
                                "2.Работа с очередью строк.");
                        m = scanner.nextInt();
                        scanner.nextLine();
                        switch (m) {
                            case 0:
                                System.out.println("Программа \"Очередь\" завершена.");
                                break;
                            case 1:
                                Queue<Number> queue1 = new LinkedList<>();
                                inputQueueNumber(queue1);
                                System.out.print("Введите начальный индекс i: ");
                                int iNum = inputIndex() - 1;
                                System.out.print("Введите конечный индекс j: ");
                                int jNum = inputIndex() - 1;
                                try {
                                    Methods.equality(queue1, iNum, jNum);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Ошибка: " + e.getMessage());
                                }
                                break;
                            case 2:
                                Queue<String> queue2 = new LinkedList<>();
                                inputQueueString(queue2);
                                System.out.print("Введите начальный индекс i: ");
                                int iStr = inputIndex() - 1;
                                System.out.print("Введите конечный индекс j: ");
                                int jStr = inputIndex() - 1;
                                try {
                                    Methods.equality(queue2, iStr, jStr);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Ошибка: " + e.getMessage());
                                }
                                break;
                            default:
                                System.out.println("Некорректный номер действия.\n");
                                break;
                        }
                    } while (m != 0);
                    break;
                }
                case 7: {
                    List<Point> points = inputPoint();
                    PointStream.collectPolyline(points);
                    break;
                }
                case 8: {
                    try {
                        System.out.println("Обработка файла с корректными и некорректными строками:");
                        File file1 = new File("HumanStream.txt");
                        HumanStream.collectionHuman(file1);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        System.out.println("Обработка файла с только некорректными строками:");
                        File file2 = new File("HumanStreamNotVal.txt");
                        HumanStream.collectionHuman(file2);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с файлом null:");
                        File file3 = null;
                        HumanStream.collectionHuman(file3);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с несуществующим файлом:");
                        File file4 = new File("Human.txt");
                        HumanStream.collectionHuman(file4);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с пустым файлом:");
                        File file5 = new File("Empty.txt");
                        HumanStream.collectionHuman(file5);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с указанием директории:");
                        File file6 = new File("D:/IdeaProjects");
                        HumanStream.collectionHuman(file6);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    break;
                }
                default: {
                    System.out.println("Некорректный номер задачи. \n");
                    break;
                }
            }
        } while (n != 0);
    }

    private static Number inputListNumber(List<Number> list) {
        String countStr;
        int count;
        while (true) {
            System.out.print("Введите количество элементов в списке: ");
            countStr = scanner.nextLine();
            if (!Validator.valIsInt(countStr) || Integer.parseInt(countStr) < 0) {
                System.out.println("Ошибка: введите корректное число.");
                continue;
            }
            count = Integer.parseInt(countStr);
            break;
        }
        for (int i = 0; i < count; i++) {
            String numStr;
            while (true) {
                System.out.print("Введите " + (i + 1) + " элемент списка: ");
                numStr = scanner.nextLine();
                if (!Validator.valIsNumber(numStr)) {
                    System.out.println("Ошибка: введите корректное число.");
                } else {
                    if (Validator.valIsInt(numStr)) {
                        list.add(Integer.parseInt(numStr));
                    } else {
                        list.add(Double.parseDouble(numStr));
                    }
                    break;
                }
            }
        }
        String valStr;
        while (true) {
            System.out.print("Введите значение, которое хотите удалить: ");
            valStr = scanner.nextLine();
            if (!Validator.valIsNumber(valStr)) {
                System.out.println("Ошибка: введите корректное число.");
                continue;
            }
            break;
        }
        return Double.parseDouble(valStr);
    }

    private static String inputListString(List<String> list) {
        String countStr;
        int count;
        while (true) {
            System.out.print("Введите количество строк в списке: ");
            countStr = scanner.nextLine();
            if (!Validator.valIsInt(countStr) || Integer.parseInt(countStr) < 0) {
                System.out.println("Ошибка: введите корректное число.");
                continue;
            }
            count = Integer.parseInt(countStr);
            break;
        }
        for (int i = 0; i < count; i++) {
            String str;
            while (true) {
                System.out.print("Введите " + (i + 1) + " строку списка: ");
                str = scanner.nextLine();
                if (str.isEmpty()) {
                    System.out.println("Ошибка: введена пустая строка. Повторите попытку.");
                    continue;
                }
                list.add(str);
                break;
            }
        }
        String valStr;
        while (true) {
            System.out.print("Введите строку, которую хотите удалить: ");
            valStr = scanner.nextLine();
            if (valStr.isEmpty()) {
                System.out.println("Ошибка: введена пустая строка. Повторите попытку.");
                continue;
            }
            break;
        }
        return valStr;
    }

    private static void inputQueueNumber(Queue<Number> queue) {
        String countStr;
        int count;
        while (true) {
            System.out.print("Введите количество элементов в очереди: ");
            countStr = scanner.nextLine();
            if (!Validator.valIsInt(countStr) || Integer.parseInt(countStr) < 0) {
                System.out.println("Ошибка: введите корректное число.");
                continue;
            }
            count = Integer.parseInt(countStr);
            break;
        }
        for (int i = 0; i < count; i++) {
            String numStr;
            while (true) {
                System.out.print("Введите " + (i + 1) + " элемент очереди: ");
                numStr = scanner.nextLine();
                if (!Validator.valIsNumber(numStr)) {
                    System.out.println("Ошибка: введите корректное число.");
                } else {
                    if (Validator.valIsInt(numStr)) {
                        queue.add(Integer.parseInt(numStr));
                    } else {
                        queue.add(Double.parseDouble(numStr));
                    }
                    break;
                }
            }
        }
    }
    private static void inputQueueString(Queue<String> queue) {
        String countStr;
        int count;
        while (true) {
            System.out.print("Введите количество элементов в очереди: ");
            countStr = scanner.nextLine();
            if (!Validator.valIsInt(countStr) || Integer.parseInt(countStr) < 0) {
                System.out.println("Ошибка: введите корректное число.");
                continue;
            }
            count = Integer.parseInt(countStr);
            break;
        }
        for (int i = 0; i < count; i++) {
            String str;
            while (true) {
                System.out.print("Введите " + (i + 1) + " элемент очереди: ");
                str = scanner.nextLine();
                if (str.isEmpty()) {
                    System.out.println("Ошибка: введена пустая строка. Повторите попытку.");
                    continue;
                }
                queue.add(str);
                break;
            }
        }
    }

    private static Integer inputIndex () {
        String indexStr;
        int index;
        while (true) {
            indexStr= scanner.nextLine();
            if (!Validator.valIsInt(indexStr)) {
                System.out.println("Ошибка: введите корректный индекс.");
                continue;
            }
            index = Integer.parseInt(indexStr);
            break;
        }
        return index;
    }

    private static List<Point>  inputPoint() {
        List<Point> points = new ArrayList<>();
        int count;
        while (true) {
            System.out.print("Введите количество точек: ");
            String countStr = scanner.nextLine();
            if (!Validator.valIsInt(countStr) || Integer.parseInt(countStr) < 0) {
                System.out.println("Ошибка: введите целое неотрицательное число.");
                continue;
            }
            count = Integer.parseInt(countStr);
            break;
        }
        if (count == 0) {
            return points;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Ввод координат " + (i + 1) + " точки.");
            double x, y;
            while (true) {
                System.out.print("Введите координату X: ");
                String xStr = scanner.nextLine();
                if (!Validator.valIsDouble(xStr)) {
                    System.out.println("Ошибка: введите корректное значение координаты X.");
                    continue;
                }
                x = Double.parseDouble(xStr);
                break;
            }
            while (true) {
                System.out.print("Введите координату Y: ");
                String yStr = scanner.nextLine();
                if (!Validator.valIsDouble(yStr)) {
                    System.out.println("Ошибка: введите корректное значение координаты Y.");
                    continue;
                }
                y = Double.parseDouble(yStr);
                break;
            }
            Point point = new Point(x,y);
            points.add(point);
            System.out.println("Точка " + point.toString() + " добавлена.");
            System.out.println();
        }
        return points;
    }
}