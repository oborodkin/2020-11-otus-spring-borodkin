package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.models.Comment;

import java.util.List;

public interface CommentsService {
    List<Comment> getAllCommentsByBookId(long bookId);
    Comment getCommentById(long commentId);
    Comment insertComment(long bookId, String text);
    void updateComment(long bookId, String text);
    void deleteCommentById(long commentId);
}
