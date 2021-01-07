package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.Genre;
import ru.otus.borodkin.elibrary.repositories.AuthorRepository;
import ru.otus.borodkin.elibrary.repositories.BookRepository;
import ru.otus.borodkin.elibrary.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public String getAllBooksAsText() {
        var books = bookRepository.getAll();
        return books.stream()
                .map(Book::getBookText)
                .collect(Collectors.joining("\n"));

    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(long bookId) throws EntityNotFoundException {
        var book = bookRepository.getById(bookId);
        if (book.isEmpty()) {
            throw new EntityNotFoundException("Книга с ID " + bookId + " не найдена", null);
        }
        return book.get();
    }

    @Override
    @Transactional
    public Book insertBook(String title, long genreId, List<Long> authors) throws EntityNotFoundException {
        Book book = makeBook(0, title, genreId, authors);
        bookRepository.save(book);
        return book;
    }

    @Override
    public void updateBook(long bookId, String title, long genreId, List<Long> authors) throws EntityNotFoundException {

    }

    @Override
    public void deleteBookById(long bookId) {

    }

    private Book makeBook(long bookId, String title, long genreId, List<Long> authors) throws EntityNotFoundException {
        var genre = genreRepository.getById(genreId);
        if (genre.isEmpty()) {
            throw new EntityNotFoundException("Не найден жанр с ID " + genreId, null);
        }
        List<Author> authorList = new ArrayList<>();
        for (Long authorId : authors) {
            var author = authorRepository.getById(authorId);
            if (author.isEmpty()) {
                throw new EntityNotFoundException("Не найден автор с ID " + authorId, null);
            }
            authorList.add(author.get());
        }
        return new Book(bookId, title, genre.get(), authorList);
    }
}
