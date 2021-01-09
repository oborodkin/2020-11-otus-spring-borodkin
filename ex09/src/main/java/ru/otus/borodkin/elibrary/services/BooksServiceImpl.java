package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.Genre;
import ru.otus.borodkin.elibrary.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {
    private final BookRepository bookRepository;

    private final GenresService genresService;
    private final AuthorsService authorsService;

    @Override
    @Transactional(readOnly = true)
    public String getAllBooksAsText() {
        List<Book> books = bookRepository.getAll();
        return books.stream()
                .map(book -> book.getBookText() + "\n" + "Authors:\n\t" + book.getBookAuthorsText())
                .collect(Collectors.joining("\n")) + "\n";

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
    @Transactional(rollbackFor = EntityNotFoundException.class)
    public Book insertBook(String title, long genreId, List<Long> authors) throws EntityNotFoundException {
        Genre genre = genresService.getGenreById(genreId);
        List<Author> authorList = authorsService.getAuthorsByList(authors);
        Book book = new Book(0, title, genre, authorList, null);
        bookRepository.save(book);
        return book;
    }

    @Override
    @Transactional(rollbackFor = EntityNotFoundException.class)
    public void updateBook(long bookId, String title, long genreId, List<Long> authors) throws EntityNotFoundException {
        Book book = getBookById(bookId);
        book.setTitle(title);
        book.setGenre(genresService.getGenreById(genreId));
        book.setAuthors(authorsService.getAuthorsByList(authors));
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBookById(long bookId) {
        bookRepository.deleteById(bookId);
    }
}
