package ru.otus.borodkin.elibrary.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.borodkin.elibrary.models.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        EntityGraph<?> entityGenreGraph = em.getEntityGraph("book-genre");
        EntityGraph<?> entityAuthorsGraph = em.getEntityGraph("book-authors");
        query.setHint("javax.persistence.fetchgraph", entityGenreGraph);
        query.setHint("javax.persistence.fetchgraph", entityAuthorsGraph);
        return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
