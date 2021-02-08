package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.dto.CommentDto;
import ru.otus.borodkin.elibrary.exceptions.RestException;
import ru.otus.borodkin.elibrary.exceptions.RestNotFoundException;
import ru.otus.borodkin.elibrary.models.Comment;
import ru.otus.borodkin.elibrary.repositories.CommentRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    private final BooksService booksService;

    @Override
    @Transactional(readOnly = true)
    public Page<CommentDto> findByBookId(Pageable pageable, long bookId) {
        var book = booksService.findById(bookId);
        if (book.isEmpty()) {
            throw new RestNotFoundException();
        }
        var comments = commentRepository.findAllByBook_Id(pageable, bookId);
        return comments.map(comment -> modelMapper.map(comment, CommentDto.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public CommentDto insertComment(long bookId, String text) {
        var optionalBook = booksService.findById(bookId);
        var comment = new Comment(0, optionalBook.orElseThrow(() -> new RestException("Не найдена книга")), text);
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public void updateComment(long commentId, String text) {
        var optionalComment = this.findById(commentId);
        var comment = optionalComment.orElseThrow(() -> new RestException("Не найден комментарий"));
        comment.setText(text);
        commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(long commentId) {
        var optionalComment = this.findById(commentId);
        var comment = optionalComment.orElseThrow(RestNotFoundException::new);
        commentRepository.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto findDtoById(long commentId) {
        var optionalComment = this.findById(commentId);
        return modelMapper.map(optionalComment.orElseThrow(RestNotFoundException::new), CommentDto.class);
    }
}
