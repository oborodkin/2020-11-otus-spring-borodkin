package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.models.Comment;

public interface CommentsService {
    Comment insert(String bookId, String text);
    String getAllCommentsAsTextByBookId(String bookId);
    Comment getCommentById(String commentId);
    void updateComment(String commentId, String text);
    void deleteCommentById(String commentId);
}
