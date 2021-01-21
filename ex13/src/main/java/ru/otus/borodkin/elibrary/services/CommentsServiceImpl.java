package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Comment;
import ru.otus.borodkin.elibrary.repositories.BookRepository;
import ru.otus.borodkin.elibrary.repositories.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentRepository commentRepository;
    private final BooksService booksService;

    @Override
    public Comment insert(String bookId, String text) {
        var comment = new Comment(null, text);
        commentRepository.save(comment);
        booksService.addCommentToBook(bookId, comment);
        return comment;
    }

    @Override
    public String getAllCommentsAsTextByBookId(String bookId) {
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

//    @Override
//    @Transactional(readOnly = true)
//    public Comment getCommentById(String commentId) {
//        var comment = commentRepository.findById(commentId);
//        if (comment.isEmpty()) {
//            throw new EntityNotFoundException("Комментарий с ID " + commentId + " не найден", null);
//        }
//        return comment.get();
//    }

//    @Override
//    @Transactional
//    public Comment insertComment(String bookId, String text) {
//        // для проверки наличия книги
//        var book = booksService.getBookById(bookId);
//        Comment comment = new Comment(null, bookId, text);
//        return commentRepository.save(comment);
//    }

//    @Override
//    @Transactional(rollbackFor = EntityNotFoundException.class)
//    public void updateComment(String commentId, String text) {
//        var comment = getCommentById(commentId);
//        comment.setText(text);
//        commentRepository.save(comment);
//    }

//    @Override
//    @Transactional
//    public void deleteCommentById(String commentId) {
//        var comment = getCommentById(commentId);
//        commentRepository.delete(comment);
//    }
}
