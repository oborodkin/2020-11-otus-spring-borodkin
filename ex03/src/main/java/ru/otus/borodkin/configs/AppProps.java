package ru.otus.borodkin.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProps {

    @Getter
    @Setter
    private String filename;
    @Getter
    @Setter
    private int passgrade;
    @Getter
    @Setter
    private Locale locale;
}
