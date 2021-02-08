package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.borodkin.elibrary.models.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBook_Id(long bookId);
}
