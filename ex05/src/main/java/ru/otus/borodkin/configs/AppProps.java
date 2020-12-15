package ru.otus.borodkin.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class AppProps {
    private String filename;
    private int passgrade;
    private Locale locale;
}
