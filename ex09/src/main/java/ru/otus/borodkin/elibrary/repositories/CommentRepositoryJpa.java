package ru.otus.borodkin.elibrary.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.borodkin.elibrary.models.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        }
        else {
            return em.merge(comment);
        }
    }

    @Override
    public void delete(Comment comment) {
        em.remove(comment);
    }
}
