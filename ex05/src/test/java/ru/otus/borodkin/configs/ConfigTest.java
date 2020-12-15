package ru.otus.borodkin.configs;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ComponentScan({"ru.otus.borodkin"})
@SpringBootConfiguration
@ExtendWith(SpringExtension.class)
public class ConfigTest {
}