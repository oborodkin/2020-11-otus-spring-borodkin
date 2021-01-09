package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Comment;

import java.util.List;

public interface CommentsService {
    String getAllCommentsAsTextByBookId(long bookId) throws EntityNotFoundException;
    Comment getCommentById(long commentId) throws EntityNotFoundException;
    Comment insertComment(long bookId, String text) throws EntityNotFoundException;
    void updateComment(long commentId, String text) throws EntityNotFoundException;
    void deleteCommentById(long commentId);
}
