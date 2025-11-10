import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n;
        do {
            System.out.println("Введите номер задачи:\n" +
                    "0.Завершить программу.\n" +
                    "1.Обобщённая коробка.\n" +
                    "2.Сравнимое.\n" +
                    "3.Список.\n" +
                    "4.Мап.\n" +
                    "5.Сет.\n" +
                    "6.Очередь.\n" +
                    "7.Коллекционирование.");
            n = scanner.nextInt();
            scanner.nextLine();
            switch (n) {
                case 0:
                    System.out.println("Программа завершена.");
                    break;
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    List<Number> list1 = new ArrayList<>();
                    Number val1 = input(list1);
                    ClassList classList1 = new ClassList(list1);
                    classList1.remove(val1);
                    System.out.println(classList1 + "\n");
                    break;
                }
                case 4: {
                    try {
                        File file1 = new File("ClassMap.txt");
                        ClassMap classMap1 = new ClassMap(file1);
                        System.out.println(classMap1 + "\n");
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        File file2 = new File("ClassMapNotVal.txt");
                        ClassMap classMap2 = new ClassMap(file2);
                        System.out.println(classMap2 + "\n");
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        File file3 = new File("ClassMapPass.txt");
                        ClassMap classMap3 = new ClassMap(file3);
                        System.out.println(classMap3 + "\n");
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        File file4 = null;
                        ClassMap classMap4 = new ClassMap(file4);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        File file5 = new File("Map.txt");
                        ClassMap classMap5 = new ClassMap(file5);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        File file6 = new File("Empty.txt");
                        ClassMap classMap6 = new ClassMap(file6);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        File file7 = new File("D:/IdeaProjects");
                        ClassMap classMap7 = new ClassMap(file7);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    break;
                }
                case 5: {
                    try {
                        File file1 = new File("ClassSet.txt");
                        ClassSet classSet1 = new ClassSet(file1);
                        System.out.println(classSet1);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        File file2 = null;
                        ClassSet classSet2 = new ClassSet(file2);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        File file3 = new File("Set.txt");
                        ClassSet classSet3 = new ClassSet(file3);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        File file4 = new File("Empty.txt");
                        ClassSet classSet4 = new ClassSet(file4);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    try {
                        File file5 = new File("D:/IdeaProjects");
                        ClassSet classSet5 = new ClassSet(file5);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    break;
                }
                case 6: {
                    break;
                }
                case 7: {
                    break;
                }
                case 8: {
                    break;
                }
                default: {
                    System.out.println("Некорректный номер задачи. \n");
                    break;
                }
            }
        } while (n != 0);
    }

    private static Number input(List<Number> list) {
        String countStr;
        int count;
        while (true) {
            System.out.print("Введите количество элементов в списке: ");
            countStr = scanner.nextLine();
            if (!Validator.valIsInt(countStr) || Integer.parseInt(countStr) <= 0) {
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
}