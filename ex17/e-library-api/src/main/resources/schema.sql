drop table if exists books_comments;
drop table if exists books_authors;
drop table if exists books;
drop table if exists authors;
drop table if exists genres;

create table genres
(
    id bigint identity,
    name varchar(255) not null
);

create table authors
(
    id bigint identity,
    full_name varchar(255) not null
);

create table books
(
    id bigint identity,
    title varchar(255) not null,
    genre_id bigint not null,
    foreign key(genre_id) references genres(id)
);

create table books_authors
(
    book_id bigint not null,
    author_id bigint not null,
    primary key (book_id, author_id),
    foreign key(book_id) references books(id), --on delete cascade,
    foreign key(author_id) references authors(id)
);

create table books_comments
(
    id bigint identity,
    book_id bigint not null,
    comment_text varchar(255) not null,
    foreign key(book_id) references books(id) --on delete cascade
);