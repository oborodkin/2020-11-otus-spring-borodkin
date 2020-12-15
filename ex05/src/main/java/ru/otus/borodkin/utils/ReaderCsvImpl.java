package ru.otus.borodkin.utils;

import org.springframework.stereotype.Component;
import ru.otus.borodkin.configs.AppProps;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Считывает CSV-файл.
 */
@Component
public class ReaderCsvImpl implements Reader {
    private final AppProps appProps;

    public ReaderCsvImpl(AppProps appProps) {
        this.appProps = appProps;
    }

    @Override
    public List<List<String>> getLines() throws IOException {
        List<List<String>> records = new ArrayList<>();
        InputStream s = this.getClass().getResourceAsStream(appProps.getFilename());
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
