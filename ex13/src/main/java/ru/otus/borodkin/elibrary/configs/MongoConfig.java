package ru.otus.borodkin.elibrary.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "ru.otus.borodkin.elibrary.repositories")
@Configuration
public class MongoConfig {
}
