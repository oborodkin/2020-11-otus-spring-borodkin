package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.borodkin.elibrary.models.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
