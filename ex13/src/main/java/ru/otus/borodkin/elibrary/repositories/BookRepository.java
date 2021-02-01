package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.borodkin.elibrary.models.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
