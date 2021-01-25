package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public Comment insert(String bookId, String text) {
        var bookTitle = booksService.getBookTitleById(bookId);
        var comment = new Comment(null, bookTitle, text);
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public String getAllCommentsAsTextByBookId(String bookId) {
        var comments = commentRepository.findAllByBook_Id(bookId);
        if (comments.size() > 0) {
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
    public Comment getCommentById(String commentId) {
        var comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            throw new EntityNotFoundException("Комментарий с ID " + commentId + " не найден", null);
        }
        return comment.get();
    }

    @Override
    public void updateComment(String commentId, String text) {
        var comment = getCommentById(commentId);
        comment.setText(text);
        commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(String commentId) {
        var comment = getCommentById(commentId);
        commentRepository.delete(comment);
    }
}
