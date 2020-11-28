package ru.otus.borodkin;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.borodkin.domain.TheTest;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello!");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        TheTest theTest = context.getBean(TheTest.class);
        theTest.showTest();
        System.out.println("Have nice day!");
        context.close();
    }
}
