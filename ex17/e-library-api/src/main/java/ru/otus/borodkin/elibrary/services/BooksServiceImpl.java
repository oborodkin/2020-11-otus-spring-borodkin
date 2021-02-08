package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.dto.BookDto;
import ru.otus.borodkin.elibrary.exceptions.RestException;
import ru.otus.borodkin.elibrary.exceptions.RestNotFoundException;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {
    private final BookRepository bookRepository;
    private final GenresService genresService;
    private final AuthorsService authorsService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(long bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    @Transactional
    public BookDto insertBook(String title, long genreId, List<Long> authors) {
        var optionalGenre = genresService.findById(genreId);
        List<Author> authorList = authorsService.getAuthorsByList(authors);
        var book = new Book(0, title, optionalGenre.orElseThrow(() -> new RestException("Не найден жанр")), authorList, null);
        bookRepository.save(book);
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    @Transactional
    public void updateBook(long bookId, String title, long genreId, List<Long> authors) {
        var optionalBook = findById(bookId);
        var optionalGenre = genresService.findById(genreId);
        var book = optionalBook.orElseThrow(() -> new RestException("Не найдена книга"));
        var genre = optionalGenre.orElseThrow(() -> new RestException("Не найден жанр"));
        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthors(authorsService.getAuthorsByList(authors));
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBookById(long bookId) {
        var optionalBook = findById(bookId);
        bookRepository.delete(optionalBook.orElseThrow(RestNotFoundException::new));
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto findDtoById(long bookId) {
        var optionalBook = this.findById(bookId);
        return modelMapper.map(optionalBook.orElseThrow(RestNotFoundException::new), BookDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookDto> findAll(Pageable pageable) {
        var books = bookRepository.findAll(pageable);
        return books.map(book -> modelMapper.map(book, BookDto.class));
    }
}
