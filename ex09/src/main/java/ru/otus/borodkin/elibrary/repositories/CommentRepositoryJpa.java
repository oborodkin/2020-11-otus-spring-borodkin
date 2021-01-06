package ru.otus.borodkin.elibrary.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.Comment;

import javax.persistence.*;
import java.util.List;
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
    public List<Comment> getByBook(long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :book_id", Comment.class);
        EntityGraph<?> entityGraph = em.getEntityGraph("comment-book");
        query.setParameter("book_id", bookId);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c ", Comment.class);
        EntityGraph<?> entityGraph = em.getEntityGraph("comment-book");
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
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
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
