package ru.otus.borodkin.elibrary.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.borodkin.elibrary.models.*;
import ru.otus.borodkin.elibrary.repositories.AuthorRepository;
import ru.otus.borodkin.elibrary.repositories.BookRepository;
import ru.otus.borodkin.elibrary.repositories.CommentRepository;
import ru.otus.borodkin.elibrary.repositories.GenreRepository;

import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {
    private Genre genreDetective;
    private Genre genreFantasy;
    private Genre genreProgramming;

    private Author authorMcLaughlin;
    private Author authorPollice;
    private Author authorWest;
    private Author authorWatts;
    private Author authorConanDoyle;

    private Book bookOop;
    private Book bookBlindsight;
    private Book bookBaskervilles;
    private Book bookHeadFirst;


    @ChangeSet(order = "000", id = "dropDB", author = "oborodkin", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initGenres", author = "oborodkin", runAlways = true)
    public void initGenres(GenreRepository repository) {
        genreDetective = repository.save(new Genre(null, "Детективный роман"));
        genreFantasy = repository.save(new Genre(null, "Фантастичекский роман"));
        genreProgramming = repository.save(new Genre(null, "Программирование"));
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "oborodkin", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        authorMcLaughlin = repository.save(new Author(null, "Б. Маклафлин"));
        authorPollice = repository.save(new Author(null, "Г. Поллайс"));
        authorWest = repository.save(new Author(null, "Д. Уэст"));
        authorWatts = repository.save(new Author(null, "Питер Уоттс"));
        authorConanDoyle = repository.save(new Author(null, "Артур Конан Дойл"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "oborodkin", runAlways = true)
    public void initBooks(BookRepository repository) {
        bookOop = repository.save(new Book(
                null,
                "Объектно-ориентированный анализ и проектирование",
                genreProgramming,
                List.of(authorMcLaughlin, authorPollice, authorWest)));
        bookBlindsight = repository.save(new Book(
                null,
                "Ложная слепота",
                genreFantasy,
                List.of(authorWatts)));
        bookBaskervilles = repository.save(new Book(
                null,
                "Собака Баскервилей",
                genreDetective,
                List.of(authorConanDoyle)));
        bookHeadFirst = repository.save(new Book(
                null,
                "Head First iPhone and iPad Development",
                genreProgramming,
                List.of(authorMcLaughlin)));
    }

    @ChangeSet(order = "004", id = "initComments", author = "oborodkin", runAlways = true)
    public void initComments(CommentRepository repository) {
        repository.save(new Comment(null, new BookTitle(bookOop.getId(), bookOop.getTitle()), "Хорошая серия"));
        repository.save(new Comment(null, new BookTitle(bookOop.getId(), bookOop.getTitle()), "Надо почитать..."));
        repository.save(new Comment(null, new BookTitle(bookBlindsight.getId(), bookBlindsight.getTitle()), "Ничего не понял, но очень интересно"));
        repository.save(new Comment(null, new BookTitle(bookBlindsight.getId(), bookBlindsight.getTitle()), "Очень много сносок"));
        repository.save(new Comment(null, new BookTitle(bookBaskervilles.getId(), bookBaskervilles.getTitle()), "Классика!"));
    }
}
