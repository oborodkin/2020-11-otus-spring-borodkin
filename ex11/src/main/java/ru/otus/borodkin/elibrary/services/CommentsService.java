package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.models.Comment;

public interface CommentsService {
    String getAllCommentsAsTextByBookId(long bookId);
    Comment getCommentById(long commentId);
    Comment insertComment(long bookId, String text);
    void updateComment(long commentId, String text);
    void deleteCommentById(long commentId);
}
