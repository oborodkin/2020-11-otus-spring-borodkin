package ru.otus.borodkin.utils;

import java.util.List;

public interface Reader {
    List<List<String>> getLines() throws Exception;
}
