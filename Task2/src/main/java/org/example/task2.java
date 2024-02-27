package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class task2 {
    public static void main(String[] args) {
        // Проверяем, переданы ли два аргумента командной строки
        if (args.length != 2) {
            System.err.println("java task2 circle_data.txt points_file.txt");
            return; // Завершаем программу, если аргументов не два
        }

        // Читаем данные о круге из файла
        double[] circleData = readCircleData(args[0]);
        if (circleData == null) {
            System.err.println("Error reading circle data");
            return; // Завершаем программу, если произошла ошибка при чтении данных о круге
        }

        // Извлекаем координаты центра и радиус круга
        double centerX = circleData[0];
        double centerY = circleData[1];
        double radius = circleData[2];

        try (BufferedReader br = new BufferedReader(new FileReader(args[1]))) {
            String line;
            // Читаем файл с точками и определяем их положение относительно круга
            while ((line = br.readLine()) != null) {
                String[] coordinates = line.split(" ");
                double x = Double.parseDouble(coordinates[0]);
                double y = Double.parseDouble(coordinates[1]);
                int position = getPosition(centerX, centerY, radius, x, y); // Определяем положение точки
                printPosition(position); // Выводим результат
            }
        } catch (IOException e) {
            System.err.println("Error reading points file: " + e.getMessage());
        }
    }

    // Метод для чтения данных о круге из файла
    private static double[] readCircleData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // Читаем первую строку файла
            String[] data = line.split(" "); // Разбиваем строку на массив по пробелам
            double[] circleData = new double[3]; // Создаем массив для хранения данных о круге
            for (int i = 0; i < data.length; i++) {
                circleData[i] = Double.parseDouble(data[i]); // Преобразуем строки в числа и записываем их в массив
            }
            return circleData; // Возвращаем массив с данными о круге
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading circle data: " + e.getMessage());
            return null; // В случае ошибки возвращаем null
        }
    }

    // Метод для определения положения точки относительно круга
    private static int getPosition(double centerX, double centerY, double radius, double x, double y) {
        double distanceSquared = (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY); // Вычисляем квадрат расстояния от точки до центра круга
        double radiusSquared = radius * radius; // Вычисляем квадрат радиуса круга
        if (distanceSquared < radiusSquared) {
            return 1; // Возвращаем 1, если точка внутри круга
        } else if (distanceSquared > radiusSquared) {
            return 2; // Возвращаем 2, если точка снаружи круга
        } else {
            return 0; // Возвращаем 0, если точка лежит на окружности круга
        }
    }

    // Метод для вывода положения точки относительно круга
    private static void printPosition(int position) {
        switch (position) {
            case 0:
                System.out.println("On the circle");
                break;
            case 1:
                System.out.println("Inside the circle");
                break;
            case 2:
                System.out.println("Outside the circle");
                break;
            default:
                System.out.println("Invalid position");
        }
    }
}
