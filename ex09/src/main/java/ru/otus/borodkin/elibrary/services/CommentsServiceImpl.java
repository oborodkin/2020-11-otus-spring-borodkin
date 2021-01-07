package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Comment;
import ru.otus.borodkin.elibrary.repositories.BookRepository;
import ru.otus.borodkin.elibrary.repositories.CommentRepository;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public String getAllCommentsAsTextByBookId(long bookId) throws EntityNotFoundException {
        var book = bookRepository.getById(bookId);
        if (book.isEmpty()) {
            throw new EntityNotFoundException("Не найдена книга с ID " + bookId, null);
        }
        var comments = commentRepository.getByBook(bookId);
        if (comments.size() > 1) {
            return book.get().getBookText() + "\n" +
                    "Comments:\n\t" +
                    comments.stream()
                            .map(Comment::getCommentText)
                            .collect(Collectors.joining("\n\t"));
        } else {
            return "По книге нет комментариев";
        }
    }

    @Override
    public Comment getCommentById(long commentId) {
        return null;
    }

    @Override
    @Transactional
    public Comment insertComment(long bookId, String text) {
        Comment comment = new Comment(0, bookId, text);
        return commentRepository.save(comment);
    }

    @Override
    public void updateComment(long commentId, String text) throws EntityNotFoundException {
        var comment = commentRepository.getById(commentId);
        if (comment.isEmpty()) {
            throw new EntityNotFoundException("Не найден комментарий с ID " + commentId, null);
        }
        comment.get().setText(text);
        commentRepository.save(comment.get());
    }

    @Override
    public void deleteCommentById(long commentId) {

    }
}
