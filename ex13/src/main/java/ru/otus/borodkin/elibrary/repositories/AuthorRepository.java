package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.borodkin.elibrary.models.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    List<Author> findAllByIdIn(List<String> authors);
}
