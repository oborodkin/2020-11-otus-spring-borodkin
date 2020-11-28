package ru.otus.borodkin.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {
    private final String fileName;

    /**
     * Считывает CSV-файл
     * @param fileName имя файла
     */
    public CsvReader(String fileName) {
        this.fileName = fileName;
    }

    public List<List<String>> getLines() throws IOException {
        List<List<String>> records = new ArrayList<>();
        InputStream s = this.getClass().getResourceAsStream(fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(s))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }
        // подразумевается, что первая строка содержит заголовок
        if (records.size() > 0) {
            records.remove(0);
        }
        return records;
    }
}
