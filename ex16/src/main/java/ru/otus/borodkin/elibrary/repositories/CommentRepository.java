package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.borodkin.elibrary.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
