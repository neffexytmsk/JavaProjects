package Tasks;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Taskss {
    static void main() throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
            System.out.println("1. Задача 1");
            System.out.println("2. Задача 2");
            System.out.println("3. Задача 3");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    Task1(in);
                    break;
                case 2:
                    Task2(in);
                    break;
                case 3:
                    Task3(in);
                    break;
                case 0:
                    System.out.println("Программа завершена!");
                    in.close();
                    return;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
    }

    public static void Task1(Scanner sc) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        Scanner in = new Scanner(System.in);
        System.out.print("Введите x: ");
        double x = in.nextDouble();
        System.out.print("Введите y: ");
        double y = in.nextDouble();

        double summ = x + y;

        double result;
        if (summ > 20) {
            result = 3 * (x * x);
            System.out.printf("Сумма %.2f + %.2f = %.2f больше 20, ", x, y, summ);
            System.out.printf("утроенный квадрат первого числа: %.2f%n", result);
        } else {
            result = y * y * y;
            System.out.printf("Сумма %.2f + %.2f = %.2f не больше 20, ", x, y, summ);
            System.out.printf("куб второго числа: %.2f%n", result);
        }
    }

    public static void Task2(Scanner sc) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));

        int[] originalArray = new int[15];
        Random random = new Random();

        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = random.nextInt(20);
        }

        int[] allowedValues = {2, 4, 6, 8, 10};

        System.out.println("Исходный массив: " + Arrays.toString(originalArray));
        System.out.println("Разрешенные значения: " + Arrays.toString(allowedValues));

        int count = 0;
        for (int value : originalArray) {
            for (int allowed : allowedValues) {
                if (value == allowed) {
                    count++;
                    break;
                }
            }
        }
        System.out.println("Количество элементов с разрешенными значениями: " + count);
    }

    public static void Task3(Scanner sc) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        int rows = 5;
        int cols = 4;

        int[][] matrix = new int[rows][cols];
        Random random = new Random();

        System.out.println("Исходная матрица:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(100);
                System.out.printf("%d \t", matrix[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nМаксимальные значения по столбцам:");
        for (int j = 0; j < cols; j++) {
            int max = matrix[0][j];
            for (int i = 1; i < rows; i++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            System.out.println("Столбец " + j + ": " + max);
        }
    }
}

