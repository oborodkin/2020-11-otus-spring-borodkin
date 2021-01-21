package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.borodkin.elibrary.models.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
