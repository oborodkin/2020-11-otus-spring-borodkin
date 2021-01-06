package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.repositories.BookRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {
    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
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
    public Book insertBook(String title, long genreId, List<Long> authors) throws EntityNotFoundException {
        return null;
    }

    @Override
    public void updateBook(long bookId, String title, long genreId, List<Long> authors) throws EntityNotFoundException {

    }

    @Override
    public void deleteBookById(long bookId) {

    }
}
