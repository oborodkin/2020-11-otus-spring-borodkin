package ru.otus.borodkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.borodkin.configs.AppProps;
import ru.otus.borodkin.service.TheTestService;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class TstsystemSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TstsystemSpringBootApplication.class, args);
        TheTestService theTestService = context.getBean(TheTestService.class);
        theTestService.run();
    }

}
