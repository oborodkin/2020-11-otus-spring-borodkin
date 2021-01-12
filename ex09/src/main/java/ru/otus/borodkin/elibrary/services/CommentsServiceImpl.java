package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Comment;
import ru.otus.borodkin.elibrary.repositories.CommentRepository;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentRepository commentRepository;

    private final BooksService booksService;

    @Override
    @Transactional(readOnly = true)
    public String getAllCommentsAsTextByBookId(long bookId) {
        var book = booksService.getBookById(bookId);
        var comments = book.getComments();
        if (comments.size() > 0) {
            return book.getBookText() + "\n" +
                    "Comments:\n\t" +
                    comments.stream()
                            .map(Comment::getCommentText)
                            .collect(Collectors.joining("\n\t"));
        } else {
            return book.getBookText() + "\n" + "По книге нет комментариев";
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(long commentId) {
        var comment = commentRepository.getById(commentId);
        if (comment.isEmpty()) {
            throw new EntityNotFoundException("Комментарий с ID " + commentId + " не найден", null);
        }
        return comment.get();
    }

    @Override
    @Transactional
    public Comment insertComment(long bookId, String text) {
        // для проверки наличия книги
        var book = booksService.getBookById(bookId);
        Comment comment = new Comment(0, bookId, text);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional(rollbackFor = EntityNotFoundException.class)
    public void updateComment(long commentId, String text) {
        var comment = getCommentById(commentId);
        comment.setText(text);
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(long commentId) {
        var comment = getCommentById(commentId);
        commentRepository.delete(comment);
    }
}
