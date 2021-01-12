package ru.otus.borodkin.elibrary.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.borodkin.elibrary.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public List<Author> getByList(List<Long> authors) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.id in :authors", Author.class);
        query.setParameter("authors", authors);
        return query.getResultList();
    }
}
