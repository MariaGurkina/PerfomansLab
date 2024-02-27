package org.example;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);// создание объекта Scanner для чтения ввода с клавиатуры

        System.out.print("Enter value n: ");
        int n = scanner.nextInt();
        System.out.print("Enter value m: ");
        int m = scanner.nextInt();

        //Выводим результат выполнения функции traverseCircularArray с аргументами n и m
        System.out.println("Resulting path: " + traverseCircularArray(n, m));

        scanner.close();
    }


    //Функция для построения пути в круговом массиве
    private static String traverseCircularArray(int n, int m) {
        StringBuilder path = new StringBuilder(); // Создаем объект StringBuilder для построения пути
        int currentIndex = 0;  // И5
        // нициализируем переменную для хранения текущего индекса

        // Проходим по всем элементам массива
        for (int i = 0; i < n; i++) {
            // Вычисляем индекс следующего элемента в круговом массиве
            int nextIndex = (currentIndex + m - 1) % n;
            // Добавляем текущий индекс в путь
            path.append(currentIndex + 1);
            // Если это не последний элемент пути, добавляем разделитель
            if (i != n - 1) {
                path.append(", ");
            }
            // Обновляем текущий индекс
            currentIndex = nextIndex;
        }

        // Возвращаем построенный путь в виде строки
        return path.toString();
    }
}