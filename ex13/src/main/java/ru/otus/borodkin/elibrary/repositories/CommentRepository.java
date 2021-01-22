package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.borodkin.elibrary.models.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByBook_Id(String bookId);
}
