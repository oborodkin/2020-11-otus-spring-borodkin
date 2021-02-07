package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.borodkin.elibrary.dto.CommentDto;
import ru.otus.borodkin.elibrary.services.CommentsService;

@RequiredArgsConstructor
@RestController
public class BooksCommentsController {
    private final CommentsService commentsService;

    @GetMapping("/rest/books/{bookId}/comments")
    public Page<CommentDto> getBookComments(
            Pageable pageable,
            @PathVariable(value = "bookId") Long bookId) {
        return commentsService.findByBookId(pageable, bookId);
    }
}
