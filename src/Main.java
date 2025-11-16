import ru.sivkova.cat.*;
import ru.sivkova.fraction.*;
import ru.sivkova.validator.*;
import ru.sivkova.point.*;

import java.io.File;
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
                    Fraction frac1 = new Fraction(1, 3);
                    Fraction frac2 = new Fraction(2, 4);
                    Fraction frac3 = new Fraction(3, -6);

                    System.out.println("Дробь 1: " + frac1);
                    System.out.println("Дробь 2: " + frac2);
                    System.out.println("Дробь 3: " + frac3);
                    System.out.println();

                    System.out.println("Первое вычисление вещественного значения дроби 1: " + frac1.getDoubleFraction());
                    System.out.println("Второе вычисление вещественного значения дроби 1: " + frac1.getDoubleFraction() + " (из кэша)");
                    System.out.println();

                    Fraction frac4 = new Fraction(2, 4);
                    Fraction frac5 = new Fraction(1, 2);
                    System.out.println("Сравнение: " +frac4 + " и " + frac5 + " = " + frac4.equals(frac5));
                    System.out.println("Хэш-код " + frac4 + " : " + frac4.hashCode());
                    System.out.println("Хэш-код " + frac5 + " : " + frac5.hashCode());
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
                    List<Number> list1 = new ArrayList<>();
                    Number val1 = inputList(list1);
                    ClassList classList1 = new ClassList(list1);
                    classList1.remove(val1);
                    System.out.println(classList1 + "\n");
                    break;
                }
                case 4: {
                    try {
                        System.out.println("Обработка файла с корректными и некорректными строками (один участник с максимальным баллом):");
                        File file1 = new File("ClassMapOneMax.txt");
                        ClassMap classMap1 = new ClassMap(file1);
                        System.out.println(classMap1);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Обработка файла с только некорректными строками:");
                        File file2 = new File("ClassMapNotVal.txt");
                        ClassMap classMap2 = new ClassMap(file2);
                        System.out.println(classMap2);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Обработка файла с только корректными строками (несколько участников с максимальным баллом):");
                        File file3 = new File("ClassMapManyMax.txt");
                        ClassMap classMap3 = new ClassMap(file3);
                        System.out.println(classMap3);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Обработка некорректного файла (первая строка не содержит количество участников):");
                        File file4 = new File("ClassSet.txt");
                        ClassMap classMap4 = new ClassMap(file4);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Обработка некорректного файла (содержит меньше участников, чем указано в первой строке):");
                        File file5 = new File("ClassMapFewStr.txt");
                        ClassMap classMap5 = new ClassMap(file5);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Обработка некорректного файла (первая строка содержит количество участников более 250):");
                        File file6 = new File("ClassMapManyStr.txt");
                        ClassMap classMap6 = new ClassMap(file6);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с файлом null:");
                        File file7 = null;
                        ClassMap classMap7 = new ClassMap(file7);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с несуществующим файлом:");
                        File file8 = new File("Map.txt");
                        ClassMap classMap8 = new ClassMap(file8);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с пустым файлом:");
                        File file9 = new File("Empty.txt");
                        ClassMap classMap9 = new ClassMap(file9);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с указанием директории:");
                        File file10= new File("D:/IdeaProjects");
                        ClassMap classMap10 = new ClassMap(file10);
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
                        ClassSet classSet1 = new ClassSet(file1);
                        System.out.println(classSet1);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с файлом null:");
                        File file2 = null;
                        ClassSet classSet2 = new ClassSet(file2);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с несуществующим файлом:");
                        File file3 = new File("Set.txt");
                        ClassSet classSet3 = new ClassSet(file3);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с пустым файлом:");
                        File file4 = new File("Empty.txt");
                        ClassSet classSet4 = new ClassSet(file4);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с указанием директории:");
                        File file5 = new File("D:/IdeaProjects");
                        ClassSet classSet5 = new ClassSet(file5);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    break;
                }
                case 6: {
                    Queue<Number> queue = new LinkedList<>();
                    inputQueue(queue);
                    ClassQueue queue1 = new ClassQueue(queue);
                    System.out.print("Введите начальный индекс i: ");
                    int i = inputIndex() - 1;
                    System.out.print("Введите конечный индекс j: ");
                    int j = inputIndex() - 1;
                    try {
                        queue1.equality(i, j);
                        System.out.println(queue1);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                }
                case 7: {
                    List<Point> points = inputPoint();
                    PointStream pointStream = new PointStream(points);
                    System.out.println(pointStream);
                    break;
                }
                case 8: {
                    try {
                        System.out.println("Обработка файла с корректными и некорректными строками:");
                        File file1 = new File("HumanStream.txt");
                        HumanStream humanStream1 = new HumanStream(file1);
                        System.out.println(humanStream1);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        System.out.println("Обработка файла с только некорректными строками:");
                        File file2 = new File("HumanStreamNotVal.txt");
                        HumanStream humanStream2 = new HumanStream(file2);
                        System.out.println(humanStream2);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с файлом null:");
                        File file3 = null;
                        HumanStream humanStream3 = new HumanStream(file3);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с несуществующим файлом:");
                        File file4 = new File("Human.txt");
                        HumanStream humanStream4 = new HumanStream(file4);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с пустым файлом:");
                        File file5 = new File("Empty.txt");
                        HumanStream humanStream5 = new HumanStream(file5);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    try {
                        System.out.println("Попытка создания объекта с указанием директории:");
                        File file6 = new File("D:/IdeaProjects");
                        HumanStream humanStream6 = new HumanStream(file6);
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

    private static Number inputList(List<Number> list) {
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

    private static void inputQueue(Queue<Number> queue) {
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

    private static Number inputElement () {
        String numberStr;
        Number number;
        while (true) {
            System.out.print("Введите элемент, который хотите добавить: ");
             numberStr= scanner.nextLine();
            if (!Validator.valIsNumber(numberStr)) {
                System.out.println("Ошибка: введите корректное число.");
                continue;
            }
            if (Validator.valIsInt(numberStr)) {
                number = Integer.parseInt(numberStr);
            }
            number = Double.parseDouble(numberStr);
            break;
        }
        return number;
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