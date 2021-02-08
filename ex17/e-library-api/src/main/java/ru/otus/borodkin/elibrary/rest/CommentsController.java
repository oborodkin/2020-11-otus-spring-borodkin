package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.borodkin.elibrary.services.CommentsService;

@RequiredArgsConstructor
@RestController
public class CommentsController {
    private final CommentsService commentsService;

    @PutMapping("/rest/comments/{commentId}")
    public void put(
            @PathVariable Long commentId,
            @RequestParam(value = "text") String text) {
        commentsService.updateComment(commentId, text);
    }

    @DeleteMapping("/rest/comments/{commentId}")
    public void delete(
            @PathVariable Long commentId) {
        commentsService.deleteCommentById(commentId);
    }
}
