package ru.otus.borodkin.elibrary.repositories;

import ru.otus.borodkin.elibrary.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> getById(long id);
    List<Comment> getByBook(long bookId);
    Comment save(Comment comment);
    void deleteById(long id);
}
