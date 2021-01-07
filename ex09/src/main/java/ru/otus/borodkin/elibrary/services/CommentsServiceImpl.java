package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.models.Comment;
import ru.otus.borodkin.elibrary.repositories.CommentRepository;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public String getAllCommentsAsTextByBookId(long bookId) {
        var comments = commentRepository.getByBook(bookId);
        if (comments.size() > 1) {
            return comments.get(0).getBook().getBookText() + "\n" +
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
    public Comment insertComment(long bookId, String text) {
        return null;
    }

    @Override
    public void updateComment(long bookId, String text) {

    }

    @Override
    public void deleteCommentById(long commentId) {

    }
}
