package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.dto.BookDto;
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
        if (optionalGenre.isPresent()) {
            List<Author> authorList = authorsService.getAuthorsByList(authors);
            var book = new Book(0, title, optionalGenre.get(), authorList, null);
            bookRepository.save(book);
            return modelMapper.map(book, BookDto.class);
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void updateBook(long bookId, String title, long genreId, List<Long> authors) {
        var optionalBook = findById(bookId);
        var optionalGenre = genresService.findById(genreId);
        if (optionalBook.isPresent() && optionalGenre.isPresent()) {
            var book = optionalBook.get();
            var genre = optionalGenre.get();
            book.setTitle(title);
            book.setGenre(genre);
            book.setAuthors(authorsService.getAuthorsByList(authors));
            bookRepository.save(book);
        }
    }

    @Override
    @Transactional
    public void deleteBookById(long bookId) {
        var optionalBook = findById(bookId);
        if (optionalBook.isPresent()) {
            bookRepository.delete(optionalBook.get());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto findDtoById(long bookId) {
        var optionalBook = this.findById(bookId);
        if (optionalBook.isPresent()) {
            return modelMapper.map(optionalBook.get(), BookDto.class);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookDto> findAll(Pageable pageable) {
        var books = bookRepository.findAll(pageable);
        return books.map(book -> modelMapper.map(book, BookDto.class));
    }
}
