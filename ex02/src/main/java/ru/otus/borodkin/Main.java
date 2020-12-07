package ru.otus.borodkin;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.borodkin.service.TheTestService;

@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello!");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TheTestService theTestService = context.getBean(TheTestService.class);
        //theTestService.showTest();
        theTestService.run();
        System.out.println("Have nice day!");
    }
}
