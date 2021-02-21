package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.dto.CommentDto;
import ru.otus.borodkin.elibrary.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentsService {
    List<CommentDto> findByBookId(long bookId);

    Optional<Comment> findById(long commentId);

    CommentDto findDtoById(long commentId);

    CommentDto insertComment(long bookId, String text);

    void updateComment(long commentId, String text);

    void deleteCommentById(long commentId);
}
