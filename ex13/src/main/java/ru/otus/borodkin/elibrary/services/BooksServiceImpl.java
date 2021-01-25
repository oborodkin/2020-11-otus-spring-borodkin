package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.BookTitle;
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
    public String getAllBooksAsText() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> book.getBookText() + "\n" + "Authors:\n\t" + book.getBookAuthorsText())
                .collect(Collectors.joining("\n")) + "\n";

    }

    @Override
    public Book getBookById(String bookId) {
        var book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new EntityNotFoundException("Книга с ID " + bookId + " не найдена", null);
        }
        return book.get();
    }

    @Override
    public BookTitle getBookTitleById(String bookId) {
        var book = this.getBookById(bookId);
        return new BookTitle(book.getId(), book.getTitle());
    }

    @Override
    public Book insertBook(String title, String genreId, List<String> authors) {
        Genre genre = genresService.getGenreById(genreId);
        List<Author> authorList = authorsService.getAuthorsByList(authors);
        Book book = new Book(null, title, genre, authorList);
        bookRepository.save(book);
        return book;
    }

    @Override
    public void updateBook(String bookId, String title, String genreId, List<String> authors) {
        Book book = getBookById(bookId);
        book.setTitle(title);
        book.setGenre(genresService.getGenreById(genreId));
        book.setAuthors(authorsService.getAuthorsByList(authors));
        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(String bookId) {
        Book book = getBookById(bookId);
        bookRepository.delete(book);
    }

}
