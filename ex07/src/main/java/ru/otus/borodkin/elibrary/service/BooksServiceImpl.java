package ru.otus.borodkin.elibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.borodkin.elibrary.dao.AuthorsDao;
import ru.otus.borodkin.elibrary.dao.BooksDao;
import ru.otus.borodkin.elibrary.dao.GenresDao;
import ru.otus.borodkin.elibrary.domain.Author;
import ru.otus.borodkin.elibrary.domain.Book;
import ru.otus.borodkin.elibrary.domain.Genre;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {
    private final BooksDao booksDao;
    private final AuthorsDao authorsDao;
    private final GenresDao genresDao;

    @Override
    public List<Book> getAllBooks() {
        return booksDao.getAll();
    }

    @Override
    public Book getBookById(int bookId) {
        return booksDao.getById(bookId);
    }

    @Override
    public Book insertBook(String title, int genreId, int authorId) throws EntityNotFoundException {
        Book book = makeBook(0, title, genreId, authorId);
        var bookId = booksDao.insert(book);
        book.setId(bookId);
        return book;
    }

    @Override
    public void updateBook(int bookId, String title, int genreId, int authorId) throws EntityNotFoundException {
        Book book = makeBook(bookId, title, genreId, authorId);
        booksDao.update(book);
    }

    private Book makeBook(int bookId, String title, int genreId, int authorId) throws EntityNotFoundException {
        Genre genre = null;
        Author author;
        try {
            genre = genresDao.getById(genreId);
            author = authorsDao.getById(authorId);
        } catch (EmptyResultDataAccessException e) {
            if (genre == null) {
                throw new EntityNotFoundException("Указанный жанр не найден", e);
            } else {
                throw new EntityNotFoundException("Указанный автор не найден", e);
            }
        }
        return new Book(bookId, title, genre, author);
    }

    @Override
    public void deleteBookById(int bookId) {
        booksDao.deleteById(bookId);
    }
}
