package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.dto.CommentDto;
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
        var comments = commentRepository.findAllByBook_Id(pageable, bookId);
        return comments.map(comment -> modelMapper.map(comment, CommentDto.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto findDtoById(long commentId) {
        var optionalComment = this.findById(commentId);
        if (optionalComment.isPresent()) {
            return modelMapper.map(optionalComment.get(), CommentDto.class);
        } else {
            return null;
        }
    }

/*

    @Override
    public Page<CommentDto> findByBookId(long bookId) {
        return null;
    }

    @Override
    @Transactional
    public Comment insertComment(long bookId, String text) {
        var comment = new Comment(0, bookId, text);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void updateComment(long commentId, String text) {
        var optionalComment = findById(commentId);
        if (optionalComment.isPresent()) {
            var comment = optionalComment.get();
            comment.setText(text);
            commentRepository.save(comment);
        }
    }

    @Override
    @Transactional
    public void deleteCommentById(long commentId) {
        var optionalComment = findById(commentId);
        if (optionalComment.isPresent()) {
            commentRepository.delete(optionalComment.get());
        }
    }

 */
}
