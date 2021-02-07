package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.borodkin.elibrary.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByBook_Id(Pageable pageable, long bookId);
}
