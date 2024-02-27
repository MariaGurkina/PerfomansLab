package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class task4 {
    public static void main(String[] args) {
        // Проверяем, передано ли имя файла в качестве аргумента командной строки
        if (args.length < 1) {
            System.out.println("Usage: java MinMoves <input_file>");
            return; // Завершаем программу, если аргументы не переданы
        }

        // Получаем имя файла из аргумента командной строки
        String filename = args[0];
        List<Integer> nums = new ArrayList<>(); // Создаем список для хранения чисел из файла

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // Читаем файл построчно и добавляем числа в список
            while ((line = reader.readLine()) != null) {
                nums.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            // В случае ошибки чтения файла выводим сообщение об ошибке
            System.err.println("Error reading file: " + e.getMessage());
            return; // Завершаем программу
        }

        // Вычисляем минимальное количество ходов для приведения всех чисел к одному
        int minMoves = minMoves(nums);
        // Выводим результат
        System.out.println(minMoves);
    }

    // Метод для вычисления минимального количества ходов
    private static int minMoves(List<Integer> nums) {
        Collections.sort(nums); // Сортируем список чисел
        int median = nums.get(nums.size() / 2); // Находим медиану списка
        int moves = 0; // Переменная для подсчета количества ходов

        // Вычисляем сумму абсолютных разностей каждого числа с медианой
        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        return moves; // Возвращаем минимальное количество ходов
    }
}
