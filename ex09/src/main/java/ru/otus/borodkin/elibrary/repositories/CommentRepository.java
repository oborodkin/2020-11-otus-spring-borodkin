package ru.otus.borodkin.elibrary.repositories;

import ru.otus.borodkin.elibrary.models.Comment;

import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> getById(long id);
    Comment save(Comment comment);
    void delete(Comment comment);
}
