package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
public class task3 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java task3 tests.json values.json <report_file_path>");
            System.exit(1);
        }

        String testsFilePath = args[0];
        String valuesFilePath = args[1];
        String reportFilePath = args[2];

        try {
            // Чтение данных из tests.json
            JSONParser parser = new JSONParser();
            JSONObject testsJson = (JSONObject) parser.parse(new FileReader(testsFilePath));

            // Чтение данных из values.json
            JSONObject valuesJson = (JSONObject) parser.parse(new FileReader(valuesFilePath));

            // Обновление значений в tests.json на основе данных из values.json
            updateTestValues(testsJson, valuesJson);

            // Запись обновленных данных в report.json
            FileWriter reportFileWriter = new FileWriter(reportFilePath);
            reportFileWriter.write(testsJson.toJSONString());
            reportFileWriter.close();

            System.out.println("Report generation completed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occurred: " + e.getMessage());
        }
    }

    private static void updateTestValues(JSONObject testsJson, JSONObject valuesJson) {
        JSONArray tests = (JSONArray) testsJson.get("tests");
        JSONArray values = (JSONArray) valuesJson.get("values");

        for (Object testObj : tests) {
            JSONObject test = (JSONObject) testObj;
            long testId = (long) test.get("id");

            for (Object valueObj : values) {
                JSONObject value = (JSONObject) valueObj;
                long valueId = (long) value.get("id");

                if (testId == valueId) {
                    test.put("value", value.get("value"));
                    break;
                }
            }
        }
    }
}
